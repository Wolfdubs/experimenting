package Concepts.FunctionalProgramming;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.Gun;
import utils.Weapon;
import utils.Weapon.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static utils.Weapon.TYPE.*;
import static utils.Weapon.generateWeaponsList;
import static utils.Weapon.generateWeaponsMap;

//Function interface
//a type of Functional Interface that receives a single argument, and returns a value after processing
//the lambda / MR you provide is used to define its SAM apply(), which applies the given function to the argument
//SAM = apply() -> accepts a type, returns any type
//Default methods of Function interface, with defined implementation
    //andThen() = to chain functions together; 1st function is called, then the parameterized one
    //compose() = reverse order of andThen, calls the parameterized function, then the caller
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
        System.out.println(squaredList);  //doesn't actually modify the list at all


        BiFunction<Function<Integer,Double>, Integer, Integer> convertDoubleFunctionToInteger = (fid, i) -> (int) Math.abs(fid.apply(i));
        for (int i = 0; i < doubledListViaMethodReference.size(); i++){
            Integer halvedInt = convertDoubleFunctionToInteger.apply(halvingFunction, doubledListViaMethodReference.get(i));
            doubledListViaMethodReference.set(i,halvedInt);
        }
        System.out.println("myList post halving " + doubledListViaMethodReference);




        myList.forEach(FunctionDemo::doubling);   //doesn't return a doubled list, printing myList shows it is unchanged, because forEach takes a Consumer, and forEach has void return

        List<String> languages = Arrays.asList("java", "python", "golang", "ruby", "javascript", "php", "swift", "perl", "groovy", "c", "shell");
        languages.forEach(language -> language.toUpperCase());
        languages.forEach(String::toUpperCase);   //alternative to the lambda above
        System.out.println(languages);  //still lower case, as forEach will not modify the Iterable, as it only works with a Consumer


        Function<String, String> printRepeat = str -> {      //function to print the number and each char of a string, as 1 big string
            String returnString = "";
            for (int i = 0; i <= str.length()-1; i++) {
                returnString = returnString.concat(i+1 + "." + (str.charAt(i)) + " ");
            }
            System.out.println(returnString);
            return returnString;
        };
        printRepeat.apply("womble");

        //andThen() to chain functions -> You CAN mix if the output type of the 1st matches the input type of the 2nd
        Double output = squaringFunction.andThen(halvingFunction).apply(10);  //squares it, then halves it
        System.out.println(output);
        halvingFunction = halvingFunction.andThen(n -> n+1);
        System.out.println(halvingFunction.apply(10));    //halves value, then applies the unnamed function to add 1, so prints 6.0

        //compose() is reverse of andThen(); executes parameterized function first
        Function<Integer,Integer> plusOne = n -> n+1;
        Function<Integer,Integer> plusOneAndSquare = squaringFunction.compose(plusOne);   //so it adds 1, then squares
        System.out.println(plusOneAndSquare.apply(5));
        int inceptionInt = plusOneAndSquare.compose(squaringFunction).apply(3);

        //identity() returns a function that returns its only argument; so just echoes the parameter passed
        Function<String,String> identityFunction = Function.identity();
        Function<Boolean,Boolean> identityFunction2 = Function.identity();
        Function if3 = Function.identity();
        System.out.println(identityFunction.apply("womble"));
        System.out.println(identityFunction2.apply(true));
        System.out.println(if3.apply(17));



        List<String> weapons = Arrays.asList("knife", "gun", "axe", "club", "grenade");
        Function<String,String> capitalize = String::toUpperCase;
        weapons.stream().map(capitalize).forEach(System.out::println);

        Function<Integer,String> hi = w -> w.toString() + " hi";
        processing(14, hi);

        List<Weapon> weaponList = generateWeaponsList();
        List<Weapon> groupWeapons = weaponList.stream()
                .filter(Weapon::isSinglePerson)
                .toList();


    }
    //method defined to accept a function and apply it to other argument
    private static String processing(int number, Function<Integer, String> lambda){
        return lambda.apply(number);
    }


}

//as primitives cannot be used as the real type for generics (which Functions' apply() accepts), the most common primitives have their own versions of Functions
    //specialized functions accept/return specific primitive types
class SpecializedFunctions {
    //Specified argument: they dont require boxing of the argument, just the return type
    IntFunction<String> intFunction = n -> n + ".... ";   //always accepts an int
    DoubleFunction<Double> doubleFunction = d -> d/(d-1);
    LongFunction<Integer> longFunction = l -> ((int) l) + 7;

    //Specified return: don't require boxing of the return, just the argument type
    ToIntFunction<Boolean> toIntFunction = b -> {       //always produces an int
        if (b) return 44;
        else return 0;
    };
    ToDoubleFunction<String> toDoubleFunction = Double::valueOf;
    ToLongFunction<Long> toLongFunction = l -> l*3;

    //Specified argument and return; don't need to box any types
    IntToDoubleFunction intToDoubleFunction = t -> t / 3.0;
    IntToLongFunction intToLongFunction;
    DoubleToIntFunction doubleToIntFunction;
    DoubleToLongFunction doubleToLongFunction;
    LongToDoubleFunction longToDoubleFunction;
    LongToIntFunction longToIntFunction;

    //primitive BiFunctions - just specify the 2 arguments
    ToDoubleBiFunction<Integer, String> toDoubleBiFunction = (a,b) -> Double.parseDouble(b) + ((double) a);  //always returns a double
    ToIntBiFunction<String, Boolean> toIntBiFunction;
    ToLongBiFunction<Character, Float> toLongBiFunction;

    private void replaceAllDemo() {
        Map<String,Integer> map = generateWeaponsMap();
        map.replaceAll((key, oldValue) -> !key.equals("nuke") ? oldValue : oldValue-1 );
    }

    //can write custom specializations e.g. for short to byte
    @FunctionalInterface
    interface ShortToByteFunction{
        byte applyAsByte(short s);
    }

    ShortToByteFunction shortToByteFunction = s -> (byte) s;
    private static Byte[] transformShortArrayToByteArray(short[] shorts, ShortToByteFunction function) {
        Byte[] bytes = new Byte[shorts.length];
        for (int i = 0; i < shorts.length; i++) {
            bytes[i] = function.applyAsByte(shorts[i]);
        }
        return bytes;
    }

    short[] shorts = {(short) 1, (short) 2, (short) 3, (short) 4};
    Byte[] bytes = transformShortArrayToByteArray(shorts, s -> (byte) (s*2));


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
        languages.stream().sorted((a, b) -> languageComparator.compareByNameLength(a, b));
        languages.stream().sorted(languageComparator::compare);  //method reference way of calling the compare method of the custom comparator
        languages.stream().sorted(languageComparator); // or just pas comparator in directly since compare() is the SAM
        languages.stream().map(Language::getName).forEach(System.out::println);  //maps each element to results of MR to getName()

        Collections.sort(languages, FunctionWithComparator::compareByName);   //using static method reference, rather than comparator object, to sort
        Collections.sort(languages, languageComparator::compareByNameLength); //using MR on particular object instance
        languages.sort(languageComparator);  //to use default SAM compare()
        languages.sort(languageComparator::compareByNameLength);


        languages.toArray(Language[]::new);
    }
}

//Same as Function interface
// but accepts 2 arguments, returns 1 result
//andThen() accepts a Function
//does not have compose() because the function provided to the methods must be executed, to have a result to process
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
        map.replaceAll((k, v) -> v + 1);    //replaceAll is an inbuilt BiFunction; accepting 2 arguments, 1 return value
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

        BiFunction<Integer,Integer,Double> power = Math::pow;
        double powered = power.apply(4,6);
        System.out.println(powered);


        BiFunction<Short, Short, List<Short>> shortList = Arrays::asList;       //(a,b) -> Arrays.asList(a,b);
        shortList.apply((short) 3, (short) 9);

        //andThen can only accept a function, because the calling function only produces 1 output that it can send to the parameterized function
        Function<Double,String> intToString = Object::toString;
        power.andThen(intToString).apply(3,7);

        String poweredString = biFunctionThenFunction(6,4, power, intToString);

        String multipliedByGeneric = genericBiFunctionThenFunction(9,5, (a,b) -> a*b, c -> "Multiplied = " + c.toString());
        String poweredByGeneric = genericBiFunctionThenFunction(9,5, (a,b) -> Math.pow(a,b), c -> "Powered = " + c.toString());
        String concatenatedByGeneric = genericBiFunctionThenFunction("womble", "mungo", String::concat, String::toUpperCase);
        Integer concatenatedIntoInteger = genericBiFunctionThenFunction("18","72", (a,b) -> a+b, Integer::valueOf);


    }

    private static String biFunctionThenFunction(int a, int b, BiFunction<Integer,Integer,Double> biFunction, Function<Double, String> function) {
        return biFunction.andThen(function).apply(a,b);
    }

    private static <A1, A2, R1, R2> R2 genericBiFunctionThenFunction(A1 a1, A2 a2, BiFunction<A1, A2, R1> biFunction, Function<R1, R2> function) {
        return biFunction.andThen(function).apply(a1,a2);
    }

    //if the map has no value for the key, the method will use the function to calculate it, else just retrieve it from the map
    private static void computeIfAbsentDemo(){
        Map<String, Integer> stringToLengthMap = new HashMap<>();
        Integer keyLength = stringToLengthMap.computeIfAbsent("womble", String::length);
    }


}

//implement the factory pattern using a BiFunction to create objects by calling their constructor and passing in the arguments it needs
class BiFunctionFactory {
    public static void main(String[] args) {
        Weapon bowieKnife = biFunctionFactory("bowie knife", BLADED, Weapon::new);  //the BiFunction is a call to the Weapon constructor, and passes in the 2 prior parameters
        Gun pistol = (Gun) biFunctionFactory("Glock", FIREARM, Weapon::new);
    }

    private static <R extends Weapon> R biFunctionFactory(String name, Weapon.TYPE type, BiFunction<String, Weapon.TYPE, R> biFunction) {
        return biFunction.apply(name, type);
    }

}

class BiFunctionFiltering {
    public static void main(String[] args) {
        List<Weapon> weapons = generateWeaponsList();
        List<String> weaponNamesAboveLength6 = filterList(weapons, 6, new BiFunctionFiltering()::filterByLength);
        weaponNamesAboveLength6.forEach(System.out::println);

        List<String> weaponNamesAlsoAboveLength6 = filterList(weapons, 6, (element, size) -> {
                    if (element.getName().length() > size) return element.getName();
                    else return null;
                });

        filterList(weapons, "a", (element, letter) -> {
            if (element.getName().startsWith(letter)) return element.getName();
            else return null;
        });

        List<Weapon> handToHandWeapons = filterList(weapons, true, (element, bool) -> {
            if (element.isHandToHand()) return element;
            else return null;
        });

        List<Weapon> selfSustainingWeapons = filterList(weapons, 7, (element, num) -> {
            if (element.getSelfSustainability() >= num) return element;
            else return null;
        });


    }
    private String filterByLength(Weapon weapon, int n) {
        String weaponName = weapon.getName();
        if (weaponName.length() > n) {
            return weaponName;
        } else {
            return null;
        }
    }
    public static <T,U,R> List<R> filterList(List<T> list, U condition, BiFunction<T,U,R> biFunction){
        List<R> filteredList = new ArrayList<>();
        for (T t : list){
            R filteredItem = biFunction.apply(t, condition);
            if (filteredItem != null){
                filteredList.add(filteredItem);
            }
        }
        return filteredList;

    }
}

//UnaryOperator & BinaryOperators are both functions that just accept and produce only 1 type for everything
//Unary Operator extends the Function interface
//accepts one 1 argument, produces 1 value
//Difference to Function -> with Unary Operator, both the argument and return must be of the same type, so only give 1 type parameter in <>
//used in list.replaceAll(); does in-place replacement of values with some computed value of the same type
//SAM = apply()
//As extension of Function, it also has andThen() and compose()
class UnaryOperatorDemo {

    public static void main(String[] args) {
        UnaryOperator<Double> unaryOperator = d -> d * 10;

        //to replace all "/" in a list with "-"
        List<String> dates = new ArrayList<>();
        UnaryOperator<String> replaceDashes = str -> str.replace("/", "-");
        dates.replaceAll(replaceDashes);
        System.out.println(dates);
        dates.replaceAll(str -> str.replace("/","-"));  //passing in UnaryOperator on the fly

        //replace elements in a list with upperCase
        List<Weapon> weapons = generateWeaponsList();
        List<String> weaponNames = new ArrayList<>(weapons.stream()  //wrapped with ArrayList to make it mutable with the below replaceAll()
                .map(Weapon::getName)
                .toList());
        weaponNames.replaceAll(String::toUpperCase);  //replaceAll returns void as it updates all elements in place; thus the unary operator
                                                        //returns the same type as it receives
    }

    //Specialized UnaryOperators, for use with primitives. dont need to box the type
    IntUnaryOperator intUnaryOperator;
    DoubleUnaryOperator doubleUnaryOperator;
    LongUnaryOperator longUnaryOperator = l -> l%5;
    void specializedUnaryOperators(){
        intUnaryOperator.applyAsInt(4);
        doubleUnaryOperator.applyAsDouble(2);
        longUnaryOperator.applyAsLong(99);
        longUnaryOperator.compose(l -> (long) Math.log(l)).applyAsLong(33434L);
    }


}

//extends BiFunction interface
//accepts 2 arguments, produces 1 value, but these must all be of the same type
//used in streams.reduce(); takes initial accumulator value and binary operator function (value pair of same type)
    //and joins them using a function into a single value of that type
//SAM = apply()
//As extension of BiFunction, it also has andThen() and compose()
class BinaryOperatorDemo {
    BinaryOperator<Character> binaryOperator = (c,d) -> (char) (d-c);

    //useful for reducing
    public static void main(String[] args) {
        List<Integer> weaponDifficulties = generateWeaponsList().stream()
                .map(Weapon::getDifficulty)
                .toList();
        Integer totalDifficulty = weaponDifficulties.stream()
                .reduce(0, Integer::sum);  //pass reduce an initial accumulator value, then the binaryOperator as the accummulator
    }

    //Specialized BinaryOperators
    IntBinaryOperator intBinaryOperator;
    DoubleBinaryOperator doubleBinaryOperator;
    LongBinaryOperator longBinaryOperator;
    void specializedBinaryOperators(){
        intBinaryOperator.applyAsInt(5,6);
        doubleBinaryOperator.applyAsDouble(2,3);
        longBinaryOperator.applyAsLong(3,8);
    }
}











