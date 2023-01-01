package Problems;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class FindNewestFile {
    public static void main(String[] args) throws IOException {
        Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(currentDirectory);

        long newest = 0L;

        for (Path p : directoryStream){
            BasicFileAttributes bfa = Files.readAttributes(currentDirectory, BasicFileAttributes.class);
            System.out.println("Iterating on: " + currentDirectory.toAbsolutePath());

            long creationTime = bfa.creationTime().toMillis();

            if (creationTime>newest){
                newest = creationTime;
            }
        }
        Date date = new Date(newest);
        System.out.printf("The last file was created on %s", date);
    }
}
