package Concepts.Basics.ExceptionHandling;

/*useful for testing
Junit does not have access to private members of a class, but using assertions does as you put them directly in the code to check states
e.g. put at beginning and end of private methods to check they run correctly
if the assertion fails, java will throw an assertion exception
should turn them off for production so they don't slow performance
    >edit configurations, >VM options >type in: -ea
*/
public class Assertions {

    private int counter;
    public int getCounter() {        return counter;    }
    public void setCounter(int counter) {        this.counter = counter;    }
    protected boolean myBool = false;

    public static void main(String[] args) {
        Assertions assertions = new Assertions();
        assertions.setCounter(5);
        assert(assertions.getCounter()==5 && (!assertions.myBool));
        assertions.increaseCounter(10);
        assertions.myBool = true;
        assert(assertions.getCounter()==15 && assertions.myBool);  //if it said ==14, it throws assertion exception
    }

    public void increaseCounter(int increase){
        counter+=increase;
    }

}
