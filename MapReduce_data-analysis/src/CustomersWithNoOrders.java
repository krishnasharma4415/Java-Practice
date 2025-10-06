import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CustomersWithNoOrders {

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
                
                context.write(new Text(customerId), new Text("O:1"));
                
            } catch (Exception e) {
                System.err.println("Error in OrderMapper: " + line);
            }
        }
    }
    
    public static class NoOrderReducer extends Reducer<Text, Text, Text, NullWritable> {
        
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) 
                throws IOException, InterruptedException {
            
            String customerName = null;
            boolean hasOrder = false;
            
            for (Text val : values) {
                String value = val.toString();
                String[] parts = value.split(":");
                
                if (parts[0].equals("C")) {
                    customerName = parts[1];
                } else if (parts[0].equals("O")) {
                    hasOrder = true;
                }
            }
            
            if (customerName != null && !hasOrder) {
                context.write(new Text("CustomerID: " + key.toString() + ", Name: " + customerName), 
                            NullWritable.get());
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        if (args.length != 3) {
            System.err.println("Usage: CustomersWithNoOrders <customers_input> <orders_input> <output>");
            System.exit(-1);
        }
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Find Customers With No Orders");
        job.setJarByClass(CustomersWithNoOrders.class);
        
        job.setReducerClass(NoOrderReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, CustomerMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, OrderMapper.class);
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}