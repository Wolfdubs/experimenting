package Problems;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileDirectoryTreeTraversal {
    public static void main(String[] args) {
        Path currentPath = Paths.get(System.getProperty("user.dir"));  //gets pwd of running program
        try {
            listDir(currentPath, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //recursive method to traverse file tree structure. pass in the tree and current depth
    public static void listDir(Path path, int depth) throws IOException {
        BasicFileAttributes bfa = Files.readAttributes(path, BasicFileAttributes.class);
        if (bfa.isDirectory()) {  //if it's a directory, list the files and traverse down the tree via directory stream
            DirectoryStream<Path> paths = Files.newDirectoryStream(path);
            System.out.println(spacesForDepth(depth) + ">>" + path.getFileName());//printing out directory name
            for (Path currentPath : paths){
                listDir(currentPath, depth+1);   //without each folder inside the directory, keep calling it recursively
            }
        } else{  //printing out file names
            System.out.println(spacesForDepth(depth) + "--" + path.getFileName());  //print the depth
        }
    }

    //for formatting of printing the directory
    public static String spacesForDepth(int depth){
        StringBuilder sb = new StringBuilder(); //lets you append pieces of a string together and at the end it concatenates them into 1 string more efficiently than concatenating strings as it is mutable
        for (int i=0; i<depth; i++){
            sb.append("  ");
        }
        return sb.toString();
    }

}
