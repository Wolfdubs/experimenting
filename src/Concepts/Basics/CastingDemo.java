package Concepts.Basics;

/*
Upcasting = cast object to its superclass type
Downcasting = cast object to its subclass type
 */
public class CastingDemo {
    public static void main(String[] args) {
        //UPCASTING: creating new subclass object and casting to the superclass type.
        Science myScience = new Chemistry();
                        // java implicitly handles the upcasting because every sub is also a super. never any errors with upcasting
                        //reference type of the super, so can do anything with the variable as you could for once that was created normally
                            //but cannot do things that only apply to the sub
        //myScience.explode();   //cannot access any of the sub-specific properties
        //USE: if a method access any object of the super type, and calls one of the super's methods, then
            //you can pass in an object of the super, or any sub, and the method wil call the method specific to the object type
        //SO CAN MAKE 1 METHOD THAT OPERATES ON ANY TYPE OF THE SUPERCLASS, RATHER THAN A NEW METHOD TO ACCEPT EACH DIFFERENT SUBTYPE AS A PARAMETER
        super_subMethod(myScience);  //passing in the chemistry object with Science reference to the agnostic method

        Physics physics = new Physics();
        super_subMethod(physics);   //error would occur here, were not for the instanceOf check, as the method will try to cast this to be a Chemistry object and call a Chemistry specific method

    }

    //Use of upcasting is upcasting the argument passed in to a method that calls a shared method, so the super_subMethod can accept an object, and auto upcast to the super
        //rather than needing a new method to accept each type of sub, just upcasting every argument passed in to be the parent
    //One of the most important uses of upcasting is when you have an ArrayList, List, LinkedList, etc, and you can make it of type Animal while adding all types of animals like Cats, Dogs and whatever
    public static void super_subMethod (Science science){   //method takes in any object of super or sub of Science class. Thi
        science.show();   //passing in a sub means this will invoke the subs method
        //science.explode();//but cannot use any subclass only methods, as this general method doesn't know what type of sub it will receive

        //DOWNCASTING: not done automatically, must be explicit.can throw errors
            //done here on the unspecified object passed in, made to be specifically as subclass
        if (science instanceof Chemistry) {  //must check that the agnostic argument actually is an object of the specific subclass before casting it, else get Exception
            Chemistry myChem = (Chemistry) science;   //now can treat this as a subclass object. But ONLY works if the object passed in actually is a Chemistry object
            myChem.explode(); //now can call subclass specific methods
                //block ensures you only call subspecific methods on the agnostic object if you checked the object actually is of that subclass
        }
    }
}

class Science{
    public void show(){
        System.out.println("I'm doing general science");}
}

class Chemistry extends Science{
    @Override
    public void show(){
        System.out.println("I'm doing chemistry");}

    public void explode(){
        System.out.println("chemistry exploding");}
}

class Physics extends Science{

}
