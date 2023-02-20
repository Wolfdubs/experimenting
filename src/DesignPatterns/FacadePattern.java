package DesignPatterns;

public class FacadePattern {
    /*
    Structural pattern that reduces subsystems apparent complexity so it is easier for clients to use
        simplifies access to subsystems
        uses composition to hide implementations of different types from clients unless they specifically need them
        encourages weak coupling between clients and subsystems
    identifies the high level tasks the subsystem is supporting, and adds them directly to the facade interface so client doesnt concern with different type declarations
        clients just interact with facade itself, which forwards requests on to the required subsystem
            so facade hides all the internal types from the client
        can do it ad hoc, where facade encompasses certain classes, but for others, the client interacts directly
    reduces dependency on the classes in the subsystem -> reusing a client no longer requiring copying all the subsystems too
        just reusing the interface declaration of Facade, and of any methods within that are called
            so ignore the concrete implementations, only work with the interfaces they implement
    This is Dependency Inversion
        the Facade's methods accept Interfaces as parameters, and always returns Interface types too
    Violates Single Responsibility principle, as provides access to many tasks in the subsystem -> is a tradeoff, as clients are easier to write & reuse
    Solves overly complex subsystems that are difficult for clients to use

     */
}
