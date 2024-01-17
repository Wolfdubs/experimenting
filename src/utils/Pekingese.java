package utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ToString
@Getter
@Setter
public
class Pekingese {
    String name;
    int age;
    float weight;
    List<String> favoriteTreats;

    public Pekingese(String name, int age, float weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        favoriteTreats = new ArrayList<>();
    }
    public void incrementAge(){
        this.setAge(this.getAge()+1);
    }

    public List<String> getFavoriteTreats(){
        return favoriteTreats;
    }

    public static List<String> getFavoriteTreats(Pekingese pekingese){
        return pekingese.getFavoriteTreats();
    }
    public void setFavoriteTreats(String ...treats){
        favoriteTreats.addAll(Arrays.asList(treats));
    }


}
