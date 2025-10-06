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

public class AvgOrderValuePerCustomer {

    public static class AvgMapper extends Mapper<LongWritable, Text, Text, Text> {
        
        private Text customerId = new Text();
        
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
                String amount = fields[3].trim();
                
                customerId.set(custId);
                context.write(customerId, new Text(amount + ":1"));
                
            } catch (Exception e) {
                System.err.println("Error processing line: " + line);
            }
        }
    }
    
    public static class AvgReducer extends Reducer<Text, Text, Text, DoubleWritable> {
        
        private DoubleWritable result = new DoubleWritable();
        
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) 
                throws IOException, InterruptedException {
            
            double totalAmount = 0.0;
            int orderCount = 0;
            
            for (Text val : values) {
                String[] parts = val.toString().split(":");
                double amount = Double.parseDouble(parts[0]);
                int count = Integer.parseInt(parts[1]);
                
                totalAmount += amount;
                orderCount += count;
            }
            
            if (orderCount > 0) {
                double average = totalAmount / orderCount;
                result.set(average);
                context.write(key, result);
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        if (args.length != 2) {
            System.err.println("Usage: AvgOrderValuePerCustomer <input path> <output path>");
            System.exit(-1);
        }
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Average Order Value Per Customer");
        
        job.setJarByClass(AvgOrderValuePerCustomer.class);
        job.setMapperClass(AvgMapper.class);
        job.setReducerClass(AvgReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}