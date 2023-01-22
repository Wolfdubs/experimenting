package Concepts.FunctionalProgramming;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

//a type of Functional Interface that receives a single argument, and returns a value after processing
//the lambda / MR you provide is used to define its SAM apply(), which applies the given function to the argument
//SAM = apply()
//also has andThen() to chain functions together; 1st function is called, then the parameterized one
public class FunctionDemo {
    static Function<Integer, Integer> squaringFunction = x -> x*x;     //can pass the function object into a method

    static int doubling(int x){
        return x * 2;
    }

    private static List<Integer> operateOnList(List<Integer> list, Function<Integer,Integer> function) {
        List<Integer> outputList = new ArrayList<>();
        for (Integer i : list) {
            int j = function.apply(i);    //apply the function to elements of the list via its apply()
            outputList.add(j);
        }
        return outputList;
    }

    public static void main(String[] args) {
        List<Integer> squaredList = operateOnList(List.of(1,2,3,4,5,6,7,8,9,10), squaringFunction);
        System.out.println(squaredList);

        List<Integer> myList = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11);
        List<Integer> doubledListViaLambda = operateOnList(myList, i -> doubling(i));  //lambda reference to concrete method implementation
        List<Integer> doubledListViaMethodReference = operateOnList(myList, FunctionDemo::doubling);
        System.out.println(doubledListViaLambda);
        System.out.println(doubledListViaMethodReference);

        Function<Integer, Double> halvingFunction = i -> i/2.0; //pure lambda implementation
        for (Integer i : squaredList) {
            halvingFunction.apply(i);
        }



        myList.forEach(FunctionDemo::doubling);   //doesn't return a doubled list, printing myList shows ti is unchanged

        List<String> languages = Arrays.asList("java", "python", "golang", "ruby", "javascript", "php", "swift", "perl", "groovy", "c", "shell");
        languages.forEach(language -> language.toUpperCase());
        languages.forEach(String::toUpperCase);   //alternative to the lambda above


        Function<String, String> printRepeat = str -> {      //function to print the number and each char of a string, as 1 big string
            String returnString = "";
            for (int i = 0; i <= str.length()-1; i++) {
                returnString = returnString.concat(i+1 + "." + String.valueOf(str.charAt(i)) + " ");
            }
            System.out.println(returnString);
            return returnString;
        };
        printRepeat.apply("womble");

        //andThen() to chain functions
        Double output = squaringFunction.andThen(halvingFunction).apply(10);  //squares it, then halves it
        System.out.println(output);
        halvingFunction = halvingFunction.andThen(n -> n+1);
        System.out.println(halvingFunction.apply(10));    //halves value, then applies the unnamed function to add 1, so prints 6.0

        //compose() is reverse of andThen(); executes parameterized function first
        Function<Integer,Integer> plusOne = n -> n+1;
        Function<Integer,Integer> PlusOneAndSquare = squaringFunction.compose(plusOne);   //so it adds 1, then squares
        System.out.println(PlusOneAndSquare.apply(5));

        //identity() returns a function that returns its only argument; so just echoes the parameter passed
        Function<String,String> identityFunction = Function.identity();
        System.out.println(identityFunction.apply("womble"));


        List<String> weapons = Arrays.asList("knife", "gun", "axe", "club", "grenade");
        Function<String,String> capitalize = String::toUpperCase;
        weapons.stream().map(capitalize).forEach(System.out::println);

        Function<Integer,String> hi = w -> w.toString() + " hi";
        processing(14, hi);

    }
    //method defined to accept a function and apply it to other argument
    private static String processing(int number, Function<Integer, String> lambda){
        return lambda.apply(number);
    }


}






class FunctionWithComparator {
    @Getter
    @Setter
    @ToString
    static class Language {
        private String name;
        private Integer users;

        public Language(String name, int users) {
            this.name = name;
            this.users = users;
        }

    }
    //for comparators, method reference will handle passing in the 2 parameters from a list
    static class LanguageComparator implements Comparator<Language> {
        @Override
        public int compare(Language o1, Language o2) {
            return o1.getUsers().compareTo(o2.getUsers());
        }

        public int compareByNameLength(Language o1, Language o2) {
            return Integer.compare(o1.getName().length(), o2.getName().length());
        }
    }

    public static int compareByName(Language a, Language b) {
        return a.getName().compareTo(b.getName());
    }

    public static void main(String[] args) {
        LanguageComparator languageComparator = new LanguageComparator();
        List<Language> languages = List.of(new Language("java", 50), new Language("python", 80),
                new Language("ruby", 20), new Language("javascript", 100), new Language("c", 30),
                new Language("swift", 5), new Language("shell", 10), new Language("golang", 1));
        languages.stream().sorted((a, b) -> languageComparator.compare(a, b));
        languages.stream().sorted(languageComparator::compare);  //method reference way of calling the compare method of the custom comparator
        languages.stream().map(Language::getName).forEach(System.out::println);  //maps each element to results of MR to getName()

        Collections.sort(languages, FunctionWithComparator::compareByName);   //using static method reference, rather than comparator object, to sort
        Collections.sort(languages, languageComparator::compareByNameLength); //using MR on particular object instance


        languages.stream().toArray(Language[]::new);
    }
}

//accepts 2 arguments
class BiFunctionDemo {
    static Map<String, Integer> map = new HashMap<>() {{
        put("womble", 10);
        put("mungo", 1);
        put("sita", 7);
        put("kosie", 2);
        put("kato", 5);
        put("jambo", 6);
    }};

    public static void main(String[] args) {
        map.replaceAll((k, v) -> v + 1);    //replaceAll is a inbuilt BiFunction; accepting 2 arguments, 1 return value
        BiFunction<Integer, Integer, Integer> product = (a,b) -> a*b;   //in the <> define the 2 argument types, and return type
        int productInt = product.apply(4,5);
        System.out.println(productInt);

        BiFunction<String, Integer, String> repeatString = (str, k) -> {
            String longString = "";
            for (int i = 1; i <= k; i++ ){
                longString = longString.concat(i + ". " + str + " ");
            }
            System.out.println(longString);
            return longString;
        };
        repeatString.apply("womble", 10);
    }
}

//Unary Operator extends the Function interface
//accepts one 1 argument, produces 1 value
//Difference to Function -> with Unary Operator, both the argument and return must be of the same value, so only give 1 type parameter in <>
class UnaryOperatorDemo {

    public static void main(String[] args) {
        UnaryOperator<Double> unaryOperator = d -> d * 10;

        //to replace all "/" in a list with "-"
        List<String> dates = new ArrayList<>();
        UnaryOperator<String> replaceDashes = str -> str.replace("/", "-");
        dates.replaceAll(replaceDashes);
        System.out.println(dates);
    }


}

//extends BiFunction interface
//accepts 2 arguments, produces 1 value, but these must all be of the same type
class BinaryOperatorDemo {
    BinaryOperator<Character> binaryOperator = (c,d) -> (char) (d-c);
}











