package Concepts.Basics;

public class VarArgs {

    //variable length arguments: to allow one method to accept different numbers of parameter. so dont have to add overload methods for every possibility
    public static int addVarArgs(int ... n){  //the ... tells method to accept any number of arguments of the type
        int sum = 0;
        for (int i : n) {     //arguments have been sent to the method as an array, so must iterate over elements
            sum += i;
        }
        return sum;
    }

    public void printVarStrings(String ... s){
        for (String str : s){
            System.out.print(str + ", ");
        }
    }

    public static void main(String[] args) {
        System.out.println(addVarArgs(6,5));  		//	when called, parameters passed in to the call are sent to method definition as an array
        System.out.println(addVarArgs(9,3,5,11,83,7,12,40));
        System.out.println(addVarArgs(1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2,1,18,17,16,15,13));
        VarArgs var = new VarArgs();
        var.printVarStrings("one", "two", "three", "four", "five", "six", "seven", "eight");
    }
}
