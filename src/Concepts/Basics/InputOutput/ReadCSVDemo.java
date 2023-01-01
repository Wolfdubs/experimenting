package Concepts.Basics.InputOutput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ReadCSVDemo {
    public static void main(String[] args) {
        Path path = Paths.get(System.getProperty("user.dir"))  //gets pwd of running program
                .resolve("C:/Users/ashmc/OneDrive/Documents/Learning/JavaFiles/symbols_valid_meta.csv");
    //    String path = "C:/Users/ashmc/OneDrive/Documents/Learning/JavaFiles/symbols_valid_meta.csv";
        String currentLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
            while ((currentLine = br.readLine()) != null ){
                String[] values = currentLine.split(",");
                System.out.println("Ticker: " + values[1] + ", Name: " + values[2]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
