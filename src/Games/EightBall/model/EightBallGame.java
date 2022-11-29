package Games.EightBall.model;

import Games.EightBall.model.EightBall;
import utils.ConvertStringToInt;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EightBallGame {

    public static void playEightBallGame(){
        System.out.println("How many questions would you like to ask? Enter as a number.");
        Scanner scanner = new Scanner(System.in);
        int questionCount;
        String questionCountStr;
        int attempts = 8;
        while (attempts>0){
            try {
                questionCount = scanner.nextInt();
                if (questionCount<=10 && questionCount>0)  {
                    System.out.println("That will cost $" + EightBall.calculateCost(questionCount));
                    System.out.println("Do you wish to continue?");
                    Scanner newScanner = new Scanner(System.in);
                    String response = newScanner.nextLine();
                    if ((!response.equals("yes")) && (!response.equals("y"))) {
                        System.out.println("Goodbye");
                        System.exit(0);
                    }
                    EightBall eightBall = new EightBall(questionCount);
                    eightBall.playGame();
                } else {
                    System.out.println("You can only have 1-10 questions.");
                    attempts--;
                }
            } catch (NoSuchElementException e){
                    questionCountStr = scanner.nextLine();
                    int questionConverted = ConvertStringToInt.convertStringToInt(questionCountStr.strip());
                    if (questionConverted!=0) {
                        System.out.println("That will cost $" + EightBall.calculateCost(questionConverted));
                        System.out.println("Do you wish to continue?");
                        Scanner newScanner = new Scanner(System.in);
                        String response = newScanner.nextLine();
                        if ((!response.equals("yes")) && (!response.equals("y"))) {
                            System.out.println("Goodbye");
                            System.exit(0);
                        }
                        EightBall eightBall = new EightBall(questionConverted);
                        eightBall.playGame();
                    } else {
                        System.out.println("You can only have 1-10 questions.");
                        attempts--;
                    }
                }
        }
        System.out.println("You have had 8 chances. You have wasted too much of my time.");
    }
}
