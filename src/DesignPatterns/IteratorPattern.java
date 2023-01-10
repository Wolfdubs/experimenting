package DesignPatterns;

import java.math.BigInteger;
import java.util.*;

/*
to abstract out logic to iterate over a collection;
    each collection needs own iterator as each has own structure; custom iterator has logic to traverse that structure
Inbuilt java collection iterators implement the Iterator Interface, specifying next() and hasNext() methods, an implement the Iterable interface (whose
    method iterator() returns a reference to the correct iterator for the collection
    Java collections all implement Iterable interface, whose only method is to return a dedicated iterator() to the caller trying to iterate over
        the collection
        That iterator contains hasNext() and next()
Classes implementing Iterable interface can be used in foreach loops, because foreach is actually turned into an iteration
An Iterator has 3 components;
    cursor to track where it is in a collection
    boolean hasNext()
    Element getNext()
Only ever create an iterator for a collection of objects
Inbuilt Iterable & Iterator interfaces:
    Iterable:
        classes implementing, get ability to iterate over objects of that class using a class that implementing Iterator
        represents a data structure that can be iterated over
        provides a method that produces an iterator
            make a custom one via call to custom made: new CustomIterator() as an inner class that implements inbuilt ITERATOR INTERFACE
    all collections implement this
    foreach loop & lambda forEach() only possible with collections that implement iterable
    Iterator stores the iteration state (cursor to track where it is in the collection)

 */
public class IteratorPattern {

    private List<String> stringList = new ArrayList<>();

    public void forEachLoop() {
        for (String s : stringList) {
            System.out.println(s.length());
        }
    }

    public void underlyingIteratorLoops() {
        Iterator<String> iterator = stringList.iterator();
        while (iterator.hasNext()) {
            String string = iterator.next();
            System.out.println(string.length());

        }
    }


    static class Song {
        String name;
        double runtime;
        int sales;

        public Song(String name, double runtime, int sales) {
            this.name = name;
            this.runtime = runtime;
            this.sales = sales;
        }
    }

    static class Album implements Iterable<Song> {
        String name;
        List<Song> songs;

        public Album(String name) {
            this.name = name;
            songs = new ArrayList<>();
        }

        protected void addSong(Song s) {
            if (!songs.contains(s)) songs.add(s);
        }

        @Override
        public Iterator<Song> iterator() {
            return songs.iterator();  //uses the builtin iterator() that all ArrayLists have, as we just iterate over this internal Arraylist
        }
    }

    static class Musician {
        static Album album = new Album("americanAnthems");

        public static void showAlbumSongs() {
            for (Song s : album) {  //to make foreach applicable to custom collection, must implement Iterator interface
                System.out.printf("\nFOREACH: Song name = %s, Song runtime = %s, Song sales = %s", s.name, s.runtime, s.sales);
            }

            //above foreach is equivalent to:
            Iterator<Song> albumIterator = album.songs.iterator();    //calls iterator() on the collection of the Iterator object, Album must implement iterable()
            while (albumIterator.hasNext()) {
                Song currentSong = albumIterator.next();
                System.out.printf("\nITERATOR: Song name = %s, Song runtime = %s, Song sales = %s", currentSong.name, currentSong.runtime, currentSong.sales);

            }
        }
        //IMPLEMENTATION JUST BY SETTING THE CUSTOM ITERATOR FOR COLLECTION AS THE INBUILT ONE FOR ARRAYLIST
        public static void main(String[] args) {
            Song starSpangledBanner = new Song("starSpangledBanner", 1.5, 8000000);
            Song myCountryTisOfThee = new Song("myCountryTisOfThee", 2.3, 1500000);
            Song godBlessTheUSA = new Song("godBlessTheUSA", 3.9, 4000000);
            album.addSong(starSpangledBanner); album.addSong(myCountryTisOfThee); album.addSong(godBlessTheUSA);
            showAlbumSongs();
        }
    }
}




//CREATING CUSTOM ITERATOR OBJECT LOGIC
/*
Implement as an inner class of the collection, so it has access to all its fields
    inner iterator class implements iterator interface and defines next() and hasNext(), and keeps a pointer of current position
 */

class Movie {
    String name;
    double runtime;
    int boxOffice;

    public Movie(String name, double runtime, int boxOffice) {
        this.name = name;
        this.runtime = runtime;
        this.boxOffice = boxOffice;
    }
}

class DVDCollection implements Iterable<Movie>{
    double value;
    String owner;
    List<Movie> movies;
    List<Movie> wishList;

    public DVDCollection(double value, String owner) {
        this.value = value;
        this.owner = owner;
        movies = new ArrayList<>(12);
        wishList = new LinkedList<>();
    }

    public void addMovie(Movie movie) {
        if(!movies.contains(movie)) movies.add(movie);
    }

    public void addMovieToWishList(Movie movie) {
        if(!wishList.contains(movie)) wishList.add(movie);
    }



    @Override
    public Iterator<Movie> iterator() {    //will generate the custom iterator to run against the collection
        return new DVDCollectionIterator();
    }

    //as an inner class, this custom iterator has access to all the custom collections private fields
    class DVDCollectionIterator implements Iterator<Movie>{   //iterator needs to be able to bounce between the 2 different collections in the custom collection

        //using inbuilt iterators of the inbuilt collections for the pointers for the custom iterators hasNext() and next()
        Iterator movieIterator = movies.iterator();
        Iterator wishListIterator = wishList.iterator();

        @Override
        public boolean hasNext() {
            return movieIterator.hasNext() || wishListIterator.hasNext();  //returns true if either of the inbuilt collections composing the custom collection have a next
        }

        @Override
        public Movie next() {    //first iterate through main movies list, then when its empty, move on to wishlist list
            Movie movie;
            if (movieIterator.hasNext()) movie = (Movie) movieIterator.next();
            else movie = (Movie) wishListIterator.next();
            return movie;
        }
    }
}

class HomeTheatre{
    static DVDCollection dvdCollection = new DVDCollection(1000, "baggies");

    public static void main(String[] args) {
        Movie womble = new Movie("womble", 1.5, 180);
        Movie mungo = new Movie("mungo", 2.3, 60);
        Movie sita = new Movie("sita", 2.0, 300);
        Movie kato = new Movie("kato", 1.2, 15);
        Movie kosie = new Movie("kosie", 1.3, 2);

        dvdCollection.addMovie(womble); dvdCollection.addMovie(mungo); dvdCollection.addMovie(sita);
        dvdCollection.addMovieToWishList(kato); dvdCollection.addMovieToWishList(kosie);
        showMovies();

    }

    public static void showMovies() {
        for (Movie movie : dvdCollection) {  //to make foreach applicable to custom collection, must implement Iterator interface
            System.out.printf("\nFOREACH: Movie name = %s, Movie runtime = %s, Movie sales = $%sm", movie.name, movie.runtime, movie.boxOffice);
        }

        //above foreach is equivalent to:
        Iterator<Movie> dvdCollectionIterator = dvdCollection.iterator();    //calls iterator() on the collection of the Iterator object, Album must implement iterable()
        while (dvdCollectionIterator.hasNext()) {
            Movie currentMovie = dvdCollectionIterator.next();
            System.out.printf("\nITERATOR: Movie name = %s, Movie runtime = %s, Movie sales = $%sm", currentMovie.name, currentMovie.runtime, currentMovie.boxOffice);

        }
    }
}


class PrimeIteration implements Iterable<Integer>{
    private static final List<Integer> NUMBERS = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25);

    @Override
    public Iterator<Integer> iterator() {
        return new PrimeIterator();
    }

    private static class PrimeIterator implements Iterator<Integer> {

        private int cursor;

        @Override
        public boolean hasNext() {
            if (cursor > NUMBERS.size()) return false;
            for (int i = cursor; i < NUMBERS.size(); i++) {
                if (isPrime(NUMBERS.get(i))) {
                    cursor = i;
                    return true;
                }
            }
            return false;
        }

        @Override
        public Integer next() throws NoSuchElementException {
            return NUMBERS.get(cursor++);

        }

        private boolean isPrime(int i){
            BigInteger bigInteger = new BigInteger(Integer.toString(i));
            return bigInteger.isProbablePrime(1);
        }

    }

    public static void main(String[] args) {
        PrimeIteration primeIteration = new PrimeIteration();
        Iterator<Integer> iterator = primeIteration.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }


    }
}



















