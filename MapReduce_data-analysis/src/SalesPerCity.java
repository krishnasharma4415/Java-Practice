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
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SalesPerCity {

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

    public static void main(String[] args) throws Exception {

        if (args.length != 4) {
            System.err.println("Usage: SalesPerCity <customers_input> <orders_input> <temp_output> <final_output>");
            System.exit(-1);
        }

        Configuration conf1 = new Configuration();
        Job job1 = Job.getInstance(conf1, "Join Customers and Orders");
        job1.setJarByClass(SalesPerCity.class);

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
        job2.setJarByClass(SalesPerCity.class);
        job2.setMapperClass(CityAggregateMapper.class);
        job2.setReducerClass(CityAggregateReducer.class);
        job2.setOutputKeyClass(Text.class);
        job2.setOutputValueClass(DoubleWritable.class);

        FileInputFormat.addInputPath(job2, new Path(args[2]));
        FileOutputFormat.setOutputPath(job2, new Path(args[3]));

        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}