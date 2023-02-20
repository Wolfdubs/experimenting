package DataStructures.List;

import javax.swing.plaf.IconUIResource;

public class DoublyLinkedList<T> {
    /*
    Advantage = can traverse bidirectionally
                adding new nodes is just shifting pointers
                deleting/search nodes doesn't require traversing entire list
    Disadvantage = extra space requirements to store extra pointer
                    insertion/deletion require extra previous pointer to be maintained
    last node's next is null, head node's previous is null
    Search = O(n)
    Access = O(n)
    Insert = O(1)
    Delete = O(1)
     */

    private Node<T> head;
    private int size;
    public DoublyLinkedList(){
        head = null;
        size = 0;
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> doublyLinkedList = new DoublyLinkedList<>();
    }

    public int getSize() {        return size;    }
    public void setSize(int size) {        this.size = size;    }

    private void insertAtStart(T data){
        Node<T> newNode = new Node<>(data);
        head.previous = newNode;
        newNode.next = head;
        head = newNode;
        setSize(getSize()+1);
    }

    private void insertAtIndex(T data, int index){
        if (index > getSize() || index < 0 || head==null) {
            System.out.println("Index not valid for list");
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> current = head;
        if (index == 0) insertAtStart(data);
        else if (index==getSize()) insertAtEnd(data);
        else {
            for (int i = 0; i < index && current.next!=null; i++){
                current = current.next;
            }
            newNode.next = current;
            current.previous.next = newNode;
            newNode.previous = current.previous;
            current.previous = newNode;
            setSize(getSize()+1);
        }


    }

    private void insertAtEnd(T data){
        Node<T> newNode = new Node<>(data);
        Node<T> current = head;
        if(isEmpty()) head = newNode;
        while(current.next!=null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.previous = current;
        setSize(getSize()+1);
    }

    private void deleteAtStart(){
        if (isEmpty()) {
            System.out.println("List is empty");
            return;
        }
        Node<T> newHead = head.next;
        head = head.next;
        head.previous = null;
        setSize(getSize()-1);
    }

    private void deleteAtIndex(int index){
        if (index==0) deleteAtStart();
        if (index==getSize()) deleteAtEnd();
        else if (index>0 && index<getSize()){
            Node<T> current = head;
            for (int i = 0; i < index && current.next!=null; i++) {
                current=current.next;
            }
            current.previous.next = current.next;
            current.next.previous = current.previous;
            setSize(getSize()-1);
        } else {
            System.out.println("Index " + index + " is not a valid index for the list of size " + getSize());
        }
    }

    private void deleteAtEnd(){
        Node<T> current = head;
        if (isEmpty()) System.out.println("List is empty");
        if(head.next==null) head=null;
        while(current.next != null) {  //loop to 2nd last position
            current.next = null;
            setSize(getSize()-1);
        }
    }

    private void display(){
        if (isEmpty()) System.out.println("empty list");
        Node<T> current = head;
        while(current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    private void search(T data){
        if (isEmpty()) {
            System.out.println("empty list");
            return;
        }
        Node<T> current = head;
        int counter = 0;
        while(current != null){
            if (current.data == data) {
                System.out.println(data + " found at index " + counter);
                break;
            } else {
                current = current.next;
                counter++;
            }
        }
    }

    private boolean isEmpty(){
        return size==0;
    }

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        public Node(T data) {
            this.data = data;
            this.next = this.previous = null;
        }
    }
}

















