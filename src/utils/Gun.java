package utils;

public class Gun extends Weapon{

    public Gun(String name) {
        super(name, TYPE.FIREARM);
    }
    public void useWeapon(){
        System.out.println("gun was just fired");
    }
}
