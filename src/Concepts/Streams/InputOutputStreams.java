package Concepts.Streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InputOutputStreams {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);   //system.in is an input stream
        int byteData = isr.read();   //just reads in 1 char at a time
        while (byteData != -1) {   //loops reads the chars until it hits the newline that was entered, and so exits
            char c = (char) byteData;
            System.out.println("Byte: " + c);
            byteData = isr.read();

            if ((char) byteData == '\n'){
                System.out.println("Done");
                System.exit(8);
            }
        }


    }
}

class BufferedStreamReading{
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        char[] chars = new char[1000];  //this array is a buffer into which chars can be read. BufferedReaders auto do this setup of buffer arrays and reading in
        isr.read(chars); //reads the chars from isr into the array
        String input = new String(chars);
        input = input.toUpperCase();

        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        osw.write(input);
        osw.flush();   //forces the stream to write its content into console
    }
}
