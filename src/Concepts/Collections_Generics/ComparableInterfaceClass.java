package Concepts.Collections_Generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Student implements Comparable<Student>{   //to be able to compare any custom objects, the class must implement Comparable interface
    int rollNo, marks;
    String name;

    public Student(int rollNo, int marks, String name) {
        this.rollNo = rollNo;
        this.marks = marks;
        this.name = name;
    }

    @Override
    public int compareTo(Student s){
        return marks>s.marks ? 1 : -1;     //sort()'s own logic will swap if return is 1
    }

    @Override  //not due to Comparable, is overriding built in toString for Object class
    public String toString() {   //useful because when you just print the object, it will auto call the toString()
        return "Student{" +
                "rollNo=" + rollNo +
                ", marks=" + marks +
                ", name='" + name + '\'' +
                '}';
    }
}

public class ComparableInterfaceClass {
    public static void main(String[] args) {
        List<Student> studentsList = new ArrayList<>();
        studentsList.add(new Student(340, 81, "Navin"));
        studentsList.add(new Student(193, 40, "Mayu"));
        studentsList.add(new Student(948, 62, "Prashant"));
        studentsList.add(new Student(454, 59, "Megha"));
        Collections.sort(studentsList);   //pass Comparable object into sort()
        for (Student s : studentsList) {
            System.out.println(s);}  //calls the overridden toString

        //can pass comparable implementation directly into sort()
        Collections.sort(studentsList, (i,j) -> i.name.length() > j.name.length() ? 1 : -1 );
        for (Student s : studentsList) {
            System.out.println(s);}




    }
}

