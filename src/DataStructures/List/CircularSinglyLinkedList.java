package DataStructures.List;

public class CircularSinglyLinkedList<T> {
    /*
    last node's pointer points to the head node
    Advantage: can start at any node and still traverse entire list
            maintain 1 pointer 'last' at the last node, and use last.next to get the head
            don't need any null assignment, unless entire list is empty
            useful for round robining tasks
    Disadvantage: more complex, risk of infinite loop if mistaken
    Search = O(n)
    Display = O(n)
    Insertion = O(1)
    Deletion = O(1)
     */

    private Node<T> head;  //head node is optional
    private Node<T> tail;

    public CircularSinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public static void main(String[] args) {
        CircularSinglyLinkedList<Integer> circularSinglyLinkedList = new CircularSinglyLinkedList<>();
        circularSinglyLinkedList.insertAtStart(4); circularSinglyLinkedList.insertAtStart(9); circularSinglyLinkedList.insertAtStart(2);
        circularSinglyLinkedList.insertAtStart(7); circularSinglyLinkedList.insertAtStart(0); circularSinglyLinkedList.insertAtStart(5);
        circularSinglyLinkedList.display();
        circularSinglyLinkedList.insertAtIndex(10,4);
        circularSinglyLinkedList.display();
        circularSinglyLinkedList.deleteAtEnd();
        circularSinglyLinkedList.display();
        circularSinglyLinkedList.deleteAtStart();
        circularSinglyLinkedList.display();
        circularSinglyLinkedList.deleteEntry(7);
        circularSinglyLinkedList.display();
        circularSinglyLinkedList.deleteEntry(81);
        circularSinglyLinkedList.display();
        circularSinglyLinkedList.insertAtEnd(15);
        circularSinglyLinkedList.display();
        circularSinglyLinkedList.search(10);
        circularSinglyLinkedList.search(32);
        circularSinglyLinkedList.reverse();
        circularSinglyLinkedList.display();

    }

    private void insertAtStart(T data){
        Node<T> newNode = new Node<>(data);
        if (isEmpty()){
            head = tail = newNode;
        } else {
            Node<T> temp = head;
            head = newNode;
            newNode.next = temp;
            tail.next = head;
        }
    }

    private void insertAtIndex(T data, int index){
        Node<T> newNode = new Node<>(data);
        Node<T> temp = head;
        if (head==null || size()<=index){
            System.out.println("Index out of bounds");
        } else{
            for (int i = 0; i < index-1; i++){
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }

    private void insertAtEnd(T data){
        Node<T> newNode = new Node<>(data);
        if (head==null){
            head = tail = newNode;
            newNode.next = tail;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    private void deleteAtStart(){
        if (head==null) return;
        if (head==tail) head = tail = null;   //if only 1 element, which will be both head and tail
        else {
            head = head.next;
            tail.next = head;
        }
    }

    private void deleteEntry(T data){
        if (head==null) System.out.println("out of bounds error");
        else {
            Node<T> temp = head;
            Node<T> previous = new Node<T>();
            while(temp.data!=data){
                if (temp.next==head) {
                    System.out.println("data not found in the list");
                    break;
                }
                previous = temp;
                temp = temp.next;

            }
            if (temp==head){   //if data to delete is at head
                previous=head;
                while(previous!=head){
                    previous=previous.next;
                }
                head=temp.next;
                previous.next=head;
            }
            else if (temp.next==head) {  //if data to delete is at end
                previous.next = head;
            } else{
                previous.next = temp.next;
            }
        }

    }


    private void deleteAtEnd() {
        if (head==null) return;
        if (head==tail) head = tail= null;
        else {
            Node<T> temp = head;
            while (temp.next != tail) {    //iterates until 2nd last element
                temp = temp.next;
            }
            tail = temp;   //set 2nd last element to tail
            tail.next = head;
        }
    }

    private void display(){
        Node<T> temp = head;
        if (head!=null){
            do {
                System.out.print(temp.data + " ");
                temp=temp.next;
            } while(temp!=head);
        }
        System.out.println();
    }

    private void search(T data){
        if (head==null) {
            System.out.println("list is empty");
            return;
        }
        Node<T> temp = head;
        boolean found = false;
        int counter = 0;
        do {
            if (temp.data == data) {
                found = true;
                break;
            }
            temp = temp.next;
            counter++;
        } while (temp != head);
        if (found) System.out.println("Data found at index " + counter);
        else System.out.println("element not in list");

    }

    private int size(){
        int size = 0;
        if (head!=null){
            size = 1;
            Node<T> temp = head;
            while(temp.next!=head){
                temp=temp.next;
                size++;
            }
        }
        return size;
    }


    private boolean isEmpty(){
        return head==null;
    }

    private Node<T> reverse(){   //O(n)
        if (head==null) return null;
        Node<T> current = head;
        Node<T> previous = null;
        Node<T> next;
        do {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }while(current!=head);
        head.next = previous;  //relinking the tail of the list to head
        head = previous;
        return head;
    }

    private static class Node<T>{
        T data;
        Node<T> next;
        public Node(){}
        public Node(T data) {
            this.data = data;
        }
    }
}
