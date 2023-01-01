package Games;

import java.util.Arrays;
import java.util.Scanner;

public class MessageEncrypter {

    public static void main(String[] args) {
        encrypt();
    //    decrypt();
    }

    public static void encrypt(){
        System.out.println("Enter message to encrypt");
        Scanner scanner = new Scanner(System.in);
        String originalString = scanner.nextLine();
        char[] original = originalString.toCharArray();
        char[] encrypted = new char[original.length];

        System.out.println("Enter key to encrypt with");
        int key = scanner.nextInt();

        for (int i = 0; i<original.length; i++) {
            char c = original[i];
            c+=key;
            encrypted[i] = c;
            System.out.print(c);
        }
       // System.out.println(Arrays.toString(encrypted));
        scanner.close();
    }

    public static void decrypt(){
        System.out.println("Enter message to decrypt");
        Scanner scanner = new Scanner(System.in);
        String originalString = scanner.nextLine();
        char[] original = originalString.toCharArray();
        char[] decrypted = new char[original.length];

        System.out.println("Enter key to decrypt with");
        int key = scanner.nextInt();

        for (int i = 0; i<original.length; i++) {
            char c = original[i];
            c-=key;
            decrypted[i] = c;
        }
        System.out.println(Arrays.toString(decrypted));
        scanner.close();
    }
}
