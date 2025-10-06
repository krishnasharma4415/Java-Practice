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
import java.util.HashMap;
import java.util.Map;

public class MatrixMultiplication {

    public static class MatrixMultMapper extends Mapper<LongWritable, Text, Text, Text> {
        
        private int m = 3;
        private int p = 3; 
        
        @Override
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException {
            
            String line = value.toString();
            String[] parts = line.split(",");
            
            if (parts.length == 4) {
                String matrixName = parts[0].trim();
                int row = Integer.parseInt(parts[1].trim());
                int col = Integer.parseInt(parts[2].trim());
                String val = parts[3].trim();
                
                if (matrixName.equals("A")) {
                    for (int k = 0; k < p; k++) {
                        String outputKey = row + "," + k;
                        String outputValue = "A," + col + "," + val;
                        context.write(new Text(outputKey), new Text(outputValue));
                    }
                } else if (matrixName.equals("B")) {
                    for (int i = 0; i < m; i++) {
                        String outputKey = i + "," + col;
                        String outputValue = "B," + row + "," + val;
                        context.write(new Text(outputKey), new Text(outputValue));
                    }
                }
            }
        }
    }

    public static class MatrixMultReducer extends Reducer<Text, Text, Text, Text> {
        
        @Override
        public void reduce(Text key, Iterable<Text> values, Context context) 
                throws IOException, InterruptedException {
            
            Map<Integer, Double> aValues = new HashMap<>();
            Map<Integer, Double> bValues = new HashMap<>();
            
            for (Text val : values) {
                String[] parts = val.toString().split(",");
                if (parts.length == 3) {
                    String matrix = parts[0];
                    int index = Integer.parseInt(parts[1]);
                    double value = Double.parseDouble(parts[2]);
                    
                    if (matrix.equals("A")) {
                        aValues.put(index, value);
                    } else if (matrix.equals("B")) {
                        bValues.put(index, value);
                    }
                }
            }
            
            double sum = 0;
            for (Map.Entry<Integer, Double> entry : aValues.entrySet()) {
                int idx = entry.getKey();
                if (bValues.containsKey(idx)) {
                    sum += entry.getValue() * bValues.get(idx);
                }
            }
            
            context.write(key, new Text(String.valueOf(sum)));
        }
    }

    public static void main(String[] args) throws Exception {
        
        if (args.length != 2) {
            System.err.println("Usage: MatrixMultiplication <input path> <output path>");
            System.exit(-1);
        }
        
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Matrix Multiplication");
        
        job.setJarByClass(MatrixMultiplication.class);
        job.setMapperClass(MatrixMultMapper.class);
        job.setReducerClass(MatrixMultReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}