package Concepts.Basics.InputOutput;

import java.io.*;

/*
I/O Stream = have input source and destination, with data send as binary stream
    input stream = program reads data from data source in binary stream
    output stream = program writes data to a destination 1 line at a time
2 stream types:
    Character Stream = when data is in characters. optimized for character files, that are read character by character by FileReader / FileWriter
    Byte Stream = for all other data types. file read/written byte by byte by FileInputStream / FileOutputStream
 */
public class InputOutputDemo {
}

class ByteStreamDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("dummySource.txt");
            fos = new FileOutputStream("dummyDestination.txt");
            //read 1 byte at a time. once at end of file, return -1
            int currentByte;
            while ((currentByte = fis.read()) != -1) {   //stores output of fis.read into currentByte
                fos.write((byte) currentByte);   //fis.read returns an int so must case to byte
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (fis != null) fis.close();
            if (fos != null) fos.close();
        }
    }
}

class characterStreamDemo {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader("dummySource.txt");
            fileWriter = new FileWriter("dummyDestination.txt");

            int currentCharacter;
            while ((currentCharacter = fileReader.read()) != -1) {
                fileWriter.write((char) currentCharacter);   //writing content char by char
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (fileReader != null) fileReader.close();
            if (fileWriter != null) fileWriter.close();
        }
    }
}
