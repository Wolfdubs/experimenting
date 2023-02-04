package Concepts.Basics.InputOutput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/*
used to read/write files
Provides multithreading capabilities to IO library, so threads aren't blocked when reading/writing
    Classically, when reading/writing, the thread is blocked until whole file is completed
    Nio library lets you use a thread to read files concurrent with another thread writing files
Classes:
    Buffers = data container
        read() operates on bytestream
        flip() resets index so it returns to the initial position for new operations sequence
    Channel = a wire to the buffer, representing connection to entities capable of performing IO
Reading: channel -> buffer | Writing: buffer -> channel

Uses buffers, channels, selectors
    a thread can ask a channel to read from a buffer, and in the meantime, until the data is ready, go do something else (hence non-blocking)
    This data is being read from a buffer, not the file/resource directly, as it was cached, allowing us to move back and forth to this data
    Channels = gateway between data & buffers that reads/writes data to/from buffers
    Selectors = handle multiple channels for events
        select which channel to use to perform the next IO operation


 */
public class NonBlockingIODemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("dummyFile.txt");
        FileChannel readChannel = fis.getChannel();
        ByteBuffer readByteBuffer = ByteBuffer.allocate(1024);
        int result = readChannel.read(readByteBuffer);   //number of bytes read
        System.out.println("file read successfully " + result);

        FileOutputStream fos = new FileOutputStream("dummyFile.txt");
        FileChannel writeChannel = fos.getChannel();
        ByteBuffer writeByteBuffer = ByteBuffer.allocate(1024);
        String message = "womble is a fluffy pekingese";
        writeByteBuffer.put(message.getBytes());
        writeByteBuffer.flip();
        writeChannel.write(writeByteBuffer);
        System.out.println("File written successfully");


        String file = "dummyFile.txt";
        Files.write(Paths.get(file),
                "womble".getBytes(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);  //file open options: CREATE file if it doesn't exist the 1st attempt to write to it
                                                                        //APPEND data to file, rather than overwrite
        Files.write(Paths.get(file),
                Arrays.asList("womble","mungo","sita"),
                StandardCharsets.UTF_8);   //can specify charset format of data to read/write to file. character encoding helps convert string to bytes correctly

        byte[] bytes = Files.readAllBytes(Paths.get(file));
        System.out.println(new String(bytes));
        
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(file))) {
            System.out.println(bufferedReader.lines().toList());
        }


    }
}














