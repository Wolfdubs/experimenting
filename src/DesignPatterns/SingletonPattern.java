package DesignPatterns;

public class SingletonPattern {
/*
simplest design pattern
have an object to be used widely throughout the system, but don't want to have to pass an instance to each place to use
also often only want a single instance of the object, so is really just a global variable
So want to ensure there is only 1 instance of the object throughout the system
    to control total number of instances of a class
Client wants to call a Singleton object, without callings its constructor, because the singleton might already exist
e.g. for a game, only want 1 instance of SoundManager class
Also used by Builder and AbstractFactory Patterns
Disadvantage:
    as a global variable, it is dangerous for evolution, understanding, tracking system state
    static getInstance; static methods are harder to evolve
        as it has a limited role, it is ok
    spreads dependencies on the Singleton class throughout the system, so causes coupling, and are hidden in the code as never passed as parameters or set as fields
How
    constructor is private
    create objects via public static getInstance()
 */

    private static SingletonPattern singletonPatternInstance;
    private SingletonPattern(){   //constructor is private so other classes cannot call it

    }

    //some complications arise if multithreading, so must avoid ever not having just 1 object
    public static SingletonPattern getInstance() {   //consumers call this to retrieve the instance of the Singleton object
        if (singletonPatternInstance == null) {
            singletonPatternInstance = new SingletonPattern();
        }
        return singletonPatternInstance;
    }

}


//e.g. a Game only wants 1 SoundManager object
class SoundManager {
    private static SoundManager soundManager;
    private SoundManager(){}
    public static SoundManager getInstance(){
        if (soundManager == null) {
            soundManager = new SoundManager();
        }
        return soundManager;
    }
}

class Game {
    public static void main(String[] args) {
        SoundManager soundManager = SoundManager.getInstance();
    }
}