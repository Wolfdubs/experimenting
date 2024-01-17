package Concepts;

import java.io.*;
import java.util.Properties;

//inbuilt class in java
//used to define variables for things like connecting to DB and store in a file, mean you can fetch the elements later
//Property class extends HashTable so provides KV pairs
//used for hibernate framework
public class PropertiesClass {

    public static void main(String[] args) throws IOException {

        Properties p = new Properties();
        /*  have already run this and created the named file
        //must create file for properties to be saved inside
        OutputStream os = new FileOutputStream("dataConfig.properties");   //OutputStream is abstract class, FOS is concrete. Output could be .txt too
        p.setProperty("url","localhost:3306/myDb");       //default port number for mySQL
        p.setProperty("username", "Telusko");
        p.setProperty("password", "0923j");
        p.store(os, null);   //to push the properties into the file
    */
        //to fetch values from properties file
        InputStream is = new FileInputStream("dataConfig.properties");
        //load the property file
        p.load(is);    //loads all the data from the property file into the p object
        System.out.println(p.getProperty("url"));
        p.list(System.out);

    }
}

