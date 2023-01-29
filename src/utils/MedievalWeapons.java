package utils;

public class MedievalWeapons extends Weapon{
    public MedievalWeapons(String name, Weapon.TYPE type) {
        super(name, type);
    }
    public void useWeapon(){
        System.out.println(this.getTYPE() + " weapon was just used");
    }
}
