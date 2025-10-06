import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HighestSpendingCustomer {

    public static class SalesMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
        
        private Text customerId = new Text();
        private DoubleWritable salesAmount = new DoubleWritable();
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            String line = value.toString();
            if (line.startsWith("OrderID") || line.startsWith("order")) {
                return;
            }
            
            try {
                String[] fields = line.split(",");
                String custId = fields[1].trim();
                double amount = Double.parseDouble(fields[3].trim());
                
                customerId.set(custId);
                salesAmount.set(amount);
                
                context.write(customerId, salesAmount);
                
            } catch (Exception e) {
                System.err.println("Error processing line: " + line);
            }
        }
    }
    
    public static class SalesReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
        
        private DoubleWritable result = new DoubleWritable();
        
        @Override
        public void reduce(Text key, Iterable<DoubleWritable> values, Context context) 
                throws IOException, InterruptedException {
            
            double totalSales = 0.0;
            
            for (DoubleWritable val : values) {
                totalSales += val.get();
            }
            
            result.set(totalSales);
            context.write(key, result);
        }
    }
    
    public static class MaxMapper extends Mapper<LongWritable, Text, Text, Text> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            try {
                String line = value.toString();
                String[] parts = line.split("\t");
                
                if (parts.length == 2) {
                    String customerId = parts[0].trim();
                    String amount = parts[1].trim();
                    
                    context.write(new Text("max"), new Text(customerId + ":" + amount));
                }
            } catch (Exception e) {
                System.err.println("Error in MaxMapper: " + e.getMessage());
            }
        }
    }
    
    public static class MaxReducer extends Reducer<Text, Text, Text, DoubleWritable> {
        
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) 
                throws IOException, InterruptedException {
            
            String maxCustomer = "";
            double maxAmount = Double.MIN_VALUE;
            
            for (Text val : values) {
                String[] parts = val.toString().split(":");
                String customerId = parts[0];
                double amount = Double.parseDouble(parts[1]);
                
                if (amount > maxAmount) {
                    maxAmount = amount;
                    maxCustomer = customerId;
                }
            }
            
            context.write(new Text(maxCustomer), new DoubleWritable(maxAmount));
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        if (args.length != 3) {
            System.err.println("Usage: HighestSpendingCustomer <input> <temp_output> <final_output>");
            System.exit(-1);
        }
        
        Configuration conf1 = new Configuration();
        
        Job job1 = Job.getInstance(conf1, "Calculate Customer Totals");
        job1.setJarByClass(HighestSpendingCustomer.class);
        job1.setMapperClass(SalesMapper.class);
        job1.setReducerClass(SalesReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(DoubleWritable.class);
        
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));
        
        if (!job1.waitForCompletion(true)) {
            System.exit(1);
        }
        
        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "Find Highest Spending Customer");
        job2.setJarByClass(HighestSpendingCustomer.class);
        job2.setMapperClass(MaxMapper.class);
        job2.setReducerClass(MaxReducer.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
        
        FileInputFormat.addInputPath(job2, new Path(args[1]));
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));
        
        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}