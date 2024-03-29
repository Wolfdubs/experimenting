package DesignPatterns;
/*
    The builder pattern is a good choice when designing classes whose constructors or static factories would have more than a handful of parameters.
    We've all at some point encountered a class with a list of constructors where each addition adds a new option parameter:

    Pizza(int size) { ... }
    Pizza(int size, boolean cheese) { ... }
    Pizza(int size, boolean cheese, boolean pepperoni) { ... }
    Pizza(int size, boolean cheese, boolean pepperoni, boolean bacon) { ... }
    This is called the Telescoping Constructor Pattern. The problem with this pattern is that once constructors are 4 or 5 parameters long it
    becomes difficult to remember the required order of the parameters as well as what particular constructor you might want in a given situation.

    One alternative you have to the Telescoping Constructor Pattern is the JavaBean Pattern where you call a constructor with the mandatory
    parameters and then call any optional setters after:

    Pizza pizza = new Pizza(12);
    pizza.setCheese(true);
    pizza.setPepperoni(true);
    pizza.setBacon(true);
    The problem here is that because the object is created over several calls it may be in an inconsistent state partway through its
    construction. This also requires a lot of extra effort to ensure thread safety.

    The better alternative is to use the Builder Pattern.

    The below code is easy to write and very easy to read and understand.
    In this example, the build method could be modified to check parameters after they have been copied from the builder to the
    Pizza object and throw an IllegalStateException if an invalid parameter value has been supplied. This pattern is flexible and
    it is easy to add more parameters to it in the future. It is really only useful if you are going to have more than 4 or 5 parameters for
    a constructor. That said, it might be worthwhile in the first place if you suspect you may be adding more parameters in the future.
     */

public class BuilderPattern {

    public static void main(String[] args) {
        Pizza pizza = new Pizza.Builder(12)
                .cheese(true)
                .pepperoni(true)
                .bacon(true)
                .build();
    }
}

class Pizza {
    private int size;
    private boolean cheese;
    private boolean pepperoni;
    private boolean bacon;

    static class Builder {
        //required
        private final int size;

        //optional
        private boolean cheese = false;
        private boolean pepperoni = false;
        private boolean bacon = false;

        public Builder(int size) {
            this.size = size;
        }

        public Builder cheese(boolean value) {
            cheese = value;
            return this;
        }

        public Builder pepperoni(boolean value) {
            pepperoni = value;
            return this;
        }

        public Builder bacon(boolean value) {
            bacon = value;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    private Pizza(Builder builder) {
        size = builder.size;
        cheese = builder.cheese;
        pepperoni = builder.pepperoni;
        bacon = builder.bacon;
    }
        /*
        Note that Pizza is immutable and that parameter values are all in a single location.
        Because the Builder's setter methods return the Builder object they are able to be chained.
         */
}
