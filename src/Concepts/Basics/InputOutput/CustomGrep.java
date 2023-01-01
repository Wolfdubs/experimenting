package Concepts.Basics.InputOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CustomGrep {

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

        }

        br.close(); bw.close(); scanner.close();

    }


}
