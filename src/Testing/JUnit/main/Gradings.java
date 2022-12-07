package Testing.JUnit.main;

public class Gradings {

    public char determineLetterGrades(int score){
        if (score < 0){
            throw new IllegalArgumentException("Score cannot be negative");
        }
        else if (score < 50){
            return 'F';
        }
        else if (score < 60){
            return 'E';
        }
        else if (score < 70){
            return 'D';
        }
        else if (score < 80){
            return 'C';
        }
        else if (score < 90){
            return 'B';
        }
        else return 'A';
    }
}
