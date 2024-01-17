package Games.HangMan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangMan {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Do you want to play 1-Player or 2-Player mode? (1/2)");
        Scanner scannerPlay = new Scanner(System.in);
        String playerCount = scannerPlay.nextLine();
        String chosenWord ="";
        if (playerCount.equals("1")) {     //if only 1 player, generate the word for the words.txt file
            List<String> words = generateWordList();          //pull the words from the file and store in a list
            chosenWord = pickRandomWord(words);               //have hangman game pick a random words from the list
        }
        else {
            System.out.println("Player 1, enter your word");   //if not 1 player, let the 1st player enter a word in the console
            chosenWord = (scannerPlay.nextLine());
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Player 2, start guessing");
        }
        int wrongCount = 0;
        List<Character> lettersGuessed = new ArrayList<>();
        while (wrongCount<=6 ) {
            printHangMan(wrongCount);
            printWordState(chosenWord,lettersGuessed);
            if (!playerGuess(lettersGuessed, chosenWord)) {  //if the player guess did not have a letter inside the word, increment wrongcount
                wrongCount++;
            }
            if(printWordState(chosenWord, lettersGuessed)){   //if this returns true, then the player has guessed all the letters correctly, so exit loop
                System.out.println("You win");
                break;}
            if (guessWord(chosenWord)) break;              //let user make a guess at the full word
        }
        System.out.println("You have been HANGED\n The word was " + chosenWord);


    }

    private static void printHangMan(int wrongCount){
        System.out.println(" ------- ");
        System.out.println(" |     |");
        if (wrongCount>0)
            System.out.println(" O");
        if (wrongCount>1)
            System.out.println(" |");
        if (wrongCount>2) {
            System.out.print("\\ ");
            if (wrongCount>3)
                System.out.println(" /");
            else System.out.println();
        }
        if (wrongCount>4)
            System.out.println(" |");
        if (wrongCount>5) {
            System.out.print("/");
            if (wrongCount > 6)
                System.out.println(" \\");
            else System.out.println();
        }
    }

    private static boolean guessWord(String chosenWord) {
        System.out.println("Enter guess for the full word");
        Scanner scanner2 = new Scanner(System.in);
        if(scanner2.nextLine().equals(chosenWord)){
            System.out.println("You win");
            return true;
        } else {
            System.out.println("Incorrect guess");
        }
        return false;
    }

    private static boolean playerGuess(List<Character> lettersGuessed, String word) {
        System.out.println("Guess a letter in the word");
        Scanner scanner1 = new Scanner(System.in);
        String guessString = scanner1.nextLine();
        char guess = guessString.charAt(0);
        lettersGuessed.add(guess);
        return word.contains(guessString);    //returns true if the player made a correct letter guess that is inside the chosenWord
    }

    private static boolean printWordState(String chosenWord, List<Character> lettersGuessed){
        int correctLetterCounter = 0; //counter to track how many letters the user has guessed correctly
        for (char c : chosenWord.toCharArray()) {
            if (lettersGuessed.contains(c)){
                System.out.print(c);
                correctLetterCounter++;
            } else System.out.print("_");
        }
        System.out.println();
        return correctLetterCounter==chosenWord.length();   //if the number of correct letters guessed matches the length of the word, then player wins
    }


    private static String pickRandomWord(List<String> words) {
        Random random = new Random();
        int chosenWordIndex = random.nextInt(words.size());
        String chosenWord = words.get(chosenWordIndex);
        return chosenWord;
        //    System.out.println(chosenWord + " is at " + chosenWordIndex);
    }

    private static List<String> generateWordList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/Games/HangMan/words.txt"));
        List<String> words = new ArrayList<>();
        while (scanner.hasNext()){
            words.add(scanner.next());
        }
        return words;
    }

}
