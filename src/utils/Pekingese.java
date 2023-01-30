package utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public
class Pekingese {
    String name;
    int age;
    float weight;

    public Pekingese(String name, int age, float weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
    public void incrementAge(){
        this.setAge(this.getAge()+1);
    }
}
