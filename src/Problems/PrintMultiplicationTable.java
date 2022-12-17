package Problems;

import java.util.Scanner;

//print full multiplication table for user input integer
public class PrintMultiplicationTable {
    public static void main(String[] args) {
        int number = getUserNumber();
        int[][] table = calculateMultiplicationTable(number);
        printTable(table);
    }

    public static int getUserNumber(){
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        boolean validNumber = false;
        do {
            System.out.println("Please enter a number");
            String numberString = scanner.nextLine();

            try {
                number = Integer.parseInt(numberString);
                validNumber = true;
            } catch (NumberFormatException nfe) {
                System.out.println(numberString + " is not a valid number");
                validNumber = false;
            }
        } while (!validNumber);
        return number;
    }

    public static int[][] calculateMultiplicationTable(int num) {
        int[][] multiplicationTable = new int[num+1][num+1];
        for (int i=1; i<=num; i++) {
            for (int j=1; j<=num; j++){
                multiplicationTable[i][j] = i*j;
            }
        }
        return multiplicationTable;
    }

    public static void printTable(int[][] table) {
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table.length; j++) {
                if (i*j<10)
                    System.out.print(table[i][j] + "   |");
                else if (i*j<100)
                    System.out.print(table[i][j] + "  |");
                else if (i*j<1000)
                    System.out.print(table[i][j] + " |");
                else System.out.print(table[i][j] + "|");
            }
            System.out.println();
            for (int k=0; k<table.length-1; k++)
                System.out.print("----|");
            System.out.println();
        }
    }


}


















