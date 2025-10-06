import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MatrixAddition {

    public static class MatrixAddMapper extends Mapper<LongWritable, Text, Text, Text> {
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            String line = value.toString();
            String[] parts = line.split(",");
            
            if (parts.length == 4) {
                String matrixName = parts[0].trim();
                String row = parts[1].trim();
                String col = parts[2].trim();
                String val = parts[3].trim();
                
                // Key: row,col | Value: MatrixName=Value
                String outputKey = row + "," + col;
                String outputValue = matrixName + "=" + val;
                
                context.write(new Text(outputKey), new Text(outputValue));
            }
        }
    }

    public static class MatrixAddReducer extends Reducer<Text, Text, Text, Text> {
        
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) 
                throws IOException, InterruptedException {
            
            double sum = 0;
            
            for (Text val : values) {
                String[] parts = val.toString().split("=");
                if (parts.length == 2) {
                    sum += Double.parseDouble(parts[1]);
                }
            }
            
            context.write(key, new Text(String.valueOf(sum)));
        }
    }

    public static void main(String[] args) throws Exception {
        
        if (args.length != 2) {
            System.err.println("Usage: MatrixAddition <input path> <output path>");
            System.exit(-1);
        }
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Matrix Addition");
        
        job.setJarByClass(MatrixAddition.class);
        job.setMapperClass(MatrixAddMapper.class);
        job.setReducerClass(MatrixAddReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}