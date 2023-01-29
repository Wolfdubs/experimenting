package Concepts.Basics.Variables;

public class Operations {

    public static void main(String[] args) {
        int x = 9 / 2;  // equals 4 due to truncation
        double y = 9.0 / 2;  //equals 4.5
        double z = 9 / 2.0;   //equals 4.5

        //concatenating ints/chars/doubles/boolean with String -> always products String
        String a = "ewdo" + x;
        String b = "weofjeo" + y;
        String c = "eofjeo" + 'd';
        String d = "eojdepd" + true;

        //Short circuit evaluation: more complex operand should be on the right, because java will calculate the left one 1st to see if it should
            //even bother calculating the 2nd; so is more efficient

        //generate any range of random numbers
        int max = 100, min = 50;
        int randomNumber = (int) (Math.random() * (max-min) +1) + min;
    }
}
