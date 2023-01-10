package DesignPatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/*
Lets objects watch the state of other objects
    Observer watches Subject
    Subject has list of observers, added to via addObserver(), and sends notifications to them via notify(), which calls each Observers update()

    2 phases:
        1. Registration = Observer registers the subject by calling a registration method on it e.g. addObservers, so the subject adds the Observer to its
                          list of observers
                          addObservers() could come from within Subject, or a Manager class
        2. Notification = When Subject's state changes it calls its own notify(), which iterates through the observers list, calling update() on each
                          Push model = Subject can send argument with the method to indicate the change
                          Pull model = Subject sends reference to itself so Observer can call back to detail changes with its own method e.g. getState()
Have Observer and Observable classes, extended/implemented by concrete classes
    abstract Observable implements notifyObservers()
    concrete subject calls notifyObservers()
    concrete observer implements update()
    abstract observable calls update()
    abstract observable contains list of Observers


 */
public class ObserverPattern {

    abstract class Subject{
        public List<Observer> observers = new ArrayList<>();
        public void addObserver(Observer observer) {
            if (!observers.contains(observer))
            observers.add(observer);
        }
        public void notifyObservers(Course course){
            Observer currentObserver = observers.iterator().next();
            while (observers.iterator().hasNext()){
                currentObserver.update(course);
                currentObserver = observers.iterator().next();
            }
        }
    }

    interface Observer {
        public void update(Course course);
    }

    class Course extends Subject {

        public Course(){
            addObserver(new Student("navin", 101));   //this design of adding concrete observers in the constructor of the concrete subject is situation dependent, not a rule
            addObserver(new Student("akshay", 59));
        }
        public void publishLecture(){
            notifyObservers(this);
        }
    }

    class Student implements Observer {

        private String name;
        private int id;
        private List<Course> courses = new ArrayList<>();

        public Student(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public void addCourse(Course c) {
            if (!courses.contains(c)) {
                courses.add(c);
                c.addObserver(this);

            }
        }

        @Override
        public void update(Course course){
            System.out.println("Reading the new lecture notes for " + course.toString());
        }
    }
}

/*
Java has inbuilt support for Observer pattern
    Observer interface; specifies update(), so any class implementing Observer will have update()
        implementing class must implement with required behavior
    Observable abstract class; functionality for subject -> maintains list of Observers, and notifyObservers() loops through list, calling update() on each
        to make notifyObservers() work, must call setChanged() beforehand, as udpate() is only called if something in the Observable has changed
     But both are deprecated so instead use
        For a richer event model, consider using the java.desktop/java.beans package.
        For reliable and ordered messaging among threads, consider using one of the concurrent data structures in the java.util.concurrent package
        For reactive streams style programming, see the java.util.concurrent.Flow API
 */
class JavaInbuiltObserverAndObservable {
    class ObserverDemo implements Observer {

        @Override
        public void update(Observable o, Object arg) {

        }
    }

    class ObservableDemo extends Observable {

    }
}





















