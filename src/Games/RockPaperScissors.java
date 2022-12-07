package Games;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    static int roundsToPlay;   //all declared static so the helper methods outside main can access them
    static int playerScore;
    static int computerScore;
    static int roundsCounter;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("How many rounds would you like to play?");
        Scanner scanner = new Scanner(System.in);
        roundsToPlay = scanner.nextInt();
        roundsCounter = 0;
        playerScore = 0;
        computerScore = 0;

   // while (true) {   //used for the commented out block at bottom where user can continue to request new games
        while (roundsCounter < roundsToPlay && calculateWinner()) {
            String computerMove = computerMove();
            String playerMove = playerMove();
            roundsCounter++;
            Thread.sleep(1000);
            if (computerMove.equals(playerMove)) {
                System.out.println("Tie: Player and Computer both picked " + computerMove + "\n");
            }
            if (playerMove.equals("rock")) {
                if (computerMove.equals("paper")) {
                    System.out.println("Computer chose: " + computerMove);
                    Thread.sleep(1000);
                    System.out.println("Computer wins - Computer's paper beats player's rock\n");
                    computerScore++;
                }
                if (computerMove.equals("scissors")) {
                    System.out.println("Computer chose: " + computerMove);
                    Thread.sleep(1000);
                    System.out.println("Player wins - Player's rock beats computer's scissors\n");
                    playerScore++;
                }
            }
            if (playerMove.equals("paper")) {
                if (computerMove.equals("rock")) {
                    System.out.println("Computer chose: " + computerMove);
                    Thread.sleep(1000);
                    System.out.println("Player wins - Player's paper beats computer's rock\n");
                    playerScore++;
                }
                if (computerMove.equals("scissors")) {
                    System.out.println("Computer chose: " + computerMove);
                    Thread.sleep(1000);
                    System.out.println("Computer wins - Computer's scissors beats player's  paper\n");
                    computerScore++;
                }
            }
            if (playerMove.equals("scissors")) {
                if (computerMove.equals("rock")) {
                    System.out.println("Computer chose: " + computerMove);
                    Thread.sleep(1000);
                    System.out.println("Computer wins - Computer's rock beats players scissors\n");
                    computerScore++;
                }
                if (computerMove.equals("paper")) {
                    System.out.println("Computer chose: " + computerMove);
                    Thread.sleep(1000);
                    System.out.println("Player wins - Player's scissors beats computer's  paper\n");
                    playerScore++;
                }
            }
        }
        if (playerScore > computerScore) {
            System.out.println("\nPlayer score = " + playerScore + "\nComputer score = " + computerScore + "\nPlayer wins");
        } else if (computerScore > playerScore) {
            System.out.println("\nPlayer score = " + playerScore + "\nComputer score = " + computerScore + "\nComputer wins");
        } else System.out.println("\nPlayer score = " + playerScore + "\nComputer score = " + computerScore + "\nTie");

        scanner.close();   //should always close to prevent resource leak
        /*
        System.out.println("Would you like to play another round? (y/n)");
        Scanner scanner1 = new Scanner(System.in);
        String playAgain = scanner1.nextLine();
        if (playAgain!="yes"){
            break;
        }*/

    }



    private static String computerMove(){
        //random computer move
        String[] moves = {"rock", "paper", "scissors"};
        return moves[new Random().nextInt(moves.length)];
    }

    private static String playerMove() throws InterruptedException {      //accept move from player
        Scanner scanner = new Scanner(System.in);
        String playerMove;
        while(true){
            Thread.sleep(1000);
            System.out.println("Please choose rock, paper or scissors");
            playerMove = scanner.nextLine();
            if (playerMove.equals("rock") || playerMove.equals("paper") || playerMove.equals("scissors")){
                break;  //will keep looping if user enters invalid input
            }
            System.out.println(playerMove + " is invalid for rock, paper, scissors");
        } return playerMove.toLowerCase();
    }

    private static boolean calculateWinner(){
        if (playerScore>roundsToPlay/2){
            System.out.println("Player wins with " + playerScore + " wins out of " + roundsCounter + " rounds so far, out of " + roundsToPlay + " possible rounds");
            return false;
        }
        else if (computerScore>roundsToPlay/2){
            System.out.println("Computer wins with " + computerScore + " wins out of " + roundsCounter + " rounds so far, out of " + roundsToPlay + " possible rounds");
            return false;
        }
        else return true;
    }
}
