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

    public class TotalSalesPerCustomer {

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
        
        public static void main(String[] args) throws Exception {
            
            if (args.length != 2) {
                System.err.println("Usage: TotalSalesPerCustomer <input path> <output path>");
                System.exit(-1);
            }
            
            Configuration conf = new Configuration();
            Job job = Job.getInstance(conf, "Total Sales Per Customer");
            
            job.setJarByClass(TotalSalesPerCustomer.class);
            job.setMapperClass(SalesMapper.class);
            job.setReducerClass(SalesReducer.class);
            
            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(DoubleWritable.class);
            
            FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
            
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }
    }