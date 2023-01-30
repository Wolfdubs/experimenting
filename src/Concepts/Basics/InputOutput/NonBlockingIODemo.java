package Concepts.Basics.InputOutput;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

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
    }
}














