import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CustomersWithMultipleOrders {

    public static class OrderCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        
        private Text customerId = new Text();
        private final static IntWritable one = new IntWritable(1);
        
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
                
                customerId.set(custId);
                context.write(customerId, one);
                
            } catch (Exception e) {
                System.err.println("Error in OrderCountMapper: " + line);
            }
        }
    }
    
    public static class CountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) 
                throws IOException, InterruptedException {
            
            int count = 0;
            
            for (IntWritable val : values) {
                count += val.get();
            }
            
            if (count > 1) {
                context.write(key, new IntWritable(count));
            }
        }
    }
    
    public static class CustomerMapper extends Mapper<LongWritable, Text, Text, Text> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            String line = value.toString();
            if (line.startsWith("CustomerID") || line.startsWith("customer")) {
                return;
            }
            
            try {
                String[] fields = line.split(",");
                String customerId = fields[0].trim();
                String customerName = fields[1].trim();
                
                context.write(new Text(customerId), new Text("C:" + customerName));
                
            } catch (Exception e) {
                System.err.println("Error in CustomerMapper: " + line);
            }
        }
    }
    
    public static class FilteredOrderMapper extends Mapper<LongWritable, Text, Text, Text> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            try {
                String[] parts = value.toString().split("\t");
                String customerId = parts[0].trim();
                String count = parts[1].trim();
                
                context.write(new Text(customerId), new Text("O:" + count));
            } catch (Exception e) {
                System.err.println("Error in FilteredOrderMapper: " + e.getMessage());
            }
        }
    }
    
    public static class JoinReducer extends Reducer<Text, Text, Text, Text> {
        
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) 
                throws IOException, InterruptedException {
            
            String customerName = null;
            String orderCount = null;
            
            for (Text val : values) {
                String value = val.toString();
                String[] parts = value.split(":");
                
                if (parts[0].equals("C")) {
                    customerName = parts[1];
                } else if (parts[0].equals("O")) {
                    orderCount = parts[1];
                }
            }
            
            if (customerName != null && orderCount != null) {
                context.write(new Text(customerName), new Text("OrderCount: " + orderCount));
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        if (args.length != 4) {
            System.err.println("Usage: CustomersWithMultipleOrders <orders_input> <customers_input> <temp_output> <final_output>");
            System.exit(-1);
        }
        
        Configuration conf1 = new Configuration();
        Job job1 = Job.getInstance(conf1, "Count Orders Per Customer");
        job1.setJarByClass(CustomersWithMultipleOrders.class);
        job1.setMapperClass(OrderCountMapper.class);
        job1.setReducerClass(CountReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[2]));
        
        if (!job1.waitForCompletion(true)) {
            System.exit(1);
        }
        
        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "Join Customer Information");
        job2.setJarByClass(CustomersWithMultipleOrders.class);
        
        job2.setReducerClass(JoinReducer.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(Text.class);
        
        MultipleInputs.addInputPath(job2, new Path(args[1]), TextInputFormat.class, CustomerMapper.class);
        MultipleInputs.addInputPath(job2, new Path(args[2]), TextInputFormat.class, FilteredOrderMapper.class);
        FileOutputFormat.setOutputPath(job2, new Path(args[3]));
        
        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}