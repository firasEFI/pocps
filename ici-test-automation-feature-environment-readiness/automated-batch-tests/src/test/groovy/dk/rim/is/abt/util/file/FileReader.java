package dk.rim.is.abt.util.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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
}
