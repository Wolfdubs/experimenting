package Games;

public class bmiCalculator {

    public static double calculateBMI(double height, double weight){
        double bmi = (weight/((height/100)*(height/100)));
        System.out.println(bmi);
        return bmi;
    }

}
