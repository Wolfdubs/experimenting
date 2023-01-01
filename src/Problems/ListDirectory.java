package Problems;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class ListDirectory {
    public static void main(String[] args) {
        Path workingDir = Paths.get(System.getProperty("user.dir"));
        DirectoryStream<Path> paths = null;
        try {
            paths = Files.newDirectoryStream(workingDir);
            for (Path path : paths) {
                System.out.println(path.toAbsolutePath());
                BasicFileAttributes bfa = Files.readAttributes(path, BasicFileAttributes.class);
                System.out.println(" Is directory -> " + bfa.isDirectory());
                System.out.println(" Is dile -> " + bfa.isRegularFile());
                System.out.println(" Creation time -> " + bfa.creationTime());
                System.out.println();
            }

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
