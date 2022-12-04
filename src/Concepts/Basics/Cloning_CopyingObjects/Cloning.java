package Concepts.Basics.Cloning_CopyingObjects;

public class Cloning {
    public static void main(String[] args) throws CloneNotSupportedException {  //due to call to clone()
        Animal animal = new Animal();
        animal.setSpecies("Kangaroo"); animal.setLifespan(20);

        //Shallow Copy; does not create a new object as constructor has only run once. Just a reference copy; have 2 references to same object in heap
        Animal animal1 = animal;

        //Deep Copy; create 2nd object and manually copy each value across. Is slow
        Animal animal2 = new Animal();
        animal2.setSpecies(animal.getSpecies());
        animal2.setLifespan(animal.getLifespan());

        //Cloning - object class has inbuilt clone(), but it is protected so cannot be used outside Object class
        // so must override yourself for your classes and ensure they implement Cloneable
        Animal animal3 = (Animal) animal.clone();  //must cast the object returned by clone() to be Animal type
        System.out.println(animal3.getSpecies());



    }
}


class Animal implements Cloneable { //gives your class permission to call Object's clone() (as it makes vulnerable to hacking if instances with info can be cloned)
    private String species;
    private int lifespan;

    public String getSpecies() {return species;}
    public void setSpecies(String species) {this.species = species;}
    public int getLifespan() {return lifespan;}
    public void setLifespan(int weight) {this.lifespan = weight;}

    public Object clone() throws CloneNotSupportedException {   //because calling Object classes' clone() can throw this
        return super.clone();   //want to invoke the clone() of the superclass Object
    }
}