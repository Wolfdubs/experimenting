package Concepts.Serialization;

import java.io.*;

/*
Serialization = for saving object state to a file (txt, data, xml), which you can then create new objects from
strongly recommended that all serializable classes explicitly declare serialVersionUID values, since the default serialVersionUID computation
is highly sensitive to class details that may vary depending on compiler implementations, and can thus result in unexpected
InvalidClassExceptions during deserialization

Vs Cloning: Object cloning is creating a copy of an object and you don't store it and use right way in the code .
Object serialisation is copying and also storing object ( may be in a file or database or over a network )
    and then you can retrieve at later point of time . It is not possible in case of cloning
 */
public class SerializationClass {
    private static final long serialVersionUID = 4L;  //explicit declaration of serialVersionUID to prevent deserialization problems


    //for storing the state of values within an object in a file, rather than the whole object itself
    //store state of objects in a file
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        //creating object to serialize
        Demo demo = new Demo();
        demo.setI(9);

        File f = new File("SerializationFile.txt");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);   //object specific stream for output
        oos.writeObject(demo);   //passing the object output stream the specific object to store in the input filepath


        //now can create new objects using the values stored in the file
        FileInputStream fis = new FileInputStream(f);         //retrieve data from file
        ObjectInputStream ois = new ObjectInputStream(fis);
        Demo newDemo = (Demo) ois.readObject();    //readObject returns Object type, so must cast it to specific object subtype
    }
}

//saving objects is by default not allowed as is insecure, so must explicitly say class implements Serializable
class Demo implements Serializable {    //Serializable allows class to be saved. Is a market interface, so no empty methods to override
    private int i;
    public void setI(int i) {this.i = i;}
}
