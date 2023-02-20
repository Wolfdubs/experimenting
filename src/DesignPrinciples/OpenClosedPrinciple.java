package DesignPrinciples;

import utils.Gun;
import utils.Pekingese;
import utils.Weapon;

public class OpenClosedPrinciple {
    /*
    "A class should be open for extension, but closed for modification"
    so can extend a classes behavior without modifying it
    e.g. create an interface that each class implements and override the abstract method with each class' implementation
        so now if a new category arises, only have to create a new class implementing the interface, not amending many different files
     */

    private interface WeaponRatingCalculator {
        double calculateRating(Weapon weapon);
    }
    private static class GunRatingCalculator implements WeaponRatingCalculator {

        @Override
        public double calculateRating(Weapon weapon) {
            return (double) (weapon.getLethality() + weapon.getSelfSustainability())/2;
        }
    }

    private static class BladeRatingCalculator implements WeaponRatingCalculator {
        @Override
        public double calculateRating(Weapon weapon) {
            return ((weapon.getAntiqueValue()*10) + weapon.getLethality() - (double) weapon.getDifficulty()/5) /3;
        }
    }

    private static class CBRNRatingCalculator implements WeaponRatingCalculator {

        @Override
        public double calculateRating(Weapon weapon) {
            return weapon.getLethality() - weapon.getDifficulty();
        }
    }
}
