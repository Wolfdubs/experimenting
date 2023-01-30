package DesignPrinciples;

import lombok.Getter;
import lombok.Setter;

public class InterfaceSegregationPrinciple {
    /*
    "Many specific interfaces are better than 1 general interface"
    interfaces with many behaviors are difficult to maintain and evolve
    e.g. with Video class, where PremiumVideo class doesn't have playAd(), a Video interface is bad because PremiumVideo still must override the method
        so same unweildliness as inheritance
    So use multiple interfaces; 1 has universal video methods, 1 has ad-specific methods
        Video implements both, PremiumVideo only the universal interface
     */

    interface VideoActions {
        String title = null;
        int likes = 0;
        int views = 0;
        int length = 0;

        default double getHoursPlayed() {
            return (length / 3600.0) * likes;
        }

        double calculateEarnings();
    }

    interface AdsActions {
        void playAd();
    }

    @Getter
    @Setter
    private class Video implements VideoActions, AdsActions {

        @Override
        public double calculateEarnings() {
            return (likes * 0.012 + views * 0.0012);
        }

        @Override
        public void playAd() {
            System.out.println("Introducing the new fluffy pekingese....");
        }

        private class PremiumVideo implements VideoActions {

            @Override
            public double calculateEarnings() {
                return (likes * 0.013 + views * 0.0013);
            }
        }
    }
}
