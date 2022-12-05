package Games;
//  need to add the jar files qrgen and zxing-core to the project in intellij
//import net.glxn.qrgen.QRCode;
//import net.glxn.qrgen.image.ImageType;
/*
import java.io.*;

public class QRCodeGenerator {

    public static void main(String[] args) {

        //needed to use extra jar files downloaded from mvn and added to the project
        String stringToQuickResponseify = "This string will become a QR code";
        //create QRCode object, pass in your string, convert it to PNG and then to stream because the "out" object as a ByteArrayOutputSteam will only take a stream
        ByteArrayOutputStream out = QRCode.from(stringToQuickResponseify).to(ImageType.PNG).stream();

        //need a file to write the QR code to
        File file = new File("C:\\Users\\ashmc\\OneDrive\\Documents\\Learning\\JavaOutput\\myNewQRCodeFile.png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(out.toByteArray());   //method to write the data to the fos, but it requires an array of bytes
            fos.flush();
        } catch (FileNotFoundException e) {System.out.println("File wasn't found");}
          catch (IOException e) {System.out.println("Issue with converting out to byte array");}

    }
}
*/