package Concepts;

import java.util.*;

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Chocolate> chocolates = Arrays.asList(new Chocolate("snickers", 6), (new Chocolate("reeces", 4)),
                (new Chocolate("butterfinger", 7)), (new Chocolate("aero", 9)),
                (new Chocolate("daim", 6)), (new Chocolate("boost", 9)), (new Chocolate("lion", 7)),
                (new Chocolate("double decker", 10)), (new Chocolate("bounty", 2)),
                (new Chocolate("kinder bueno", 5)));
        Collections.sort(chocolates, new compareChocolate());   //the customized compare() will be called for every iteration of the sort
        for (Chocolate chocolate : chocolates) {
            System.out.println(chocolate);
        }
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

    public Chocolate(String type, int deliciousness) {
        this.type = type;
        this.deliciousness = deliciousness;
    }

    public String getType() {        return type;    }
    public void setType(String type) {        this.type = type;    }
    public int getDeliciousness() {        return deliciousness;    }
    public void setDeliciousness(int deliciousness) {        this.deliciousness = deliciousness;    }
    @Override
    public String toString() {    return "Chocolate{" +     "type='" + type + '\'' +     ", deliciousness=" + deliciousness +            '}';    }
}