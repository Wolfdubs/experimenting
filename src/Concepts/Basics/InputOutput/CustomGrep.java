package Concepts.Basics.InputOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//The Linux grep command is a string and pattern matching utility that displays matching lines from multiple files
public class CustomGrep {  //With grep, you can perform simple searches, recursive searches, search for whole words, use multiple
                        // search terms, count matches, add context, and even pipe the output to other commands for further manipulation

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("Enter file name to write grep output to");
        String GrepOutputFile = scanner.nextLine();
        Path fileInput = Paths.get(System.getProperty("user.dir"))
                .resolve("src/Concepts/Basics/InputOutput/Files").resolve("dataStart.csv");
        Path fileOutput = fileInput.resolveSibling(GrepOutputFile);
        if (!Files.exists(fileInput)){
            throw new FileNotFoundException(fileInput.toAbsolutePath().toString());
        }

        BufferedReader br = new BufferedReader(new FileReader(fileInput.toFile()));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileOutput.toFile()));

        System.out.println("Enter regex to search input file via regex");
        String grep = scanner.nextLine();

        String line = br.readLine();
        while (line!=null) {
            if (line.equals(grep)){
                bw.write(line);
            }
        }

        br.close(); bw.close(); scanner.close();

    }


}
