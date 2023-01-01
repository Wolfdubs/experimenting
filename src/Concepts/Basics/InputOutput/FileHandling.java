package Concepts.Basics.InputOutput;

import java.io.*;

public class FileHandling {

    public static void main(String[] args) throws FileNotFoundException, IOException {

    //Write data to a file
    File f = new File("myFile.txt");
    FileOutputStream fos = new FileOutputStream(f) ;
    DataOutputStream dos = new DataOutputStream(fos);
    dos.writeUTF("Lines to write to my file");

    //Read data from a file
    FileInputStream fis = new FileInputStream(f);
    DataInputStream dis = new DataInputStream(fis);
    String fileContents = dis.readUTF();
    System.out.println(fileContents);

    }
}
