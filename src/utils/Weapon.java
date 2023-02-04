package utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.Weapon.TYPE.*;
import static utils.Weapon.TYPE.BLADED;

@Getter (AccessLevel.PUBLIC)
@Setter (AccessLevel.PUBLIC)
@ToString
public class Weapon {
    private TYPE TYPE;
    String name;
    public enum TYPE {
        BLUNTFORCE, BLADED, FIREARM, EXPLOSIVE, GASPOWERED, BIOLOGICAL, CHEMICAL, NUCLEAR, BOW
    }
    boolean handToHand;
    boolean singlePerson;
    int lethality;
    int selfSustainability;
    int difficulty;
    double antiqueValue;


    public Weapon(String name, Weapon.TYPE type) {
        this.name = name;
        TYPE = type;
    }

    public Weapon(String name, Weapon.TYPE type, boolean handToHand, boolean singlePerson, int lethality, int selfSustainability, int difficulty) {
        this.name = name;
        TYPE = type;
        this.handToHand = handToHand;
        this.singlePerson = singlePerson;
        if (lethality<=10 && lethality>=0) {
            this.lethality = lethality;
        } else {
            this.lethality = -1;
        }
        if (selfSustainability<=10 && selfSustainability>=0) {
            this.selfSustainability = selfSustainability;
        } else {
            this.selfSustainability = -1;
        }
        if (difficulty<=10 && difficulty>=0) {
            this.difficulty = difficulty;
        } else {
            this.difficulty = -1;
        }
    }

    public static List<Weapon> generateWeaponsList(){
        List<Weapon> weapons = List.of(
                new Weapon("axe", BLUNTFORCE, true, true, 4, 10, 1),
                new Weapon("M16", FIREARM, false, true, 5, 5, 2 ),
                new Weapon("chainsaw", GASPOWERED, true, true, 4, 5, 2),
                new Weapon("anthrax", BIOLOGICAL, false, false, 6, 2, 10),
                new Weapon("missile", EXPLOSIVE, false, false, 8, 3, 8),
                new Weapon("nuke", NUCLEAR, false, false, 10, 1, 10),
                new Weapon("crossbow", BOW, false, true, 4, 8, 4),
                new Weapon("katana", BLADED, true, true, 5,9, 5));
        return weapons;
    }


    public static Map<String,Integer> generateWeaponsMap(){
        List<Weapon> weapons = generateWeaponsList();
        Map<String, Integer> weaponNameToLethalityMap = weapons.stream()
                .collect(Collectors.toMap(Weapon::getName, Weapon::getLethality
                        ,(key, duplicateKey) -> key));
        return weaponNameToLethalityMap;
    }

    public void useWeapon(){
        System.out.println("weapon was just used");
    }


}
