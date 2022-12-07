package Testing.Debugging;

//Debugging lets you walk through code execution line by line, seeing what all variable values are
//must add breakpoints to the code -> code will run up until that specific line and then pause just before it. Can have multiple breakpoints
    //set by clicking in the left margin by the line
    //console shows all the variables in the scope of the current line
        //for objects, can see the values for all its fields
        //the ids auto assigned for fields and objects mean you can see if variables refer to the same actual object in memory
    //Expressions tool lets you see the value of any expression you input at any point in the code execution
    //can also see all threads running
//Step Over = execute current line and move to next line
//Step into = to enter into method calls made by current line, so can follow execution inside the method called. Steps into the 1st line
    //will only show the variables in the scope of the method
    //breakpoints set inside a method will make debugger auto step into
//step return = to exit from the step into; completing execution of the method and returning back to where the method was called from
//resume = continues execution until program hits another breakpoint. so jumps to next breakpoint/end of program if no other breakpoints

//Can also change variable values arbitrarily to see how code will react, by editing inside console, or even by inspecting the variable/expression and editing
//can use breakpoints to iterate through loops to see how values change
    //can just to specific iteration via Conditional Breakpoint and enter a java expression that returns a boolean, so when it returns true, the breakpoint will stop there
                //e.g. Conditional Breakpoint of i==7; will jump through for loop straight to where i==7
                // can use a conditional breakpoint to execute whatever piece of code you like, for example automatically edit a variable's value
                // instead of doing it yourself everytime or log something with a print without even touching the code
public class DebuggingDemo {
}
