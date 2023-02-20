package DesignPatterns;

public class MVCPattern {
    /*
    Is a meta-pattern that combines multiple patterns
    Separates the view of an application, from its implementation
        as views are updated the most, so MVC enables updating separate from rest of the system
        splitting UI from business logic is god for team segregation
        easier testing if segregated
        same backend logic will be rendered by multiple views e.g. different widget charts representing data, or 2D map vs first-person view in game
        increases re-usability, ease of fixing bugs
        decoupling components of an application
    Model
        can contain many types and many objects
        contains all data in the app, can validate its own integrity and persist itself (e.g. saving to disk, DB)
            does not contain the business logic though
        knows nothing about how to render itself
            means can reuse model between apps easily, as is domain independent
                e.g. mobile app vs web app
        is the subject/Observable within an observer design pattern
            whenever model updates, it fires notify() events to the View, so View knows a change has happened in Model
            View then calls the Model object to ask for its state
        must think about how to encapsulate data in domain independent model objects

    View
        logic to render model objects so user can see them
        is the observer in the observer design pattern
        contains many different views
            so when Model changes, use Observer pattern it can alert many Views concurrently as they will be in the Observer list
                these Views can then retrieve the state to render to latest
        Often nested, so would use a Composite Pattern
        Views are often Strategies in the Controller for ease of subbing out different views
    Controller
        Tightly bound to its associated view
        Contains the business logic for the app
        Responds to changes in View and updates Model accordingly
            so contains the business logic to respond to actions from users and update the Model as required
    Process
        1. User clicks something in a View
        2. Invokes a change in Controller
        3. Controller business logic reasons about the change, and updates Model accordingly
        4. Model updates and fires notify() to al the Views observing it
        5. Each observing View calls back to Model to retrieve state to update their rendering
    Disadvantage:
        Coupling between M & V & C
            but lose coupling along the notify() path between Model and View; Model doesn't need to know anything about the Views
        Easy to put far too much functionality in Views, which are also hardest part to test
            Try to make sure Controller holds more of this logic, to keep Views lightweight
        Performance is worse if Model has 1000s of objects all firing notify(), it can overwhelm Views
        Small apps don't need this overcomplicated deconstruction

     For a Game
        characters in Model are more lightweight, e.g. how many coins they have, state
        View will have design
            must have a handleInput() to pass along to Controller
        Controller then receives handleInput() from controller and move the Model objects as needed, e.g updating positions
            would also contain GameClock
            these changes are send to Model, which then sends to the View

     Advantages -> Decouples View from the rest fo the system
        1. Splitting View from Controller relieves the critical path for the app; so teams can split by specialism
        2. Easier to add collaborative views - views that work/rely on same data source
        3. Easy to add new views without changing the rest of the system
            as UIs change more often that backend implementation
        4. Much easier to test, especially with most functionality being in Controller as opposed to View
     */
}
