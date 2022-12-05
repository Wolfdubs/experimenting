package Concepts.Basics;

public class Operations {

    public static void main(String[] args) {
        ternaryOperator(5);
        ternaryOperator(-5);
        switchStatement(3);
        switchStatement(10);
        doWhile(4);
        doWhile(-10);
        nestedLoopsFlag();
        nestedLoopsAlphabet();
        nestedLoopsBox();
    }

    public static void ternaryOperator(int i){
        // expression ? AssignmentExpressionIfTrue : AssignmentExpressionIfFalse
        String j = i >= 0 ? "Input was positive" : "Input was 0 or negative";
        System.out.println(j);
    }

    public static void switchStatement(int n){           //switch does not support double, but does support String, char, int
        switch (n){
            case 1:
                System.out.println("One");
                break;               // required because else, once it finds 1 matching case, it would then keep executing all the other cases
            case 2:
                System.out.println("Two");
                break;
            case 3:
                System.out.println("Three");
                break;
            case 4:
                System.out.println("Four");
                break;
            case 5:
                System.out.println("Five");
                break;
            default:
                System.out.println("No match");
        }
    }
/*   enhanced switch not supported by java 11
        doesn't require break statements
    public static void enhancedSwitch(String day){
        switch (day) {
            case "Monday" -> System.out.println("It's Monday");
            case "Tuesday" -> System.out.println("It's Tuesday");
            default -> System.out.println("It's the weekend");
        }
    }

 */

    public static void doWhile(int num){
        do{                                //will always get executed at least once. continuous looping decided by the while at bottom
            System.out.println(num);
            num--;
        } while (num>0);
    }

    public static void nestedLoopsFlag(){
        for (short i=0; i<5; i++){
            for (byte j=0; j<7; j++){
                System.out.print("* ");
            }
            for (byte k=0; k<30; k++){
                System.out.print("=");
            }
            System.out.println();
        }
        for (byte b=0; b<4; b++ ){
            for (short s=0;s<44;s++){
                System.out.print("=");
            }
            System.out.println();

        }
    }

    public static void nestedLoopsAlphabet(){
        for (char c = 65; c<=90; c++){
            for (char d = 65; d<=c; d++){
                System.out.print(d);
            }
            System.out.println();
        }
    }

    public static void nestedLoopsBox(){
        for (short s=0; s<4; s++){
            for (; s>=1 && s<=2; s++){
                System.out.println("$     $");
            }
            System.out.println("$ $ $ $");
        }
    }




}
/*
    String[] outerloop = {"one", "two", "three", "four"};
        for (byte i = 0; i < outerloop.length; i++){
        for (short j = 0; j<outerloop[i].length(); j++){
        System.out.println(j);
        }
        }*/
