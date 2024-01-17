package Games.EightBall.model;

import java.util.Random;
import java.util.Scanner;

public class EightBall {

    public int numberOfQuestions;

    private final String[] responses = {"It is certain", "Reply hazy, try again",	"Don’t count on it", "It is decidedly so", "Ask again later",
                          "My reply is no", "Without a doubt", "Better not tell you now", "My sources say no", "Yes definitely",
                          "Cannot predict now",	"Outlook not so good", "You may rely on it", "Concentrate and ask again", "Very doubtful",
                          "As I see it, yes", "Most likely", "Outlook good", "Yes", "Signs point to yes"};

    private final String[] validQuestionStarters = {"should", "can", "will", "do", "is", "would", "are", "may", "shall", "could"};

    public EightBall (int inputNumberOfQuestions){
        this.numberOfQuestions = inputNumberOfQuestions;
    }

    private boolean promptUser(){
        if ( numberOfQuestions > 1 ){
            System.out.println("You have " + numberOfQuestions + " remaining questions to ask. Ask a question.");
        } else {
            System.out.println("You have only " + numberOfQuestions + " remaining question to ask. Ask your final question.");
        }
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase().trim();
        String[] inputArr = input.split(" ", 2);   //splits string in 2, with the first word in [0], and all the rest in [2]
        String inputFirstWord = inputArr[0];
        for (String q : validQuestionStarters) {
            if (inputFirstWord.equals(q) && (input.indexOf("?") == (input.length()-1))){
                //System.out.println("valid question");
                numberOfQuestions--;
                return true;
            }
        }
        System.out.println("That is not a valid question -> Questions must begin with 'should, can, will, do, is, would, are, may, shall, could' and end with '?'");
        return false;
    }

    private String pickAnswer(){
        Random rand = new Random();
        String response = responses[rand.nextInt(responses.length - 1)];
        return response;
    }

    public void playGame(){
        while(this.numberOfQuestions > 0){
            if (promptUser()) {
                System.out.println(pickAnswer() + "\n");
            }
        }
        System.out.println("You are out of questions.");
        System.exit(0);
    }

    public static double calculateCost(int questionCount){
        return questionCount* 0.5;
    }
}
