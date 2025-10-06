import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CitySalesPercentage {

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
                String city = fields[2].trim();
                
                context.write(new Text(customerId), new Text("C:" + city));
                
            } catch (Exception e) {
                System.err.println("Error in CustomerMapper: " + line);
            }
        }
    }
    
    public static class OrderMapper extends Mapper<LongWritable, Text, Text, Text> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            String line = value.toString();
            if (line.startsWith("OrderID") || line.startsWith("order")) {
                return;
            }
            
            try {
                String[] fields = line.split(",");
                String customerId = fields[1].trim();
                String amount = fields[3].trim();
                
                context.write(new Text(customerId), new Text("O:" + amount));
                
            } catch (Exception e) {
                System.err.println("Error in OrderMapper: " + line);
            }
        }
    }
    
    public static class JoinReducer extends Reducer<Text, Text, Text, DoubleWritable> {
        
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) 
                throws IOException, InterruptedException {
            
            String city = null;
            double totalSales = 0.0;
            
            for (Text val : values) {
                String value = val.toString();
                String[] parts = value.split(":");
                
                if (parts[0].equals("C")) {
                    city = parts[1];
                } else if (parts[0].equals("O")) {
                    totalSales += Double.parseDouble(parts[1]);
                }
            }
            
            if (city != null && totalSales > 0) {
                context.write(new Text(city), new DoubleWritable(totalSales));
            }
        }
    }
    
    public static class CityAggregateMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            try {
                String[] parts = value.toString().split("\t");
                String city = parts[0].trim();
                double amount = Double.parseDouble(parts[1].trim());
                
                context.write(new Text(city), new DoubleWritable(amount));
            } catch (Exception e) {
                System.err.println("Error in CityAggregateMapper: " + e.getMessage());
            }
        }
    }
    
    public static class CityAggregateReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
        
        @Override
        public void reduce(Text key, Iterable<DoubleWritable> values, Context context) 
                throws IOException, InterruptedException {
            
            double totalSales = 0.0;
            
            for (DoubleWritable val : values) {
                totalSales += val.get();
            }
            
            context.write(key, new DoubleWritable(totalSales));
        }
    }
    
    public static class TotalSalesMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            try {
                String[] parts = value.toString().split("\t");
                double amount = Double.parseDouble(parts[1].trim());
                
                context.write(new Text("TOTAL"), new DoubleWritable(amount));
            } catch (Exception e) {
                System.err.println("Error in TotalSalesMapper: " + e.getMessage());
            }
        }
    }
    
    public static class TotalSalesReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
        
        @Override
        public void reduce(Text key, Iterable<DoubleWritable> values, Context context) 
                throws IOException, InterruptedException {
            
            double grandTotal = 0.0;
            
            for (DoubleWritable val : values) {
                grandTotal += val.get();
            }
            
            context.write(key, new DoubleWritable(grandTotal));
        }
    }
    
    public static class PercentageMapper extends Mapper<LongWritable, Text, Text, Text> {
        
        private double grandTotal = 0.0;
        
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            Configuration conf = context.getConfiguration();
            grandTotal = conf.getDouble("grand.total", 1.0);
        }
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            try {
                String line = value.toString();
                
                if (line.startsWith("TOTAL")) {
                    return;
                }
                
                String[] parts = line.split("\t");
                String city = parts[0].trim();
                double cityTotal = Double.parseDouble(parts[1].trim());
                
                double percentage = (cityTotal / grandTotal) * 100.0;
                
                context.write(new Text(city), 
                    new Text(String.format("Sales: %.2f, Percentage: %.2f%%", cityTotal, percentage)));
                
            } catch (Exception e) {
                System.err.println("Error in PercentageMapper: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        if (args.length != 6) {
            System.err.println("Usage: CitySalesPercentage <customers_input> <orders_input> <temp1> <temp2> <temp3> <final_output>");
            System.exit(-1);
        }
        
        Configuration conf1 = new Configuration();
        Job job1 = Job.getInstance(conf1, "Join Customers and Orders");
        job1.setJarByClass(CitySalesPercentage.class);
        job1.setReducerClass(JoinReducer.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);
        
        MultipleInputs.addInputPath(job1, new Path(args[0]), TextInputFormat.class, CustomerMapper.class);
        MultipleInputs.addInputPath(job1, new Path(args[1]), TextInputFormat.class, OrderMapper.class);
        FileOutputFormat.setOutputPath(job1, new Path(args[2]));
        
        if (!job1.waitForCompletion(true)) {
            System.exit(1);
        }
        
        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "Aggregate Sales Per City");
        job2.setJarByClass(CitySalesPercentage.class);
        job2.setMapperClass(CityAggregateMapper.class);
        job2.setReducerClass(CityAggregateReducer.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(DoubleWritable.class);
        
        FileInputFormat.addInputPath(job2, new Path(args[2]));
        FileOutputFormat.setOutputPath(job2, new Path(args[3]));
        
        if (!job2.waitForCompletion(true)) {
            System.exit(1);
        }
        
        Configuration conf3 = new Configuration();
        Job job3 = Job.getInstance(conf3, "Calculate Grand Total");
        job3.setJarByClass(CitySalesPercentage.class);
        job3.setMapperClass(TotalSalesMapper.class);
        job3.setReducerClass(TotalSalesReducer.class);
        job3.setOutputKeyClass(Text.class);
        job3.setOutputValueClass(DoubleWritable.class);
        
        FileInputFormat.addInputPath(job3, new Path(args[3]));
        FileOutputFormat.setOutputPath(job3, new Path(args[4]));
        
        if (!job3.waitForCompletion(true)) {
            System.exit(1);
        }
        
        System.out.println("Please check temp3 output for TOTAL and run Job 4 with -D grand.total=<value>");
        
        System.exit(0);
    }
}