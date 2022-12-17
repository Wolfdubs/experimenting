package Concepts;

import com.sun.security.jgss.GSSUtil;

public class LocalVariableTypeInference {
    //type inference also seen in lambda parameters where don't have to specify variable types


    public static void main(String[] args) {
        //local variables can be defined loosely with var, for Java 10+
        //    real type is converted & assigned at runtime
        var i = 8;
        var nums = new int[10];
        // var d;    //this will cause compilation error as java cannot infer the type without initialization
        var obj = new LocalVariableTypeInference();
        String var = "howdy";   //can still use var as variable name. but not as a class name

       // new enhancedSwitch();

    }

}


class enhancedSwitch {
    //ability to use switch as an expression
    public void enhancedSwitch() {

        //Usage 1
        String day = "Monday";
        switch (day) {
            case "Saturday", "Sunday" -> System.out.println("Wake up at 1000");   //can include multiple conditions for 1 case
            case "Monday" -> System.out.println("Wake up at 0800");  //don't need break; to prevent execution continuing on to later cases
            default -> System.out.println("Wake up at 0900");
        }

        //Usage 2
        String color = "red";
        String result = "";
        switch (color) {
            case "blue" -> result = "you have chosen sapphire";
            case "green" -> result = "you have chosen jade";
            case "red" -> result = "you have chosen ruby";
        }
        System.out.println(result);

        //Usage 3
        String sport = "Swimming";
        String myResult = "";
        myResult = switch (sport) {
            case "Hockey" -> "Buy stick";    //directly returns the value "Buy stock" to myResult
            case "Swimming" -> "Buy goggles";
            case "Climbing" -> "Buy chalk";
            default -> "Buy shoes";
        };

        //Usage 4 = just like Usage 3, but replace -> with : yield
        String sports = "Swimming";
        String myResult2 = "";
        myResult2 = switch (sport) {
            case "Hockey":
                yield "Buy stick";    //directly returns the value "Buy stock" to myResult
            case "Swimming":
                yield "Buy goggles";
            case "Climbing":
                yield "Buy chalk";
            default:
                yield "Buy shoes";
        };

    }
}


