package Concepts;

import java.util.*;

/*Comparator vs Comparable:
    Comparable is good for defining the default sorting of a class, so use it for the main way to sort a class
    Comparator is better for:
        1. If cannot modify src of classes to sort, so cannot use Comparable
        2. Avoid complicating the domain class with extra code
        3. can define in anonymous class / lambda / method reference

 */
public class ComparatorDemo {
    public static void main(String[] args) {
        List<Chocolate> chocolates = Arrays.asList(new Chocolate("snickers", 6,40), (new Chocolate("reeces", 4,12)),
                (new Chocolate("butterfinger", 7,20)), (new Chocolate("aero", 9,30)),
                (new Chocolate("daim", 6,35)), (new Chocolate("boost", 9,32)), (new Chocolate("lion", 7,38)),
                (new Chocolate("double decker", 10,45)), (new Chocolate("bounty", 1,31)),
                (new Chocolate("kinder bueno", 5,15)));
        Collections.sort(chocolates, new compareChocolate());   //the customized compare() will be called for every iteration of the sort
        for (Chocolate chocolate : chocolates) {
            System.out.println(chocolate);
        }

        //define comparator with lambda
        Comparator<Chocolate> chocolateComparator = (Chocolate c1, Chocolate c2) -> Integer.compare(c1.getWeight(), c2.getWeight());
        chocolates.sort(chocolateComparator);
        System.out.println(chocolates);
        //define comparator with MR
        Comparator<Chocolate> chocolateComparatorComparingInt = Comparator.comparingInt(Chocolate::getWeight);
        chocolates.sort(chocolateComparatorComparingInt);
        System.out.println(chocolates);

    }

    public static class compareChocolate implements Comparator<Chocolate> {

        @Override
        public int compare(Chocolate o1, Chocolate o2) {
            if (o1.getDeliciousness()>=o2.getDeliciousness()){
                return -1;
            } else return 1;
        }
    }
}

class Chocolate{
    private String type;
    private int deliciousness;
    private int weight;

    public Chocolate(String type, int deliciousness, int weight) {
        this.type = type;
        this.deliciousness = deliciousness;
        this.weight = weight;
    }

    public String getType() {        return type;    }
    public void setType(String type) {        this.type = type;    }
    public int getWeight() { return weight;}
    public int getDeliciousness() {        return deliciousness;    }
    public void setDeliciousness(int deliciousness) {        this.deliciousness = deliciousness;    }
    public void setWeight(int weight) {this.weight = weight;}
    @Override
    public String toString() {    return "Chocolate{" +     "type='" + type + '\'' +     ", deliciousness=" + deliciousness +     "weight-" + weight +       '}';    }
}