package Games;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WordsPerMinute {
    public static final int TEST_LENGTH = 10;
    public static final double AVERAGE_WORD_LENGTH = 5;
    public static final double SECONDS_IN_MINUTE = 60;
    public static void main(String[] args) throws InterruptedException {

        String[] words = {"sausage", "blubber", "pencil", "cloud", "moon", "water", "computer", "school", "network", "hammer", "walking", "violently",
                "mediocre", "literature", "chair", "two", "window", "cords", "musical", "zebra", "xylophone", "penguin", "home", "dog", "final",
                "ink", "teacher", "fun", "website", "banana", "uncle", "softly", "mega", "ten", "awesome", "attach", "blue", "internet", "bottle",
                "tight", "zone", "tomato", "prison", "hydro", "cleaning", "send", "frog", "book", "zooming", "falling", "evil", "gamer", "lid",
                "juice", "monitor", "captain", "bonding", "loudly", "thudding", "guitar", "shaving", "hair", "soccer", "water", "racket", "table",
                "late", "media", "desktop", "capital", "java", "python", "programming", "word", "killer", "intimate", "kisses", "groovy", "match",
                "smooth", "monster", "flying", "hyper", "womble", "pekingese", "mungo", "element", "crust", "forest", "comment", "like", "world",
                "magic", "sand", "silent", "present", "down"};

        String[] sampleWords = new String[TEST_LENGTH];

        for (int i = 0; i < 10; i++){
            int random = new Random().nextInt(words.length-1);
            String sampleWord = words[random];
            sampleWords[i] = sampleWord;
        }

        System.out.println("3");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("GO");

        for (String s :  sampleWords){
            System.out.print(s + " ");
        }
        System.out.println();

        double start = LocalTime.now().toNanoOfDay();
        // System.out.println(Arrays.toString(sampleWords));
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        double numChars = input.length();
        double end = LocalTime.now().toNanoOfDay();
        double speedNano = end-start;
        double speedSeconds = speedNano/1000000000.0;

        String[] inputArray = input.split(" ");
        boolean wordsMatch = true;
        for (int i = 0; i < sampleWords.length; i++){
            if (!sampleWords[i].equals(inputArray[i])){
                wordsMatch = false;
                break;
            }
        }

        if (wordsMatch){
            System.out.println("Your speed was " + speedSeconds + " seconds");
            double wpmLong = ((numChars / AVERAGE_WORD_LENGTH) / speedSeconds) * SECONDS_IN_MINUTE ;
            DecimalFormat df = new DecimalFormat("0.0");
            String wpm = df.format(wpmLong);
            System.out.println("Your words per minute = " + wpm);
        } else {
            System.out.println("You did not type the words correctly");
        }


    }




















}
