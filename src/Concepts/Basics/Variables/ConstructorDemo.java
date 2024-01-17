package Concepts.Basics.Variables;

public class ConstructorDemo {

    //Chaining constructors = best practice with constructor overloading, because DRY
    //less specific constructor calls more specific one to set other fields
    private static class Pekingese {
        protected String name;
        int age;
        protected double weight;

        public Pekingese() {
            this("");   //calls the String parameterized constructor, via this() which refers to current object the constructor is initializing
        }

        public Pekingese(String name){
            this(name, 0, 0);  //calls the most specific parameter
        }

        public Pekingese(String name, int age, double weight) {
            this.name=name;
            this.age = age;
            this.weight = weight;
        }

    }

    private static class PekingesePets extends Pekingese {

        public PekingesePets(){  //if you don't invoke super, java automatically calls the parameterless constructor of superclass
            System.out.println("child constructor run");
        }

        public PekingesePets(String name) {
            super(name);
        }
    }

    //have a method that contains a call to the constructor, so user can create an object via this method call
    private static Pekingese createPekingese(String name, int age, double weight){
        return new Pekingese(name, age, weight);
    }

    public static void main(String[] args) {
        Pekingese myPekingese = createPekingese("womble", 13, 10);
        ConstructorDemo cd = new ConstructorDemo();
        cd.createPekingese("mungo",12,1);
        ConstructorDemo.PekingesePets pp = new ConstructorDemo.PekingesePets("mungo");
    }
}
