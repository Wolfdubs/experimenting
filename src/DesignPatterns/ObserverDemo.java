package DesignPatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/*
communicate change in 1 class object to other class objects
    many-to-one relationship; 1 class is changed and feeds this update info to other classes
    e.g. in gUI, document sends changes to child nodes to rerender
Java: can use built in Observer class & Observable interface
 */
public class ObserverDemo extends Observable {

    public void cookFood(){
        setChanged();
        notifyObservers();
    }

}

class Ingredient implements Observer{

    private String name;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(getName() + " has been cooked");
    }

    public Ingredient(String name){
        super();
        this.name=name;
    }

    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }

    @Override
    public String toString() {        return "Ingredients{" +                "name='" + name + '\'' +                '}';    }
}

class Runner{
    public static void main(String[] args) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("sugar")); ingredients.add(new Ingredient("flour")); ingredients.add(new Ingredient("salt"));

        ObserverDemo kitchen = new ObserverDemo();
        for (Ingredient ingredient : ingredients){
            kitchen.addObserver(ingredient);    //ingredients can be passed into addObserver because they implement Observable interface
        }
        kitchen.cookFood();   //the Observable extending class thus cals notify observers, which inside the observers calls update()
    }
}
