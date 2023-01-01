package Games;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class LotteryGame {
    public static final int LOTTERY_LENGTH = 6;
    public static final int PRIZE_MONEY_MIN = 1000000;
    public static final int PRIZE_MONEY_MAX = 200000000;
    public static final int LOTTERY_NUMBER_MIN = 1;
    public static final int LOTTERY_NUMBER_MAX = 98;

    public static void main(String[] args) {

        int[] lotteryNumbers = generateLotteryNumbers();
        Arrays.stream(lotteryNumbers).forEach(System.out::println);
        int[] userGuesses = userGuesses();
        compareLotteryNumbers(lotteryNumbers, userGuesses);


    }

    public static int generatePrizeMoney(){
        Random random = new Random();
        int prizeMoney = random.nextInt(PRIZE_MONEY_MIN, PRIZE_MONEY_MAX);
        return prizeMoney;
    }

    public static String generateFormattedPrizeMoney(){
        return NumberFormat.getCurrencyInstance().format(generatePrizeMoney());
    }

    public static int[] generateLotteryNumbers(){
        int[] lotteryNumbers = new int[LOTTERY_LENGTH];
        Random random = new Random();
        for (int i =0; i<LOTTERY_LENGTH; i++){
            lotteryNumbers[i] = random.nextInt(LOTTERY_NUMBER_MAX) +1;
        }
        return lotteryNumbers;
    }

    public static int[] userGuesses(){
        int[] userGuesses = new int[LOTTERY_LENGTH];
        Scanner scanner = new Scanner(System.in);
        for (int i=0; i<LOTTERY_LENGTH; i++){
            boolean validNumber;
            do {
                System.out.println("Your current chosen numbers are");
                Arrays.stream(userGuesses).forEach(n -> System.out.print(n + " "));  System.out.println();
                try {
                    System.out.println("Please enter your choice for number " + (i+1) + " (Valid choices are from 1-99)");
                    String userNumberString = scanner.nextLine();
                    int userNumber = Integer.parseInt(userNumberString);
                    if (userNumber>LOTTERY_NUMBER_MIN && userNumber<LOTTERY_NUMBER_MAX + 1){
                        userGuesses[i] = userNumber;
                        validNumber = true;
                    } else {
                        System.out.println("Try Again: " + userNumber + " is not between 1-99");
                        validNumber = false;
                    }

                } catch (NumberFormatException nfe) {
                    System.out.println("Choice must be a number");
                    validNumber = false;
                }
            } while (!validNumber);

        }
        scanner.close();
        return userGuesses;
    }

    public static boolean compareLotteryNumbers(int[] lottery, int[] user){
        System.out.println("You chose: ");   Arrays.stream(user).forEach(i -> System.out.print(i + " "));
        System.out.println("\nThe correct lottery numbers were: "); Arrays.stream(lottery).forEach(i -> System.out.print(i + " "));
       for (int i = 0; i<LOTTERY_LENGTH; i++){
           if (lottery[i] != user[i]){
               System.out.println("\nYou have not won the lottery. Better luck next time");
               return false;
           }
       }
       System.out.println("\nYou have won the lottery! \nYou have won " + generateFormattedPrizeMoney());
       return true;
    }

}












