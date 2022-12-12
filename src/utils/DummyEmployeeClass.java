package utils;

public class DummyEmployeeClass {
    private final String name;   //private and final fields still mutatable via reflection
    private int salary;
    private String jobTitle;

    public DummyEmployeeClass(String name, int salary) {
        this.name = name;
        this.salary = salary;}

    public String getName() {        return name;    }
    public int getSalary() {        return salary;    }
    public void setSalary(int salary) {        this.salary = salary;    }
    public String getJobTitle() {        return jobTitle;    }
    public void setJobTitle(String jobTitle) {        this.jobTitle = jobTitle;    }

    public void work(){        System.out.println("I'm working");    }
    private void bathroomBreak(int time, String message, boolean washHands) {
        System.out.println("Bathroom breaks are private, I need " + time + " minutes");
        System.out.println("I also wanna say: " + message);
        if (washHands) {System.out.println("I'm washing my hands");  }
    }

    public static void changeJobs() {        System.out.println("I gotta new job");    }
    private static void cashPaycheck() {        System.out.println("I love to cash my paycheck");    }
}
