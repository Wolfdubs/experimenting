package DesignPatterns;

import java.util.ArrayList;
import java.util.List;

/*
e.g. for a file system directory of Folders and Files, where a folder can contain either another folder or a file
        by having folders and files extend abstract/interface Component, dont need to duplicate code inside Folder to add() or iterate() both folders and files
                just do it once for Component

The Composite Pattern provides a design for a hierarchy, in which nodes with children differ in their behaviour from nodes with no children.
    An example might be the display of a hierarchy, like a file system, where folders contain files.  Folders would be displayed differently from files,
    and would have contents (the files).
The pattern consists of three classes: the Composite (the node that can have children), the Leaf (no children), and the Component,
    which is a superclass extended by both the Composite and Leaf.
The Composite has a collection of Components, so that the Composite class can loop through those Components without keeping track of whether
    the Component is actually a Composite or a Leaf.
The Composite also has an addComponent method so that Components can be added to its contents.
Without the Component super class abstraction, the Composite would have to maintain different lists for each kind of element in its contents,
    and would need to provide individual methods for adding contents, and displaying contents for each kind of content.
3 elements:
    1. Component class; abstract class or interface (if dont want any default implementation) with abstract operations e.g. abstract 'Component'
    2. Composite class; extends/implements the Component,  contains list of the Components
        has implementation of the operation to perform when visiting each component, and has addComponent() to maintain component list. e.g. Folder
    3. Leaf class; extends/implements the Component,just has the operation implementation. e.g. File
 */
public class CompositePattern {
    public static void main(String[] args) {
        Topic programming = new Topic("Programming Topic");
        Topic java = new Topic("Java Topic");
        Topic python = new Topic("Python Topic");
        Topic sql = new Topic("SQL Topic");
        Lecture javaLecture = new Lecture("Java Lecture");
        Lecture javaLecture2 = new Lecture("Object Oriented Programming");
        Lecture pythonLecture = new Lecture("Python Lecture");
        Video pythonVideo = new Video("Python Video");
        Video sqlVideo = new Video("SQL Video");
        programming.addModule(java); programming.addModule(python); programming.addModule(sql);
        java.addModule(javaLecture); java.addModule(javaLecture2);
        python.addModule(pythonLecture); python.addModule(pythonVideo);
        sql.addModule(sqlVideo);
        programming.display("   ");

    }

    static class Lecture extends Module{    //is a Leaf

        private String name;

        public Lecture(String name) {
            this.name = name;
        }

        @Override
        public void display(String indentLevel) {
            System.out.println(indentLevel + name);
        }
    }


    static class Video extends Module{   //is a leaf

        private String name;

        public Video(String name) {
            this.name = name;
        }

        @Override
        public void display(String indentLevel) {
            System.out.println(indentLevel + name);
        }
    }

    static class Topic extends Module{   //is a composite; consists of topics & leafs
        private String name;
        private List<Module> modules = new ArrayList<>();

        public Topic(String name) {
            this.name = name;
        }

        public void addModule(Module m) {modules.add(m);}

        @Override
        public void display(String indentLevel) {
            System.out.println(indentLevel + name);
            for (Module module : modules) {
                module.display(indentLevel + indentLevel);
            }
        }
    }

    static abstract class Module {     //is a component extended by all composites and leaves
        public abstract void display(String indentLevel);
    }
}




















