package Games;

import java.text.DecimalFormat;
import java.util.Scanner;

public class GPACalculator {
    //gpa = total points / total credits
    //points for class = grade * credits
    //A = 4, B = 3

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer classPoints;
        Integer classCreditsValue = 0;
        Integer totalPoints = 0;
        Integer totalCredits = 0;
        boolean moreClassesToEnter;
        do {
            boolean validCredits = false;
            do {
                System.out.println("Enter the credits allocated for the class");
                String classCredits = scanner.nextLine();
                try {
                    classCreditsValue = Integer.parseInt(classCredits);
                    validCredits = true;
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid integer");
                }
            } while (!validCredits);

            Integer classGradeValue;
            boolean validGrade;
            do {
                System.out.println("Enter the grade achieved for the class");
                String classGrade = scanner.nextLine();
                classGradeValue = switch (classGrade) {
                    case "A", "a": yield 4;
                    case "B", "b": yield 3;
                    case "C", "c": yield 2;
                    case "D", "d": yield 1;
                    case "E", "e": yield 0;
                    default:  yield -1;
                };
                if (classGradeValue == -1){
                    System.out.println("Please enter a valid grade A-E");
                    validGrade = false;
                } else validGrade = true;
            } while (!validGrade);

            classPoints = classGradeValue * classCreditsValue;
            totalPoints += classPoints;
            totalCredits+=classCreditsValue;

            System.out.println("Do you have more classes to add? (Y/N)");
            moreClassesToEnter = scanner.nextLine().equalsIgnoreCase("Y");
        } while (moreClassesToEnter);

        DecimalFormat df = new DecimalFormat("0.00");   //java class to specify number of decimal places
        Double GPA = ((double) totalPoints / (double) totalCredits);
        String roundedGPA = df.format(GPA);
        System.out.println("Total points = " + totalPoints + "\nTotal credits = " + totalCredits + "\nGPA = " + roundedGPA);
        scanner.close();
    }

}
