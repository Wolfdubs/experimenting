package Concepts.Serialization;

import java.io.*;
import java.sql.SQLOutput;

/*
Serialization = Converting java objects into bytes to transfer over a network (to reach applications deployed on other machines)
Data can only travel the network as bytes, so must;
    convert java objects into bytes -> send over network -> transform back into objects (deserialization)

Can only write objects to a file via serialization:
used when saving object state to a file (txt, data, xml), which you can then create new objects from
strongly recommended that all serializable classes explicitly declare serialVersionUID values, since the default serialVersionUID computation
is highly sensitive to class details that may vary depending on compiler implementations, and can thus result in unexpected
InvalidClassExceptions during deserialization

Vs Cloning: Object cloning is creating a copy of an object and you don't store it and use right way in the code .
Object serialization is copying and also storing object ( may be in a file or database or over a network )
    and then you can retrieve at later point of time . It is not possible in case of cloning

 */
public class SerializationDemo {



    //for storing the state of values within an object in a file, rather than the whole object itself
    //store/writing state of objects in/to a file
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {

        //creating object to serialize
        Demo demo = new Demo();
        demo.setI(9);


        File f = new File("SerializationFile.txt");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);   //object specific stream for output
            oos.writeObject(demo);   //passing the object output stream the specific object to store in the input filepath to persist the state of the object passed
            oos.close();
            fos.close();
            System.out.println("Object has been serialized" + demo);
        } catch (IOException ioe) {
            System.out.println("IOException occurred");
        }


        //now can create new objects using the values stored in the file
        try {
            FileInputStream fis = new FileInputStream(f);         //retrieve data from file
            ObjectInputStream ois = new ObjectInputStream(fis);
            Demo newDemo = (Demo) ois.readObject();    //readObject returns Object type, so must cast it to specific object subtype
            newDemo.printValues();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

//saving objects is by default not allowed as is insecure, so must explicitly say class implements Serializable
class Demo implements Serializable {    //Serializable allows class to be saved. Is a marker interface, so no empty methods to override. only classes implementing Serializable can be serialized

    //serialVersionID is so java can uniquely identify the object once deserialized and ensure the same version sent is the one received(secure)
    @Serial            //tracks the object state, as every serialization increments the count by 1
    private static final long serialVersionUID = 4L;  //explicit declaration of serialVersionUID to prevent deserialization problems

    private int i;
    public void setI(int i) {this.i = i;}

    int a = 10;
    final int b = 20;
    transient int c = 30; //transient specifies members to exclude from serialization, so it won't be sent
                    /*indicates variable is not part of the default serialized form of the object / the objects persisted state
                    When objects are serialized, transient instance fields are ignored
                    when objects deserialized, transient instance fields are initialized to their default value, e.g. for int it will be 0 */
    transient static int d = 40; //using static with transient -> negates the transient, because static fields are part of the class,
                                 //while transient only operate sat the object state level
    transient final int e = 50;  //using final with transient -> final fields are directly serialized by their initial value so cannot be reinitialized

    //values written to the file will be: 10, 20, 30, 40, 50
    //values retrieved from the file will be: 10, 20, 0, 40, 50

    void printValues(){
        System.out.printf("a = %d, b = %d, c = %d, d = %d, e = %d", a,b,c,d,e);;
    }
}
