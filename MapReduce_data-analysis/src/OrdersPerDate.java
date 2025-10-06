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
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class OrdersPerDate {

    public static class DateMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        
        private Text orderDate = new Text();
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
                String date = fields[2].trim();
                
                orderDate.set(date);
                context.write(orderDate, one);
                
            } catch (Exception e) {
                System.err.println("Error processing line: " + line);
            }
        }
    }
    
    public static class DateReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        
        private IntWritable result = new IntWritable();
        
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) 
                throws IOException, InterruptedException {
            
            int count = 0;
            
            for (IntWritable val : values) {
                count += val.get();
            }
            
            result.set(count);
            context.write(key, result);
        }
    }
    
    public static void main(String[] args) throws Exception {
        
        if (args.length != 2) {
            System.err.println("Usage: OrdersPerDate <input path> <output path>");
            System.exit(-1);
        }
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Orders Per Date");
        
        job.setJarByClass(OrdersPerDate.class);
        job.setMapperClass(DateMapper.class);
        job.setReducerClass(DateReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}