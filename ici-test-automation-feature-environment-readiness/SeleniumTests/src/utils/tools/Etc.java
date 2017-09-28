package utils.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Etc {
    public static String generateNDigitNumberAsString(int n) {
        char[] id = new char[n];
        Random rand = new Random();

        for (int i = 0; i < id.length; i++) {
            id[i] = Character.forDigit(rand.nextInt(10), 10);
        }

        return String.valueOf(id);
    }

    public static File createFile(String filePath, String fileName, String fileContent) throws IOException {
        // This creates a basic file somewhere on the computer
        // try {
        // Save file content as a file
        String prefix = (filePath.charAt(filePath.length() - 1) == '\\') ? "" : "\\";
        File newTextFile = new File(filePath + prefix + fileName);

        FileWriter fw = new FileWriter(newTextFile);
        fw.write(fileContent);
        fw.close();

        return newTextFile;
        //
        // } catch (IOException iox) {
        // //do stuff with exception
        // iox.printStackTrace();
        // throw iox;
        // }
    }
    
}
