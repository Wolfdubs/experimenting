package Games;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.NavigableMap;
import java.util.Scanner;

public class MortgageCalculator {

    private static final int MONTHS_IN_A_YEAR = 12;

    //user inputs the principal, interest rate, and term
    //output monthly repayment & net repayment

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean validPrincipal = false;
        Double principal = 0.0;
        do { System.out.println("Enter the principal");
            String principalString = scanner.nextLine();
            try { principal = Double.parseDouble(principalString);
                validPrincipal = true;
            } catch (NumberFormatException ime) {
                System.out.println("Principal must be an double");
            }
        } while (!validPrincipal);

        boolean validInterestRate = false;
        Double annualInterest = 0.0;
        do{ System.out.println("Enter the annual interest rate");
            String interestString = scanner.nextLine();
            try{
                annualInterest = Double.parseDouble(interestString);
                annualInterest = annualInterest/100;  //to make it a %
                validInterestRate = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Interest rate must be a double");
            }
        } while (!validInterestRate);

        boolean validTerm = false;
        Integer term = 0;
        do {
            System.out.println("Enter the term");
            String termString = scanner.nextLine();
            try {
                term = Integer.parseInt(termString);
                validTerm = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Term must be an integer");
            }
        } while (!validTerm);

        Double monthlyRepayment = calculateMonthlyRepayment(principal, calculateMonthlyInterestRate(annualInterest),
                totalNumberOfMonthlyPayments(term));
        String monthlyRepaymentFormatted = calculateMonthlyRepaymentFormatted(monthlyRepayment);
        System.out.println(monthlyRepaymentFormatted);

        String totalRepayment = calculateTotalRepaymentAmount(monthlyRepayment, totalNumberOfMonthlyPayments(term));
        System.out.println(totalRepayment);


    }

    private static double calculateMonthlyInterestRate(double annualRate){
        //mir = (1+air)^(1/12) -1
        //return Math.pow((1 + annualRate), (1.0/12.0)) -1;
        return annualRate/MONTHS_IN_A_YEAR;
    }

    private static int totalNumberOfMonthlyPayments(int term){
        return term * MONTHS_IN_A_YEAR;
    }

    private static double calculateMonthlyRepayment(double principal, double monthlyInterest, int paymentsCount){
        //M = P * ( (i(1+i)^n) / ((1+i)^n -1)
        double monthlyRepayment = principal * (
                (monthlyInterest * (Math.pow(1+monthlyInterest, paymentsCount)))
                / ((Math.pow(1 + monthlyInterest,paymentsCount)) -1));
        return monthlyRepayment;
    }

    private static String calculateMonthlyRepaymentFormatted(double monthlyRepayment){
        return NumberFormat.getCurrencyInstance().format(monthlyRepayment);
    }

    private static String calculateTotalRepaymentAmount(Double monthlyRepayment, int paymentsCount){
        return NumberFormat.getCurrencyInstance().format(monthlyRepayment * paymentsCount);

    }
}
