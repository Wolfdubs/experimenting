package Concepts.Basics.Generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class GenericListCreationMethod {

    private static <T> List<T> getObjectList(int length, Supplier<T> objectSupply) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(objectSupply.get());
        }
        return list;
    }

    public static void main(String[] args) {
        List<RandomStuff> programmingLanguages = getObjectList(10, RandomStuff::new);
    }

    static class RandomStuff{
        String randomName;
        int randomNumber;

        public RandomStuff(){
            this.randomName = new Random().ints(97, 123)
                    .limit(7)
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint,
                            StringBuilder::append)
                    .toString();
            this.randomNumber = new Random().ints(0,1000)
                    .limit(3)
                    .sum();
        }
    }
}
