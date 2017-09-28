package dk.rim.is.abt.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Created by aso on 12-05-2017.
 * This small utility reads in a file and prints the bytes as a string. It is used to prepare test data for testing.
 */
public class Byter {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\aso\\Pictures\\u4gnaXC.jpg"); //the path to your file
        byte[] data = Files.readAllBytes(path);
        String bytes = Base64.getEncoder().encodeToString(data);

        System.out.println("-----------------------Your Bytes Here-----------------------");
        System.out.println(bytes);
        System.out.println("-----------------------Your Bytes Here-----------------------");
    }

    public static byte[] toByteArray(InputStream resourceStream) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] result = new byte[]{};
        int nRead;
        byte[] data = new byte[1000];
        try {
            while ((nRead = resourceStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            //String bytes = Base64.getEncoder().encodeToString(buffer.toByteArray());
            //we have a String of our encoded bytes, close reader/writer streams
            result = buffer.toByteArray();
            buffer.close();
            resourceStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    /*public static byte[] getBytesFromInputStream(InputStream iStream) {
        byte[] bytes;
        byte[] byteArray;
        while ((iStream.read(bytes)) > 0) {
            byteArray += bytes;
        }
        return byteArray;
    }*/
}
