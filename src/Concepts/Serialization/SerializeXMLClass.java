package Concepts.Serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Stores object data in xml file
normal serialization drawback: if class changes between when you serialized vs creating new objects from it
storing data in xml format, which can also then be sent between machines
xml stored data is also human readable, unlike normal plain Serialized data
 */
public class SerializeXMLClass {
    public static void main(String[] args) throws FileNotFoundException {
        Game g1 = new Game();
        g1.setPlayers(4); g1.setName("monopoly");
        Game g2 = new Game();
        g2.setPlayers(5); g2.setName("Scrabbled");
        Game g3 = new Game();
        g3.setPlayers(2); g2.setName("Chess");

        List<Game> list = new ArrayList<>();
        list.add(g1); list.add(g2); list.add(g3);

        GamesList gamesList = new GamesList();
        gamesList.setGames(list);


        //Serializing Game class
        //Create XML encoder
        XMLEncoder x = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("gamesList.xml")));
        x.writeObject(gamesList);   //writing to gamesList object to the XMLEncoder object
        x.close();


        //Deserializing stored object data from XML
        XMLDecoder xd = new XMLDecoder(new BufferedInputStream(new FileInputStream("gamesList.xml")));
        GamesList gl = (GamesList) xd.readObject();
        xd.close();
        List<Game> games = gl.getGames();
        games.forEach(System.out::println);   //will call the toString() of each Game object
    }

}


class Game {
    private int players;
    private String name;

    public int getPlayers() {return players;}
    public void setPlayers(int players) {this.players = players;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {return "Games{" + "players=" + players + ", name='" + name + '\'' + '}';}
}

class GamesList {
    private List<Game> games;

    public List<Game> getGames() {return games;}
    public void setGames(List<Game> games) {this.games = games;}
}



