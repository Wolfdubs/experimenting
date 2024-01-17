package Concepts.Basics.InputOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileReaderWriter {
    /*classic way to read/write files in Java
    Steps
        1. Instantiate them
        2. Specify the file path
        3. use read() or write()
            write() accepts a boolean; true = append the data to a file, false = or overwrite existing content
            read() by default only reads 1 char at a time
                to read multiple chars at a time, use overloaded read() and pass in exact amount of chars to read
     */

    public static void main(String[] args) throws IOException {


        File file = new File("src\\Concepts\\Basics\\InputOutput\\Files\\dummyFile.txt");
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write("womble");
        }
        try (FileReader fileReader = new FileReader(file)) {
            System.out.println(fileReader.read());
        }
        try (FileReader fileReader = new FileReader(file)) {
            char[] chars = new char[(int) file.length()];
            fileReader.read(chars);    //specifying for the fileReader to read all the chars at once
            System.out.println(new String(chars));
        }
    }

    class BufferedReaderWriter{
        /*
        Used to wrap the FileReader & FileWriter classes because they provide a buffer for the file reader & writer objects (which can process only 2-4
         bytes at a time), and mean you don't have to specify exactly how many chars to read
         BufferedReader = stores the bytes read by FileReader in a buffer (a special location in RAM)
            When the FileReader hits /n, the BufferedReader returns the output stored in the buffer
            Gives access to many new methods
                readLine() = not limited to read() of just 1 char at a time
         */
        public static void main(String[] args) throws IOException {
            File file = new File("dummyFile.txt");
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.write("womble");
            }
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
    }

    class readWriteString {
        //alt way to read/write files
        //useful for reading/writing small files into string object directly, and vice versa
        public static void main(String[] args) throws IOException {
            String file = "src\\Concepts\\Basics\\InputOutput\\Files\\readWriteStringFile.txt";
            Files.writeString(Paths.get(file), "womble", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            String content = Files.readString(Paths.get(file));
            System.out.println(content);
        }
    }

}
