import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

class NumberMax
{
    public static class MaxMapper extends Mapper<LongWritable, Text, Text, LongWritable>
    {
        private Text maxKey = new Text("maximum");
        private LongWritable number = new LongWritable();

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
        {
            String line = value.toString().trim();
            if (!line.isEmpty())
            {
                try
                {
                    long num = Long.parseLong(line);
                    number.set(num);
                    context.write(maxKey, number);
                }
                catch (NumberFormatException e)
                {
                }
            }
        }
    }

    public static class MaxReducer extends Reducer<Text, LongWritable, Text, LongWritable>
    {
        private LongWritable result = new LongWritable();

        public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException
        {
            long max = Long.MIN_VALUE;
            for (LongWritable value : values)
            {
                if (value.get() > max)
                    max = value.get();
            }
            result.set(max);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "number max");
        job.setJarByClass(NumberMax.class);
        job.setMapperClass(MaxMapper.class);
        job.setReducerClass(MaxReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}