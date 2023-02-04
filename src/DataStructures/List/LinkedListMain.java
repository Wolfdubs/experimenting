package DataStructures.List;

public class LinkedListMain {

    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        myList.insert(4);
        myList.insert(9);
        myList.insert(32);
        myList.printList();
        myList.insertAtStart(12);
        myList.printList();
        myList.insertAt(2,99);
        myList.printList();
        myList.deleteAt(2);
        myList.printList();
        myList.deleteAt(3);
        myList.printList();
    }
}
