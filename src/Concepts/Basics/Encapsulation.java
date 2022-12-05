package Concepts.Basics;

class Encapsulation {

    private int myInt;
    private String myString;

    //Encapsulation -> binding data with methods; means object values should only be accessible via getters & setters
    // keeps variable safe, because methods can include logic for what is permitted, or e.g. add to a logfile to track changes every time the method is called

    public int getMyInt() {
        System.out.println("checking user is logged in first");
        System.out.println("User has accessed the myInt value of " + this);
        return myInt;
    }

    public void setMyInt(int myInt) {
        this.myInt = myInt;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

}


