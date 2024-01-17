package Concepts;

import utils.Weapon;
import utils.Gun;
import utils.WeaponInterface;

public class IoCAndDI {
/*
Spring uses IoC and is open-source
Benefits:
	world most popular java framework
	IoC and DI
AOP = aspect oriented programming
DAO = data access object
Hibernate = object-relational mapper implementing JPA
JPA = java persistence API
Microservices = application designed as a collection of loosely-coupled, maintainable, testable, independently deployable services organized around functionality
*/
/* INVERSION OF CONTROL
design principle of inverting the control flow of a program. almost all frameworks use IoC as it is a defining characteristic for them
increases modularity of a program, more extensible
Problem solved = complexity of binding together many components from different layers -> frameworks support this wiring
Providing a callback that implements and/or controls a reaction, instead of acting ourselves directly it hands control to the external handler/controller
transfers control of objects / portions of a program to a container or framework
instead of custom code calling a library, IoC enables the framework to take control and make calls to our custom code
    frameworks use abstractions with extra behavior to do this. To add custom behavior, must extend the framework's classes or plugin custom classes
Advantages:
    decouples task execution from its implementation
    easier to switch between implementations
    increases modularity
    easier to test by isolating components / mocking its dependencies, letting components communicate via contracts
Achieved via Strategy design pattern, service locator pattern, factory pattern, dependency injection
Frameworks implementing IoC often use IoC containers
    Spring has ApplicationContext interface to instantiate, configure, assemble and manage beans
        Implementations:
            ClassPathXmlApplicationContext = for standalone apps
            FileSystemXmlApplicationContext = also for standalone apps
            WebApplicationContext = for web apps
        Assembles beans via configuration metadata -> XML or annotations
            manually instantiate container:
                AnnotationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    framework to manage automation dependency injection throughout the application
Object coupling is bound at runtime by an assembler object, typically not known at compile time
helps wire lightweight containers or assemble components from different projects into a cohesive application

If you have a 'repository' class responsible for handing over data from a daa source
It could establish a connection to the data source by itself,
but better if the repository class let you pass a connection to the data source into its constructor
    so the caller provides the connection, so you decouple the data source connection dependency from the repository class
        so the repository can work with whatever data source the caller passes, not just the 1 specified in the repository class
        This has inverted control, as the responsibility to create the connection now sits with the caller, not the repository
Anything that inverts the control structure of a program from the classic procedural design
Let frameworks handle communication between UI and your code rather than your code generating UI directly

Traditionally: when a class uses another class, it instantiates it directly, so controls its dependencies
IoC: the caller passes in the dependency to the class that will then use it, so controls the dependencies

custom code receives flow of control from a generic framework, opposite to procedural programming
    instead of custom code calling libraries to manage generic tasks, the framework calls the custom task-specific code
    increases modularity & extensibility
    related to dependency inversion -> which decouples dependencies between high & low levels through shared abstractions
    related to event driven programming, as this is often implemented via IoC as custom code just handles the event, while framework/runtime env handles the event loop & dispatch of events/messages

Program is founded on a framework that manages common behaviors & operations e.g. controlling mouse, network connection
    this framework manages the application, and calls the custom code when needed and monitors users actions

Benefits
    decouples task execution from implementation
    focuses a module on the task it is designed for
    frees modules from assumptions about how other systems work, instead rely on contracts
    prevent side effects from replacing modules
Implementations
    Design patterns:
        callbacks, schedulers, event loops, dependency injection, template method
    OOP implementations:
        service locator pattern strategy design pattern, dependency injection, contextualized lookup, template method pattern

For loose coupling of application components
The control refers to any extra responsibilities the class has beyond its main one e.g. control of program flow, or over dependent object creation and binding
TTD not possible without IoC

IoC is a principle, DI is a pattern, IoC container is a framework


 */
    /*
    class ServerFacade {   //has many assumptions about the data returned by the DAO, so couples ServerFacade implementation to DAO implementation
        public <K, V> V respondToRequest(K request) {
            if (businessLayer.validateRequest(request)) {
                Data data = DAO.getData(request);
                return Aspect.convertData(Data);
            }
            return null;
        }
    }

    class ServerFacadeIoC {  //hands control totally over to the DAO object
        public <K, V> V respondToRequest(K request, DAO dao) {
            return dao.getData(request);   //IoC being used as the method depends on the dependency object beign passed in
        }
    }




/* DEPENDENCY INJECTION
pattern to hand a component's dependency to the component from outside, freeing the component to lookup the dependent itself
is a specific type of IoC, but is harder to implement than standard IoC
A design pattern to implement IoC where the control being inverted is setting the objects dependencies
    inserting objects into other objects is done by an assembler, not the objects themselves
Dependent object/module is coupled to the object it needs at runtime -> the specific object that will satisfy the dependency during execution is
    not known at compile time with static analysis (analysis of program without execution)
Subtypes
    constructor injection, parameter injection, setter injection, interface injection, method injection

Helps achieve loose coupling between classes, where high-level modules don't depend on low-level modules, but both only depend on abstraction
 */


    class Store {//Traditional way of creating object dependency
        private WeaponInterface weapon;
        public Store() {
            weapon = new Gun( "gun1");  //must instantiate an implementation of the WeaponInterface within the Store class itself
        }
    }

    class StoreDI{
        private WeaponInterface weapon;
        public StoreDI(WeaponInterface weapon) {
            this.weapon = weapon;  //don't have to specify the implementation of the WeaponInterface to use
        }
    }
    //in Spring, would use a metadata annotation, that the Spring IoC container will read and use to assemble beans at runtime
/*
TODO:
    https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring
    https://www.geeksforgeeks.org/spring-understanding-inversion-of-control-with-example/
    https://www.tutorialsteacher.com/ioc/inversion-of-control
    https://stackoverflow.com/questions/3058/what-is-inversion-of-control?page=1&tab=scoredesc#tab-top
    https://medium.com/@amitkma/understanding-inversion-of-control-ioc-principle-163b1dc97454
    https://en.wikipedia.org/wiki/Dependency_injection

  */
}
