package Concepts.Basics.Variables;

public class Variables {

    //multiple overloading constructors need different signatures; polymorphism
    public Variables(){}
    public Variables(String s){}
    public Variables(double d){}
    public Variables(float f, long l, short s, boolean b){}

    public static void main(String[] args) {
        System.out.println(incrementChar('x'));
        System.out.println(incrementChar('Z'));
        System.out.println(incrementChar('z'));
        char myChar = 72;
        System.out.println(myChar);
        System.out.println(incrementChar(myChar));
        intIncrements();

        int[][] array2D = {{1,2,3,4}, {10,20,30,40,50}, {9,8,7,6}, {-3,-9}};  //jagged 2D array as column length varies
        int array2DValue = array2D[1][3];
        int[] array2DValue2 = array2D[2];
        System.out.println(array2DValue);

    //2 ways to iterate over 2D array
        for (byte i = 0; i<array2D.length; i++){       //iterates over rows
            for (byte j=0; j<array2D[i].length; j++){  //iterates over columns
                System.out.print(array2D[i][j] + " ");
            }
            System.out.println();
        }

        for (int[] k : array2D){
            for (int i : k){
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }

    public static char incrementChar(char inputChar) {
        char myChar = inputChar;
        myChar++;
        return myChar;
    }

    public static void intIncrements() {
        int n = 2, o = 4, p = 1;
        int q = n++;     //first assigns the value to q, then increments the value of n. post increment
        int r = ++n;     //first increments the value of n, then assigns value to r. pre increment
        System.out.println(q);
        System.out.println(r);
    }

    public static int casting(double n){
        int i = (int) n;
        return i;
    }

    /*by default all decimals are double
    could also store whole numbers in doubles, but will add a .0
    cast to int will truncate the decimal
    float, must end with f; float num = 5.5f;
    short: use if you are certain value will be between -32000 - 32000
    byte; store values from -128 - 127
    long; 8 bytes
    char: can assign to a letter e.g. 'A' or directly to an ASCII character
    so can do a++ to increment to next letter in ASCII*/
}
