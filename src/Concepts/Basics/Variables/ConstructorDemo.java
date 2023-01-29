package Concepts.Basics.Variables;

public class ConstructorDemo {

    //Chaining constructors = best practice with constructor overloading, because DRY
    //less specific constructor calls more specific one to set other fields
    private class Pekingse {
        protected String name;
        int age;
        protected double weight;

        public Pekingse() {
            this("");   //calls the String parameterized constructor, via this() which refers to current object the constructor is initializing
        }

        public Pekingse(String name){
            this(name, 0, 0);  //calls the most specific parameter
        }

        public Pekingse(String name, int age, double weight) {
            this.name=name;
            this.age = age;
            this.weight = weight;
        }

    }

    private class PekingesePets extends Pekingse {

        public PekingesePets(){  //if you don't invoke super, java automatically calls the parameterless constructor of superclass
            System.out.println("child constructor run");
        }

        public PekingesePets(String name) {
            super(name);
        }
    }
}
