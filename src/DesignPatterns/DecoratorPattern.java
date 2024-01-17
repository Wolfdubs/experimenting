package DesignPatterns;

public class DecoratorPattern {
    /*
    helps allow extension of a system in the adding of varying kinds of behavior
    can add arbitrary combinations of behaviors to instances, rather than to the class (aka all its objects)
    allows instances to be wrapped with new responsibilities -> so don't need to compile all possible combinations of behaviors in advance
    Example of Composition over Inheritance
    Similar to Composite Pattern, but varies by intent
        Composite Pattern is to aggregate functions
        Decorator is to be able to wrap new behaviors on an instance
    Component interface declares the high level actions to perform on all class instances within the Decorator pattern
    e.g. to layer behaviors to all occur at the same time e.g. on a game screen to call a type that shows lightening, another to shake controller, another to make the screen black
        rather than needing a new type for each possible type combination of these, just layer them independently
    Supports single responsibility principle -> the component can rely on its 1 task without worrying about which subtypes methods to invoke
    Supports Open/Closed -> extend system with new behavior by just adding a new Decorator subtype
    Uses composition to extend systems
    Disadvantages
        Use strategy pattern if decorated behaviors are heavyweight
        debugging can be tricky as an object contains many layered objects inside
    Works dynamically at runtime
    Solves problem of combinatorial explosion of possible subtypes in a system
    Decorated objects are unaware of the other types of decorators it is wrapped in
     */
}

interface Component{  //as the ConcreteComponent and Decorator both implement Component, client needn't concern with what wrapping has occurred
    void action();
}

class ConcreteComponent implements Component{  //the objects in the class needing to perform the relevant action
    public void action(){}
}

class Decorator implements Component{
    Component wrap;
    public void action(){}
}

class Decorator1 extends Decorator{
    public Decorator1(Component component){wrap=component;}
    public void action(){
        //does stuff
        super.wrap.action();  //then calls the action() of the wrapped Component
    }
}

class Decorator2 extends Decorator{
    public Decorator2(Component component){wrap=component;}
    public void action(){}
}

class DecoratorRunner{
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component.action();  //just calls the action method implemented in the concrete class
        component = new Decorator1(component);  //enclosed the existing instance of the component within the specific decorator
        component.action();  //now calls action() from Decorator1, which does stuff, and then itself calls the ConcreteComponent.action() via the wrapped Component of Decorator
        component = new Decorator2(component); //can wrap multi-layers
        component.action();  //would call Decorator2.action() -> Decorator1.action() -> ConcreteComponent.action()
    }
}

////////////////
interface GameFrame {
    void render();
}

class NormalLevel implements GameFrame{
    public void render() {}
}

class UnderwaterLevel implements GameFrame{
    public void render() {}
}

class LavaLevel implements GameFrame{
    public void render() {}
}

abstract class FrameDecorator implements GameFrame{
    GameFrame wrap = null;    //this is the core field that allows behaviors to be forward on the encapsulated objects
    public abstract void render();
}

class Lightening extends FrameDecorator {   //can layer these arbitrarily, can have a Lightening inside Blackout,
    public Lightening(GameFrame gameFrame) {wrap = gameFrame;}  //inside Squid while in a LavaLevel, or vice versa.
    public void render(){}                    //Layering can change at runtime by the Client with reference to GameFrame
}

class Blackout extends FrameDecorator {
    public Blackout(GameFrame gameFrame) {wrap = gameFrame;}
    public void render(){}
}

class SquidInk extends FrameDecorator {
    public SquidInk(GameFrame gameFrame) {wrap = gameFrame;}
    public void render(){}
}

class GameDecoratorRunner {
    public static void main(String[] args) {
        GameFrame gameFrame = new LavaLevel();
        gameFrame = new Blackout(new SquidInk(new Lightening(gameFrame)));
        gameFrame.render();  //runs Blackouts render(), then Squids, then Lightening, then LavaLevel
            //each decorated object should be isolated - not aware of other decorated objects alongside it, no implied ordering constraints

    }
}

















