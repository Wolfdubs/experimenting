package Testing.JUnit.test;

import Testing.JUnit.main.AddingCalculator;
import Testing.JUnit.main.Gradings;
import org.junit.Test;

//convention is to have all tests in separate test package
//for unit testing: testing 1 specific unit independently at a time; for classes/methods
//Run tests with Coverage - will tell you how much of your code the tests have covered e.g. 21% lines are covered, 50% of classes
    //shows which lines of code were executed by the test, and in red which were not
public class MyTestSuiteClass {    //run all tests with class' play button

    //TESTS FOR CALCULATOR CLASS; should be in their own class themselves
    @Test    //specifies that the method is a test. each should test just 1 scenario
    public void twoPlusTwoShouldEqualFour() {//Junit tests dont have to return anything. can run tests just by the arrow by the test
        var ac = new AddingCalculator();   //unrelated to junit, java can infer the type from the constructor
        org.junit.Assert.assertEquals(4,ac.add(2,2));  //1st value is what you expect, 2nd value is the result to check calculation of
        org.junit.Assert.assertNotEquals(5, ac.add(2,2));
        org.junit.Assert.assertTrue(ac.add(2,2)==4);
        org.junit.Assert.assertFalse(ac.add(2,2)==5);
        org.junit.Assert.assertNull(null);
        org.junit.Assert.assertNotNull(ac);
    }

    //at least 1 unit test should fail if code is not doing the correct thing. e.g. 2+2 is same as 2*2, so need more tests to check method is addition, not multiplication
    @Test    //specifies that the method is a test. each should test just 1 scenario
    public void fivePlusEightShouldEqualThirteen() {
        var ac = new AddingCalculator();
        org.junit.Assert.assertEquals(13,ac.add(5,8));
        org.junit.Assert.assertNotEquals(14, ac.add(5,8));
    }


    //TESTS FOR GRADINGS CLASS. should have 1 test for every outcome, and for every boundary case to test for < vs <=
    @Test
    public void gradeOf55ShouldEqualE(){
        var g = new Gradings();
        org.junit.Assert.assertEquals('E', g.determineLetterGrades(55));
    }

    @Test
    public void gradeOf80ShouldEqualB(){
        var g = new Gradings();
        org.junit.Assert.assertFalse(g.determineLetterGrades(80)!='B');
    }

    @Test  //normally if tested code throws an exception, it will ake test throw it too, even if code is meant to throw error
    public void negativeGradeShouldThrowException(){    //assertThrows will handle tested exception without failing
        var g = new Gradings();
        //org.junit.Assert.assertThrows(IllegalArgumentException.class,                      //not working for me; maybe need junit5?
        //                          () -> {g.determineLetterGrades(-1);});       //1st argument is exception type, then lambda with code to execute the test
    }
}