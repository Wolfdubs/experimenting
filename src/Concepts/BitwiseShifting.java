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

class BitManipulation {
    //Often used in problems where must have a fast time complexity, but reduce space complexity to constant
    //look at the problem bit by bit, rather than entire digits at once, to see what result AND OR & XOR gives
/*
Addition is just like in decimal
    1 0 1+
    0 1 1
= 1 0 0 0
    1 1
 */
    /*
    Representing Negatives in binary
    2's compliment
        1st bit represents the sign
            0 = positive
            1 = negative
        Remaining bits are filled with the remaining number you would have to add to the positive version to get 2^(bit count)
            e.g. 18 = 00010010
                -18 = what number must be added to 0010010 (drop 1st signed bit) to get 10000000
                    0010010 +
                    xxxxxxx
                  = 1000000

                  Answer -> add all the inverse bits of the positive version (so every single bit is a 1)
                    then add 1 to flip is over from 1111111 -> 10000000
                    0010010 +
                    1101101 -> 1101110 = -18
                  = 1111111   10000000
                  Steps
                    1. Take all the non-signed bits of the positive value representation (drop 1st index)
                    2. Invert all digits
                    3. add 1 (ie move the last 1 up 1 index)

     */

    /*
    Shifting
    2 types (distinction only relevant for right shift)
        Logical shifting
        Arithmetic shifting

    Left Shift: move all bits leftwards, dropping the 1st digit off to trash. does a 2x
    Right Shift: move all bits rightwards, dropping last digit to trash. does a truncated /2

    Negative numbers:
        fate of the signed bit depends on whether doing logical vs arithmetic shifting
            Logical right shift = shifts all bits rightward, filling the now empty signed bit index with 0
                loses relationship between original and new values
            Arithmetic right shift =shifts all bits rightward, then fills signed bit space with original value
                preserves arithmetic relation between original and new values - so does truncated /2
    */
    
    /*
    MASKS
    used to get & update bits
    0 & 0 -> 0
    0 & 1 -> 0
    1 & 1 -> 1
    0 | 0 -> 0
    0 | 1 -> 1
    1 | 1 -> 1
    0 ^ 0 -> 0
    0 ^ 1 -> 1
    1 ^ 1 -> 0
    
    Getting nth bit in a number to find if it is a 0 or 1
        1. Take the input number
        2. Create a mask with all 0's, except a 1 in the target index
            create by taking a 1 and left shifting by n positions
        3. Do & operation between the input and the mask -> output will show if original bit is 1 or 0
            if the tested bit was 0, the entire output will be all 0's
                 00101100
               & 00100000  (1<<n)
                 00100000  -> shows the input index bit was a 1
           Operation = (input&(1<<n))!=0
     */
    void getNthBit(){
        int input = 44; //0010110
        int targetIndex = 2;
        int mask = 1 << targetIndex;
        boolean isNthBit1 = (input&(mask))!=0;
    }

    /*Setting nth bit
        1. Take input number
        2. Create a mask with a 1 at index n
        3. or the input and the mask
        Result = the same number as input, but with nth index definitely being 1
     */
    void setNthBit(){
        int input = 44;  //0010110
        int targetIndex = 1;
        int mask = (1<<targetIndex);
        boolean setNthBitTo1 = (input|mask)!=0;
    }

    /*
    clearing nth bit
        1. Take input number
        2. Create a mask with a 1 every index except the one to clear, and do an &
            create by taking the mask from the get/set and invert it with ~
     */
    void clearNthBit(){
        int input = 7; //00000111
        int targetIndex = 3;
        int mask = (1 << targetIndex);
        boolean clearNthBit = (input & (~mask)) != 0;
    }

    int modifyBit(int input, int targetIndex, int bitValue) {
        int mask = 1 << targetIndex;  //mask only has a value at the index to modify
        return (input & ~mask) | ((bitValue << targetIndex) & mask);  //1st part changes original index bit to 0. 2nd part changes it to the bitValue passed in
    }

}





















