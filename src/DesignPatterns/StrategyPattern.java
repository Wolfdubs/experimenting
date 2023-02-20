package DesignPatterns;

public class StrategyPattern {
    /*
    helps change the behavior of a program
    we don't know every possible variation in advance, so need mechanism to encapsulate algorithms allowing future extension
    example of being open for extension but closed for modification
        extension via Strategy interface that new concrete classes can implement
        closed via consumer's code doesn't have to change, since it depends only on the Strategy interface -> doesn't interact with concrete instances
    example of polymorphism hiding concrete types behind abstract interfaces
    Strategy interface defined the structure of all the concrete classes that we want to define it with, which hold the actual implementation
    Context class uses composition, as it has a Strategy field with 1 of the concrete implementations
        it forwards consumer requests to the correct implementation
        Client then just works with declarations of the Strategy interface from Context

    Concrete1, Concrete2 --> Strategy Interface -->  Context (contains Strategy object) --> Client
        Consumer is insulated from any changes in the concrete classes

    e.g. if different Enemy types in a game, and different logic depending on which is touched, instead of if-else's, which are hard to
        evolve, and scattered everywhere Enemies are used, so it is hard to add new Enemies as must update all those checks
        Instead, have a TouchStrategy Interface, that all the Enemy subclasses implement
        Context object contains a private field of TouchStrategy Interface type. this field won't change at runtime
        When the Client calls handleTouched(), the Context forwards that request to the correct concrete class
            this 1 agent is fixed as will be used for the execution lifetime of the program; won't change while program is running
                Strategy Pattern is about easy ability to add new types to the program, not about having them change during execution
            the Client just specifies the concrete type when it calls the Constructor for TouchStrategy and specifies the
                concrete class to instantiate
                So it has a slight dependency in this, as Client must specify explicitly which implementation to use
                but this link breaks the encapsulation
                    resolve via a Factory,so the Client does not know about the concrete types anymore
                        have the Client use a TouchFactory, which only implements getStrategy() to get the correct concrete Strategy class
                            Clients then passes this returned type into the constructor in the Context when creating the Strategy interface object
                        or have the Context use the TouchFactory, which means don't need the parameter passed to the constructor
                            good as clears a dependency from the Client
                                don't have the Client instantiate the objects, else lose benefit of Strategy Pattern

Client --> (sends requests) --> Context --> (has a field of the Strategy type declaration) --> Strategy --> concrete1,2,3
                                        --> (calls from constructor for Strategy delcaration) --> StrategyFactory (call to instantiate specific object)--> concrete1,2,3

     Provides mechanism for encapsulating algorithms to support future extension (not modification)
     Provides mechanism to vary state of program in a static way (not dynamically)
     */

}


interface MovementStrategy{
    void handleMovement();
}

class NaiveEnemy implements MovementStrategy {
    public void handleMovement(){}
}

class AIEnemy implements MovementStrategy {
    public void handleMovement(){}
}

class DeepLearningEnemy implements MovementStrategy {
    public void handleMovement(){}
}

class AbstractNPCContext {
    public AbstractNPCContext(){
        MovementFactory movementFactory = new MovementFactory();
        movementFactory.getStrategy(agent);
    }
    MovementStrategy agent;
    void move(){agent.handleMovement();};
}

class MovementFactory {
    MovementStrategy getStrategy(MovementStrategy movementStrategy){
        return movementStrategy;
    }
}

class ClientRunner{
    public static void main(String[] args) {
        AbstractNPCContext abstractNPCContext = new AbstractNPCContext();
        abstractNPCContext.move();

    }

}














