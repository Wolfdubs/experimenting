package Concepts.Basics.InputOutput;

import utils.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import static utils.Weapon.generateWeaponsList;

public class CreatingFile {
    public static void main(String[] args) {
        List<Weapon> weapons = generateWeaponsList();
        File fileOut = new File("Weapons.csv");
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(fileOut);
            for (Weapon weapon : weapons) {
                printWriter.println(weapon.getName() + "," + weapon.getTYPE());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        finally {
            printWriter.close();
        }
    }
}
