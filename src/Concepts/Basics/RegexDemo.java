package Concepts.Basics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
To find patterns in text
Classes:
    Pattern = represents compiled regex; write regex according to its syntax and give the pattern to search for, then compile the pattern search
        instantiate via the static compile(), not a constructor
    Matcher = provides engine to interpret the pattern and search for it in text, returning boolean
        instantiate via matcher() on Pattern object, not a constructor
    PatternSyntaxException = thrown if invalid regex syntax
 */
public class RegexDemo {

    String metaCharacters = "?*+.([{\\-=$!|}])";
    String anyCharacter = ".";
    String anyDigit = "\\d";   //0-9
    String noneDigit = "\\D";  //[^0-9]
    String whitespace = "\\s";   //[\t, \n, \x0B \f \r]
    String nonWhitespace = "\\S";  //"[^\s]";
    String wordCharacter = "\\w";   //[a-zA-Z0-9]
    String nonWordCharacter = "\\W";    //[^\w]

    String listedChars = "[abc]";
    String negationChars = "[^abc]";   //any char except one of these
    String rangeChars = "[a-zA-Z]";  //inclusive range of a-z or A-Z
    String unionChars = "[a-d[m-p]]";  //same as a-d or m-p
    String intersection = "[a-z&&[def]]";   //d e or f
    String subtraction = "[a-z&&[^xy]]";   //a-z, except x and y
    String subtraction2 = "[a-wz]"; //a-w, and then z too
    String subtractionRange = "[a-z&&[^m-p]";  //a-z excluding m-p
    String subtractionRange2 = "[a-lq-z";   //a-z excluding m-p

    public static void main(String[] args) {
        String inputString = "jodejwodp347394iedjed";
        String regex = ".*[a-z][0-9][a-z]";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(inputString);
        boolean regexMatched = matcher.find();
        if (regexMatched) System.out.println("Match found");
        else System.out.println("no match found");
    }

}
