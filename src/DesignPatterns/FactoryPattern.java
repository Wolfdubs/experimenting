package DesignPatterns;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Supplier;

public class FactoryPattern {

    public static void main(String[] args) {
        Dog dog = factory(Dog::new);
        Dog dog2 = factory(() -> new Dog("mungo"));

    }
    private static Dog factory(Supplier<? extends Dog> supplier) {
        Dog dog = supplier.get();
        if (dog.getName() == null || dog.getName().equals("")){
            dog.setName("default");
        }
        dog.setCost(BigDecimal.ONE);
        dog.setBirthday(LocalDate.of(2010,01,01));

        return dog;
    }

    @Getter
    @Setter
    private static class Dog {
        String species;
        String name;
        double weight;
        int age;
        BigDecimal cost;
        LocalDate birthday;

        public Dog(){}  // for factory(Dog::new)

        public Dog(String name) {  //for factory(() -> New Dog("womble");
            this.name = name;
        }


    }
}


