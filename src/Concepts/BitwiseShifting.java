package Concepts;
/*
Binary represents negative numbers by having 2's compliment version of the positive, storing 1's in different left indices
<< = signed left shift: moves all bits to the left; each shift doubles the number (by increasing the exponent over 2e.g. 2^4 -> 2^5)
>> = signed right shift: moves all bits to the right; each shift halves the number (by decreasing the exponent over 2 e.g. 2^5 -> 2^4)
    this also deletes the least significant bits (the rightmost ones)
    if the number was positive, a 0 will fill the trailing positions on the left, if negative then a 1 will fill them
>>> = unsigned right shift: moves all bits to the right and adds a 0 to the vacant leftmost position (instead of the original sign)
        doesn't preserve the original sign; just fills the leftmost bits with 0's so the number becomes positive
There is no unsigned left shift because left positions are just overridden by the digits shifted left
 */
public class BitwiseShifting {
    public static void main(String[] args) {
        byte a = 64, b;
        int i;
        i = a << 2;   //leftmost 2 bits are lost; e.g. 0010 -> 1000
        b = (byte) ( a << 2);
        System.out.println("Original value of a = " + a);
        System.out.printf("Value of i = %d, value of b = %d",i,b);

        int two = 2;     // 2^1
        int eight = 8;   // 2^3
        int twoLeft = two << 2;   //2^(1+2) = 8
        int twoRight = two >> 2;  //2^(1-2) = 0
        int eightLeft = eight << 2;  //2^(3+2) = 32
        int eightRight = eight >> 2;  //2^(3-2) = 2
        System.out.printf("\ntwoLeft = %d, twoRight = %d",twoLeft,twoRight);
        System.out.printf("\neightLeft = %d, eightRight = %d",eightLeft,eightRight);

        byte num1 = 8;
        byte num2 = -8;   //0010110100111000
        System.out.println("\nnum1 -> " + (num1 >>> 2) +
                " num2 -> " + (num2 >>> 2));   //00111111111111111111111111111110



    }


}

class BitwiseOperations {
    /*
    & = does AND bit-by-biy
    | = does OR bit-by-bit
    ^ = does XOR bit by bit
    ~ = does bitwise compliment; aka inverts the bit value
     */

    public static void main(String[] args) {
        System.out.println(1 | 2);
    }
}





















