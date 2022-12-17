package Games;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static char userChar;
    public static char computerChar;
    Scanner scanner = new Scanner(System.in);


    //need to generate the board each time and print it out
    //need to take user input and add their move to the board
    //need a computer opponent who can make a move
    //determine when game is over; either a 3-in-a-row or full board
    public static void main(String[] args) throws InterruptedException {
        char[][] board = {{' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}};

        userChar = userChoiceOfChar();
        do {
            takeUserMove(board, userChar);
            printBoard(board);
            if(isGameOver(board))
                break;
            computerMove(board);
            printBoard(board);
            if(isGameOver(board))
                break;
        } while (!isBoardFull(board));
    }


    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
                if ((j + 1) % 3 != 0)
                    System.out.print("|");
            }
            if ((i + 1) % 3 != 0)
                System.out.println("\n-+-+-");
        }
        System.out.println();
    }

    public static char userChoiceOfChar() {
        String userString;
        char userCharChoice;
        do {
            System.out.println("\nWould you like to be X or O");
            Scanner scanner = new Scanner(System.in);
            userString = scanner.nextLine();
            char[] userStringAsChar = userString.toUpperCase().toCharArray();
            userCharChoice = userStringAsChar[0];
        } while (!userString.equals("x") && !userString.equals("X") && !userString.equals("O") && !userString.equals("o"));
        System.out.println("You have chosen to play as " + userCharChoice);
        if (userString.equals("X") || userString.equals("x")) {
            computerChar = 'O';
        } else computerChar = 'X';

        return userCharChoice;
    }

    //assign a number corresponding to each index of the board 1-9, that the user can specify as their spot to move to

    public static void takePlayerMove(char[][] board, int playerChoice, char symbol) {
        switch (playerChoice) {
            case 1 -> board[0][0] = symbol;
            case 2 -> board[0][1] = symbol;
            case 3 -> board[0][2] = symbol;
            case 4 -> board[1][0] = symbol;
            case 5 -> board[1][1] = symbol;
            case 6 -> board[1][2] = symbol;
            case 7 -> board[2][0] = symbol;
            case 8 -> board[2][1] = symbol;
            case 9 -> board[2][2] = symbol;
        }

    }
    public static void takeUserMove(char[][] board, char userChar) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;
        int printUserMoveWasInvalidCounter = 0;
        do {
            System.out.println("\nWhere would you like to place?");
            userChoice = scanner.nextInt();
            if (printUserMoveWasInvalidCounter > 1){
                System.out.println("Position " + userChoice + " is invalid");
            }
            printUserMoveWasInvalidCounter++;

        } while (!isValidMove(board, userChoice));

        takePlayerMove(board, userChoice, userChar);
    }

    public static void computerMove(char[][] board) throws InterruptedException {
        //  int[] openSpaces = new int[9];
        Thread.sleep(1000);
        System.out.println("\nComputer will now make a move:");
        Random random = new Random();
        int compChoice;
        while (true){
            compChoice = random.nextInt(9) + 1;    //to get number 1-9
            if (isValidMove(board, compChoice)){       //makes sure the number the computer generated is in open spot
                break;
            }
        }
        System.out.println("Computer chose " + compChoice);
        takePlayerMove(board, compChoice, computerChar);

    }

    public static boolean isValidMove(char[][] board, int choiceSpot){
        switch (choiceSpot) {
            case 1 -> {return board[0][0] == ' ';}
            case 2 -> {return board[0][1] == ' ';}
            case 3 -> {return board[0][2] == ' ';}
            case 4 -> {return board[1][0] == ' ';}
            case 5 -> {return board[1][1] == ' ';}
            case 6 -> {return board[1][2] == ' ';}
            case 7 -> {return board[2][0] == ' ';}
            case 8 -> {return board[2][1] == ' ';}
            case 9 -> {return board[2][2] == ' ';}
            default -> {return false;}
        }
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        System.out.println("The board is now full: Game ends in a TIE");
        return true;
    }

    private static boolean isGameOver(char[][] board) {
        if (isThereThreeInARow(board))
            return true;
        else return (isBoardFull(board));

    }

    private static boolean isThereThreeInARow(char[][] board) {
        for (int i = 0; i<board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //Checking for 3 in a row across a row
                if (board[i][0] == userChar && board[i][1] == userChar && board[i][2] == userChar) {   //checking if any row is 3 in a row for user
                    System.out.println("User has 3 in a row: USER WINS");
                    return true;
                }
                if (board[i][0] == computerChar && board[i][1] == computerChar && board[i][2] == computerChar) {   //checking if any row is 3 in a row for user
                    System.out.println("Computer has 3 in a row: COMPUTER WINS");
                    return true;
                }
                //Checking for 3 in a row through a column
                if (board[0][j] == userChar && board[1][j] == userChar && board[2][j] == userChar) {   //checking if any row is 3 in a row for user
                    System.out.println("User has 3 in a row: USER WINS");
                    return true;
                }
                if (board[0][j] == computerChar && board[1][j] == computerChar && board[2][j] == computerChar) {   //checking if any row is 3 in a row for user
                    System.out.println("Computer has 3 in a row: COMPUTER WINS");
                    return true;
                }
            }
        }
        //Checking for 3 in a row diagonally; 1,5,9 | 3,5,7
        if ((board[0][0]==userChar && board[1][1]==userChar && board[2][2]==userChar)
            || (board[0][2]==userChar && board[1][1]==userChar && board[2][0]==userChar)) {
            System.out.println("User has 3 in a row: USER WINS");
            return true;
        }
        if ((board[0][0]==computerChar && board[1][1]==computerChar && board[2][2]==computerChar)
                || (board[0][2]==computerChar && board[1][1]==computerChar && board[2][0]==computerChar)) {
            System.out.println("Computer has 3 in a row: COMPUTER WINS");
            return true;
        } else return false;

    }


}
