package pl.kurs.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {


    public static File createFile(final String fileLocation) {
        Path path = Paths.get(fileLocation);
        try {
            Files.createDirectories(path.getParent());
            File file = new File(path.toUri());
            if (file.exists())
                file.delete();
            file.createNewFile();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
