package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    public static void main(String[] args) {
        calculateCircleArea();
    }

    public static boolean printArray(int[] arr){
        int[] printableArray = new int[10];
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i] + ",");
        }
        return true;
    }

    public static <T> void swap(T a, T b){
        T temp = a;
        a = b;
        b = temp;
    }

    public static double calculateCircleArea(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter circle radius");
        boolean validInput = false;
        double radius = 0;
        while (!validInput) {
            try {
                String radiusString = scanner.nextLine();
                radius = Double.parseDouble(radiusString);
                validInput = true;
                scanner.close();
            } catch (NumberFormatException | InputMismatchException ime) {
                System.out.println("Enter a valid number");
                validInput = false;
            }
        }
        double area = Math.PI * Math.pow(radius, 2);
        String areaFormatted = new DecimalFormat("0.00").format(area);
        System.out.println("Circle area = " + areaFormatted);
        return area;
    }
}

class GenericSwap<T>{
    public <T> void swap(T a, T b){
        T temp = a;
        a = b;
        b = temp;
    }
}
