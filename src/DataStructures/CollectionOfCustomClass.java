package DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
USING CUSTOM CLASSES WITH COLLECTIONS REQUIRES OVERRIDING THAT CLASSES EQUALS() AND HASHCODE()
else collection's get or contains() won't retrieve object matches as it calls the class' equals(), which uses default Object class == comparison
so override to do a values comparison so the hashtable will find matches it contains vs the argument passed to get()
To retrieve custom Objects from a Collection, must override the equals() and hashCode() methods for your custom object, else java will see that the object
    you pass in to search for isn't the same reference as the object in the table, so returns nothing and can throw exception
    because hashTables get() uses the equals() of the object to get its equivalent, so must override Object class' default to check the values
    must override equals() and hashCode() together as they work together -> so equal objects also have equal hashcodes
    e.g. check in 'implementing-object-oriented-design-lecture-starters' Congenial Hair Salon Starter code
Because the Collection's .get() & contains() is really doing a comparison between what it contains, and the argument passed in
when we decide on the criteria for two objects to be equal, we can use that information to do things like call contains() with a newly created object
    against a Collection
    Thus can create a new object, and then see if the Collection contains it (without ever having added the object), as contains() will call
    overridden equals() to see if Collection contains an object with the same chosen values to compare the objects with
*/
public class CollectionOfCustomClass {


    private Map<Treatment, ArrayList<String>> treatments = new HashMap<>();
    public CollectionOfCustomClass() {
        Treatment cut = new Treatment("cut", 1);
        Treatment colour = new Treatment("colour", 2);
        Treatment condition = new Treatment("condition", 3);

        addTreatment(cut);
        addTreatment(colour);
        addTreatment(condition);

        addTreatmentSpecialist(cut, "Navin");
        addTreatmentSpecialist(cut, "Mishal");
        addTreatmentSpecialist(cut, "Balaji");
        addTreatmentSpecialist(colour, "Prashant");
        addTreatmentSpecialist(colour, "Sonali");
    }

    static class Treatment {

        private String name;
        private int hoursNeeded;

        public Treatment(String name, int time) {
            this.name = name;
            this.hoursNeeded = time;
        }

        // getters
        public String getName() { return name; }
        public int getHoursNeeded() { return hoursNeeded; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Treatment treatment = (Treatment) o;
            return hoursNeeded == treatment.hoursNeeded && Objects.equals(name, treatment.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, hoursNeeded);
        }
    }



    public static void main(String[] args) {
        CollectionOfCustomClass hswcc = new CollectionOfCustomClass();
        //if Treatment class only has Object's default equals(), below will return false,
        //  because the key passed in is a different object to the one in the collection
        //with overriden equals, it returns true, because it checks for value equality
        System.out.println(hswcc.treatments.containsKey(new Treatment("cut", 1)));
        System.out.println(hswcc.treatments.containsKey(new Treatment("shave", 99)));
    }

    public void suggestConsultant(Treatment treatment) {
        System.out.print("May we suggest you work with: \n");
        ArrayList<String> consultants = treatments.get(treatment);
        for (String consultant : consultants){
            System.out.println(consultant + "?");
        }
    }

    private void addTreatment(Treatment treatment){
        treatments.put(treatment, new ArrayList<>());
    }

    private void addTreatmentSpecialist(Treatment t, String name){
        ArrayList<String> consultants = treatments.get(t);
        consultants.add(name);  //don't need to do put() of this consultants list back into the map, as the add() operates directly on the object reference
    }
}
