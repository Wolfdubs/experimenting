package DesignPatterns;

public class StatePattern {
    /*
    Strategy pattern is a static view of the program
    State pattern allows dynamic variation of program behavior based on internal state
    Encapsulate states explicitly, within state objects, and let them manage transitions between states, removing this responsibility from client objects
        so is easier to add new states to the system, without modifying clients
    State Interface has concrete implementations, and also a Context field
    Context class also has a State field, with setState()
    Client is linked to the Context
    So same design as Strategy, except the State interface also has a Context field
        this means at runtime can change the State object inside the Context via the Context object inside State interface
            as the concrete state can do this.context.setState() to modify the state to be a new object
    Rather than having a series of if-else's to manage state e.g. of Mario being big/small/dead/invincible, especially if code is duplicated (wich can cause inconsistent behaviors)
    Makes state more independent
    Flexible way to manage dynamic state transitions during runtime,by localizing state decisions within state classes that manage themselves independently
        so supports Open-Closed principle as easy to add new states without changing the Context

    Both Strategy & State isolate Client from future changes to concrete classes as Context class doesn't change
        both have very similar structure
            both use inheritance to enable extension
            both support Open/Closed principle
        but State is enabling dynamic changing of state at runtime via Context field's setState()
            field to track current Context
            method to transition between concrete subclasses
        while Strategy has a set field that is used throughout execution of program

    At runtime, a concrete state invokes setState() on a context object
     */
}

class Client{  //doesn't have to do anything to manage current state
    
}

class Context{
    State state;   //this dynamic field can be changed as the program runs, as e.g. StateOne can do this.context.setState()
    void setState(State s){this.state=s;}
    void action(){}
}

interface State{
    Context context = null;
    void action();
}

class StateOne{
    void action(){        System.out.println("in state 1");    }
}
class StateTwo{
    void action(){        System.out.println("in state 2");    }
}
class StateThree{
    void action(){        System.out.println("in state 3");    }
}



////



class MarioContext{  //methods contained are called by the Game/Level while playing - then uses Composition via State field to forward implementation to current state object
    MarioState marioState;  //tracks marios current state. using delegation, Mario forwards request to the concrete implementation state object
    void setMarioState(MarioState marioState) {this.marioState=marioState;}
    void hit(){this.marioState.hit();}   //forwarding the hit to the current state object
    void trigger(){this.marioState.trigger();}
}


interface MarioState{
    MarioContext marioContext = new MarioContext();  //the state tracks what context it is associated with. exists only so States can call the setState() inside Context
    public void hit();
    public void trigger();  //encountering either mushroom or star
}

class Small implements MarioState{
    public Small(MarioContext mc){}
    public void hit(){marioContext.setMarioState(new Dead(marioContext));}  //so changes related to a single state are isolated within the state itself
    public void trigger(){marioContext.setMarioState(new Invincible(marioContext, this));}  //any transition outside of the Small state is handled within Small
}                                                                               //passing the mario State so Invincible transitions back to this when timeout expires

class Big implements MarioState{
    public Big(MarioContext mc){marioContext.setMarioState(this);}
    public void hit(){marioContext.setMarioState(new Small(marioContext));}  //when Big mario is bit, it sets the state to Small
    public void trigger(){}
}

class Invincible implements MarioState{
    MarioState lastState;   //reverts to this state when timeout is reached
    public Invincible(MarioContext marioContext, MarioState marioState){lastState=marioState;}  //must also track the prior state for after timeout
    public void hit(/*do nothiing as invincible*/){}
    public void trigger(){}
    private void timeout(){marioContext.setMarioState(lastState);}
}                       //upon timeout, it sets the state to tbe the last state that was passed in by other state in cnstructor call

class Dead implements MarioState{
    public Dead(MarioContext marioContext){marioContext.setMarioState(this);}
    public void hit(/*do nothiing as dead*/){}
    public void trigger(){}
}

