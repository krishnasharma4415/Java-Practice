import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URI;

public class LocalToHDFS {
    public static void main(String[] args) {
        // Check arguments
        if (args.length != 2) {
            System.out.println("Usage: LocalToHDFS <local_file_path> <hdfs_destination_path>");
            System.exit(1);
        }

        String localFilePath = args[0];
        String hdfsDestPath = args[1];

        // HDFS URI (adjust according to your setup)
        String hdfsUri = "hdfs://localhost:9000";

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", hdfsUri);

        try {
            FileSystem fs = FileSystem.get(new URI(hdfsUri), conf);

            // Input stream for local file
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(localFilePath));

            // Output stream to HDFS
            OutputStream outputStream = fs.create(new Path(hdfsDestPath));

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File successfully copied to HDFS: " + hdfsDestPath);

            fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
