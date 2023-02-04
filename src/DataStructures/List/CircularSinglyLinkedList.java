package DataStructures.List;

public class CircularSinglyLinkedList<T> {
    /*
    last node's pointer points to the next node
    Advantage: can start at any node and still traverse entire list
            maintain 1 pointer 'last' at the last node, and use last.next to get the head
            don't need any null assignment, unless entire list is empty
            useful for round robining tasks
    Disadvantage: more complex, risk of infinite loop if mistaken
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
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            newNode.next = head;
        }
    }

    private void display(){
        Node<T> temp = head;
        if (head!=null){
            do {
                System.out.println(temp.data);
                temp=temp.next;
            } while(temp!=head);
        }
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

    private static class Node<T>{
        T data;
        Node<T> next;
        public Node(){}
        public Node(T data) {
            this.data = data;
        }
    }
}
