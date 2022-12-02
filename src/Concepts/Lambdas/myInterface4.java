package Concepts.Lambdas;

@FunctionalInterface //option best-practice annotation to say this is a functional interface, ie it has only 1 SAM. means compiler will not let you add another SAM
                    //can still have other static & default methods though

public interface myInterface4 {

    public int InterfaceMethod4(int i, int j);

}
