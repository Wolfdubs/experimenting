package Games;

import java.util.Random;

public class Dice {

    private final int faces = 6;

    private int roll(){
        Random random = new Random();
        return random.nextInt(1,faces);
    }

    public static void main(String[] args) {
        Dice d = new Dice();
        int myRoll = d.roll();
        System.out.println(myRoll);
    }
}
