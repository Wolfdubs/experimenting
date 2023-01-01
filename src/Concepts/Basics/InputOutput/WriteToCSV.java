package Concepts.Basics.InputOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteToCSV {
    public static void main(String[] args) throws IOException {


        Path fileIn = Paths.get(System.getProperty("user.dir")).resolve("src/Concepts/Basics/InputOutput/Files")
                .resolve("dataStart.csv");
        Path fileOut = fileIn.resolveSibling("data.csv");

        if (!Files.exists(fileIn)) {
            throw new FileNotFoundException(fileOut.toAbsolutePath().toString());
        }

        BufferedReader br = new BufferedReader(new FileReader(fileIn.toFile()));
        BufferedWriter bf = new BufferedWriter(new FileWriter(fileOut.toFile()));

        //read and write file 1 line per loop
        int count = 0;
        String line = br.readLine();
        while (line!=null){
            count++;
            line = line.toUpperCase();   //change the content in the output
            //write the file
            bf.write(line + "\n");
            bf.flush();

            line = br.readLine();

        }
        br.close();
        bf.close();

    }
}
