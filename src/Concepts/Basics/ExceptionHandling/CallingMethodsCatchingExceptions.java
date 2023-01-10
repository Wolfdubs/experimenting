package Concepts.Basics.ExceptionHandling;

public class CallingMethodsCatchingExceptions {


    private int temperature;
    private boolean raining;
    private boolean snowing;

    public CallingMethodsCatchingExceptions(int temperature, boolean raining, boolean snowing) {
        this.temperature = temperature;
        this.raining = raining;
        this.snowing = snowing;
    }


    public void analyzeWeather() throws ImpossibleTempException, ImpossiblePrecipException {
        System.out.println("What's the weather today?");
        if (temperature < -50 || temperature > 60) {
            throw new ImpossibleTempException();
        }
        else if (temperature<-20){
            throw new IHateColdException();
        }

        else if (temperature < 0) {
            System.out.println("Chilly today!");
            if (raining) {
                throw new ImpossiblePrecipException();
              //  System.out.println("Raining?!?");   this would be an unreachable statement as exception thrown above means its never hit
            }
            if (snowing) {
                System.out.println("But the snow is nice!");
            }
        }
        else if (temperature <= 30) {
            System.out.println("Ahh, perfect weather.");
            if (raining) {
                System.out.println("What a shame!");
            }
            if (snowing) {
                throw new ImpossiblePrecipException();
            }
        }
        System.out.println("Enjoy the weather!");
    }

    public void getWeather() {
        try {
            analyzeWeather();
        } catch (ImpossibleTempException ite) {
            System.out.println("That's an impossible temperature!");
        } catch (ImpossibleException ie){
            System.out.println("This is the super exception so will encompass precip exception");
        } finally {
            System.out.println("Make sure to dress appropriately");
        }
    }

    public static void main(String[] args) {
        try {
            CallingMethodsCatchingExceptions cmce = new CallingMethodsCatchingExceptions(25, true, false);
            cmce.getWeather();
        } catch (IHateColdException ihce){  //as getWeather() doesn't catch this unchecked runtime exception, main should have code to deal with ti to prevent program crashing if temp < -20
            System.out.println("Cold weather sucks");
        }
    }

}

class ImpossibleException extends Exception{}

class ImpossibleTempException extends ImpossibleException{}

class ImpossiblePrecipException extends ImpossibleException{}

class IHateColdException extends RuntimeException{}
