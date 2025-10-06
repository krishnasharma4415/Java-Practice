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

class NumberSum
{
    public static class SumMapper extends Mapper<LongWritable, Text, Text, LongWritable>
    {
        private Text sumKey = new Text("total");
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
                    context.write(sumKey, number);
                }
                catch (NumberFormatException e)
                {
                }
            }
        }
    }

    public static class SumReducer extends Reducer<Text, LongWritable, Text, LongWritable>
    {
        private LongWritable result = new LongWritable();

        public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException
        {
            long sum = 0;
            for (LongWritable value : values)
                sum += value.get();
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "number sum");
        job.setJarByClass(NumberSum.class);
        job.setMapperClass(SumMapper.class);
        job.setReducerClass(SumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
