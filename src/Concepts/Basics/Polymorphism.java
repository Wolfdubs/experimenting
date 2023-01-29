package Concepts.Basics;


import utils.Gun;
import utils.MedievalWeapons;
import utils.Weapon;

//compiler performs isA test;if declared type is a class, 'is a' tests if instantiated class is that class or a subclass
        //if declared class is an interface, is a checks if instantiated class / superclasses implement the interface
public class Polymorphism {
    public static void main(String[] args) {

        //objects can be declared as the superclass/implemented interface, and instantiated as the child/concrete class
        Weapon gun = new Gun("gun");
        Weapon medievalWeapon = new MedievalWeapons("mace", Weapon.TYPE.BLUNTFORCE);

        //Runtime resolution / dynamic binding : at runtime, the JVM works up the class chain to find the method implementation to invoke for an object
            //starting at instiated class, up superclasses/interfaces
        //method calls on the objects use the instantiated class implementation
        gun.useWeapon();
        medievalWeapon.useWeapon();


        //can make array of supertype that contains subtype elements
        Weapon[] weapons = {gun, medievalWeapon, new MedievalWeapons("spear", Weapon.TYPE.BLADED)};
        for (Weapon weapon : weapons) {
            weapon.useWeapon();
        }

        processWeapon(gun);

    }
    //polymorphism means 1 method can flexibly process objects of different classes, by accepting/returning the supertype
    private static void processWeapon(Weapon weapon) {
        System.out.println("the type of this object is " + weapon.getClass());
    }

}


