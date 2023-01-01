package Concepts.Basics.Variables;


//for creating custom ranges e.g. months, colors, mammals of things that wont change
//better than defining constant variables inside interface, as caller won't know the type the interface has defined the variable as
//in back-end, java creates a class in which each enum value is created as a static final object
// static is why to refer to the enum requires the enum class name too e.g. EnumDemo.MULE
public enum EnumDemo {   //enum cannot extend any class, as it by default extends Enum class, but it can implement any interface
    HORSE, DONKEY(400), MULE, BURRO;     //DONKEY has been created using overloaded constructor. This declaration line auto instantiates each enum
    //can also create variables & methods inside enum
    private int price;
    public void setPrice(int price) {this.price = price;}
    public int getPrice() {return price;}

    //can also set a constructor that applies as the default for every object of the enum
    //constructor is automatically called for every enum you have, even if you don't use/create them
    EnumDemo(){   //for enum constructor, don't use public
        this.price = 100;    //sets a default value for each enum
        System.out.println("constructor has been called");}

    EnumDemo(int price){    //can overload constructors, and then define enums using this one
        this.price = price;}
}

class EnumClassDemo{
    public static void main(String[] args) {
        System.out.println(EnumDemo.HORSE);

        //can use enum in switch statement
        EnumDemo animal = EnumDemo.DONKEY;
        switch (animal) {
            case HORSE:
                System.out.println("Horse Time");
                break;
            case DONKEY:
                System.out.println("Donkey Time");
                break;
            case MULE:
                System.out.println("Mule Time");
                break;
            case BURRO:
                System.out.println("Burro Time");
                break;
        }

        //can call methods from enum
        System.out.println(EnumDemo.MULE.getPrice());       //MULE object will be created with default price set in constructor
        System.out.println(EnumDemo.DONKEY.getPrice());
        EnumDemo.BURRO.setPrice(300);
        System.out.println(EnumDemo.BURRO.getPrice());

        //enums have many built in methods too, because every enum extends Enum class, which implements Comparable & Serializable
        System.out.println(EnumDemo.HORSE.ordinal());  //prints what position in the sequence of enums it is
        System.out.println(EnumDemo.BURRO.ordinal());
        //thus can fetch all the enums into an array
        EnumDemo animals[] = EnumDemo.values();  //values() comes from compiler, not from enum class



    }
}
