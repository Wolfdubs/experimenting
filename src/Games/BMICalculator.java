package Games;

import java.text.DecimalFormat;
import java.util.Scanner;

public class BMICalculator {
    // kg / m^2
    // (lb /in^2) * 703
    static double weight;
    static double height;

    public static void main(String[] args) {
        getUserInput();
        String bmi = calculateBMIImperial(height, weight);
        calculateBMIHealth(bmi);

    }

    public static void getUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your weight (lbs)");
        weight = scanner.nextDouble();
        System.out.println("Please enter your height (feet only (inches will come next))");
        int heightFeet = scanner.nextInt();
        System.out.println("Please enter your height (additional inches)");
        double heightInches = scanner.nextDouble();
        height = (heightFeet *12) + heightInches;
        scanner.close();
    }

    public static double calculateBMIMetric(double h, double w){
        double bmi = (w/((h/100)*(h/100)));
        System.out.println(bmi);
        return bmi;
    }

    public static String calculateBMIImperial(double h, double w){
        double bmi = ((w/ Math.pow(h,2)) * 703);
        DecimalFormat df = new DecimalFormat("0.0");
        String bmiFormatted = df.format(bmi);
      //  System.out.println(bmiFormatted);
        return bmiFormatted;
    }

    public static void calculateBMIHealth(String bmi){
        double bmiDouble = Double.parseDouble(bmi);
        if (bmiDouble < 19) System.out.println("A BMI of " + bmiDouble + " is underweight");
        if (bmiDouble >=19 && bmiDouble< 25) System.out.println("A BMI of " + bmiDouble + " is a healthy weight");
        if (bmiDouble >= 25 && bmiDouble < 30) System.out.println("A BMI of " + bmiDouble + " is overweight");
        else if (bmiDouble >= 30) System.out.println("A BMI of " + bmiDouble + " is obese");

    }

}














