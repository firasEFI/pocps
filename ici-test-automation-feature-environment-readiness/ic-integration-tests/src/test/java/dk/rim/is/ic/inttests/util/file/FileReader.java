package dk.rim.is.ic.inttests.util.file;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.ClassLoader.getSystemClassLoader;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Radoslaw Domanski (rdo)
 * @since 31.01.2017
 */
public class FileReader {
    private FileReader() {
    }

    public static String readFile(String path) throws IOException {
        byte[] encoded = readByteFile(path);
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public static byte[] readByteFile(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
    
    public static String getResourceFileAsString(String filePath) throws IOException {
        return IOUtils.toString(getSystemClassLoader().getResourceAsStream(filePath), UTF_8);
    }
}
