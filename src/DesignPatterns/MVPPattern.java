package DesignPatterns;

public class MVPPattern {
    /*
    another compound pattern
    modernization of MVC to enhance testability -> only primitives sent/received by View, which are easier to test than complex Model objects
        decreases amount of code in Views; views in MVP are simpler
        forces the Controller (Presenter for MVP) to be feature-full
        like MVC, goal is to decouple View from Model
            but unlike MVC it enforces this decoupling by disallowing any Model objects being sent to the View
            Model's use of Event Bus can improve efficiency about how event notifications occur -> decreasing complexity
            Both MVC & MVP:
                encourage lightweight Views
                enhance system testability
                encourage designers to pull functionality out of Model
                but they do NOT always use Observer pattern
                MVP uses Presenter, not Controller
    Same overall architecture as MVC
        but instead of the Observer pattern between Model & View, instead the model interacts with the middle layer 'Presenter'
        using an event bus (a 3rd party component to moderate interactions between the 2 layers. not required but often seen)
    Presenter
        acts like to Controller, but
        must ensure it has no dependencies on View components -> so it is easy to test (core of the pattern)
    View
        where MVP is most different to MVC
        uncoupled from Model
        Presenter sits between View and Model as a mediator
            Presenter updates the Model and retrieves Model state in response to nofity() events or Event bus from Model
            Presenter never passes Model object to the View - only primitives (and data structures containing them)
            So interactions between Presenter & Model are only well-defined Model objects
            and between Presenter & View are only primitives -> easy to create assertions for in testing
        Enforced to be lightweight, unlike MVC, because Presenter never passes Model objects to them, so not much data there
            thus Presenter is forced to handle the logic of the app
                good as Views will be modified most, so need to be simplest
    Process
        1. User performs an action
        2. View passed this to Presenter. if action included data, only primitive versions are sent to Presenter
        3. Presenter updates its internal state, and the Model as needed
        4. Model updates and fires events, either notify() event or via Event Bus, back to the Presenter
            often passes an instance of itself along with notify()
            so like to Push version of the Observer Pattern, as Presenter doesn't need to then go and retrieve the model state
        5. Presenter updates View, only sending primitives

    Disadvantage
        Presenter can bloat - so try to make multiple Presenters to handle different cases

     */
}
