package Concepts.Basics;

//StringBuilder is mutable, so much faster to operate on than strings which require creation of new string every operation and coping char each time
public class StringBuilderDemo {

    //O(n^2): copies each char over each time, because strings are immutable
    public static String stringAppend(String[] arr){
        String concatenated = "";
        for (String s : arr){
            concatenated+=s;
        }
        return concatenated;
    }

    //O(n); as just pasting the strings together
    public static String stringBuilderAppend(String[] arr){
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : arr){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String[] strings = new String[100];
        for (int i=0; i<strings.length; i++){
            strings[i] = Double.toString(Math.random());
        }

        long startStringAppend = System.currentTimeMillis();
        String output = stringAppend(strings);
        long timeTakenStringAppend = System.currentTimeMillis() - startStringAppend;
        System.out.println(String.valueOf(timeTakenStringAppend));

        long startStringbuilderAppend = System.currentTimeMillis();
        String output2 = stringBuilderAppend(strings);
        long timeTakenStringbuilderAppend = System.currentTimeMillis() - startStringbuilderAppend;
        System.out.println(timeTakenStringbuilderAppend);












    }
}
