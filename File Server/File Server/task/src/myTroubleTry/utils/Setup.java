package utils;

import java.io.File;
import java.io.IOException;

public class Setup {
    public static String createWorkingDirectory(String path) {
        String filePath = System.getProperty("user.dir") + path;
        File file = new File(filePath);

        if (file.exists()) { return filePath; }
        else if (file.mkdirs()) { return filePath; }
        else { System.out.println("Could not create file path!"); }

        return null;
    }
}
