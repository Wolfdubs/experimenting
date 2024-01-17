package Games.ScrabbleSolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class ScabbleSolver {

    //returns all possible words that can be made from user input string
    public static void main(String[] args) {

        System.out.println("Enter your mix of letters");
        Scanner scanner = new Scanner(System.in);
        String userLetters = scanner.nextLine().toUpperCase();

        //iterate through the string and count the occurrence of every character
        Map<Character, Integer> userLetterIntegerMap = getCharacterIntegerMap(userLetters);

        //iterate through the dictionary word list, and for each word, see if the hashmap contains enough of the letters in each word to create it

        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Games/ScrabbleSolver/englishWords.txt"));
            System.out.println("All the possible English words you can make with those letters: ");
            for (String currentWord = bf.readLine(); currentWord!=null; currentWord = bf.readLine()) {
                //create a map of the number of occurrences of each char in the currentWord we're iterating in the .txt
                Map<Character, Integer> wordLetterCount = getCharacterIntegerMap(currentWord);
                //for each char in the wordmap, check if the userLetterMap has enough of each char for it
                boolean canMakeCurrentWord = true;
                for (char c : wordLetterCount.keySet()){   //returns every char key in the wordMap
                    int currentWordCharCount = wordLetterCount.get(c);   //gets the number of occurrences for the current char in the current word
                    int userLettersCharCount = userLetterIntegerMap.containsKey(c) ? userLetterIntegerMap.get(c) : 0;
                    if (currentWordCharCount > userLettersCharCount){  //the currentWord requires more of the char than exists in the user input
                        canMakeCurrentWord = false;
                        break;
                    }

                } if (canMakeCurrentWord){
                    System.out.println(currentWord);
                }
            }
            bf.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scanner.close();


    }

    private static Map<Character, Integer> getCharacterIntegerMap(String userLetters) {
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        char[] userLettersChars = userLetters.toCharArray();
        for (char letter : userLettersChars){
            if (characterIntegerMap.containsKey(letter)){
                int oldValue = characterIntegerMap.get(letter);
                characterIntegerMap.replace(letter, ++oldValue);
            } else {
                characterIntegerMap.put(letter, 1);
            }
            //Alternative to above if else
            //int count = characterIntegerMap.getOrDefault(letter, 0);
            //characterIntegerMap.put(letter, ++count);
        }
        return characterIntegerMap;
    }
}
