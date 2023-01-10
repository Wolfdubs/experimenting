package DataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/*
Collections operate directly on object references (not for primitives through(?), so if you update an object values, dont need to put it back again
 */
public class Collections {

    static class GuideLeader{
        private String name;
        public GuideLeader(String name){
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GuideLeader that = (GuideLeader) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    static class GirlGuide{
        private String name;
        public GirlGuide(String name){
            this.name = name;
        }
    }

    public static void main(String[] args) {
        HashMap<GuideLeader, ArrayList<GirlGuide>> troops = new HashMap<>();

        //set up guiders
        GuideLeader vanessa = new GuideLeader("Vanessa");
        GuideLeader trixie = new GuideLeader("Trixie");
        GirlGuide chloe = new GirlGuide("Chloe");
        GirlGuide kira = new GirlGuide("Kira");

        //set up troops
        troops.put(vanessa, new ArrayList<GirlGuide>());
        troops.put(trixie, new ArrayList<GirlGuide>());

        //get GirlGuides into troops
        ArrayList<GirlGuide> fortyNinthGuides = troops.get(trixie);
        fortyNinthGuides.add(chloe); //don't then need to put() back into the map, as the add() will operate directly on the object already
                                     // retrieved from inside the map from prior lines get()
        troops.put(vanessa,fortyNinthGuides);    //this then also puts a reference to that same object in the map under the vanessa object key
        fortyNinthGuides.add(kira);            //thus is added to the arraylist for both keys in the map
        troops.get(trixie).remove(chloe);     //and thus is removed from the arraylist for both keys that refer to the same object map value
        ArrayList<GirlGuide> eighteenthGuides = troops.get(new GuideLeader("Trixie"));   //uses the overriden equals() to retrieve the list for the key equivalent to the new object passed in

    }
}

