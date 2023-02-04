package DataStructures.List;

public class LinkedList {

    Node head;  //by default this will be null when the default no-args constructor is called

    public void insert(int num){
        Node node = new Node(num, null);

        if (head == null) {            //checks if there is any nodes inserted yet, if not, then head node becomes the new node
            head = node;
        }
        else {
            Node n = head;    //create iterating node that will traverse the list, which at the end you can set to the new 'node'
            while (n.next != null){
                n = n.next;
            }
            n.next = node;
        }
    }

    public void insertAtStart(int num) {
        Node node = new Node(num, null);
        node.next = head;                         //don't need to check if head is currently null, as new node will point to head whether it is null or not
        head = node;
    }

    public void insertAt(int index, int num) {
        Node node = new Node(num, null);
        Node n = head;
        if (index == 0){           //is same as inserting at the start
            insertAtStart(num);
            return;           // must return here so it doesn't add it again via the for loop. or could wrap rest of method in else block
        }
        //get to node before given index
        for (int i = 0; i < index-1; i++) {          //to traverse array until the node just before the index passed in. must do index-1 as end up with the .next anyway
            n = n.next;
        }
        node.next = n.next;             //make the new node point to the previous nodes old next
        n.next = node;                  //make the previous node point to new node

    }

    public void deleteAt(int index){
        Node n = head;

        if (index == 0){
            head = head.next;
            return;
        }

        for (int i = 0; i<index-1; i++){    // will get index out of bounds with index-1 if index is 0
            n = n.next;              //end up at the node just before the one to delete
        }
        n.next = n.next.next;
    }

    public Node deleteValue(int n){
        Node node = head;
        Node previousNode = head;

        if (head.data==n){
            head=head.next;
        } else {
            while (node!=null){
                if (node.data == n){
                   previousNode.next = node.next;
                } else {
                    previousNode = node;
                    node = node.next;
                }
            }
        }
        return node;
    }

    public Node deleteFirst(){
        Node reference = head;
        if (!isEmpty()){
            head = head.next;
        }
        return reference;

    }

    public void printList(){
        Node node = head;       // sets node iterator to start at head node
        System.out.println("\n");  // just for formatting to print blank line
        while (node != null) {           //runs until end of the list. cannot use node.next!=null as it will exit before last node is printed. must only not print when the node itself is null
            System.out.print(node.data + ", ");    // prints out nodes data
            node = node.next;                 // jumps to next node
        }
    }

    public boolean isEmpty(){
        return (head ==null);
    }

    public Node search(int data){
        Node node = head;
        if (!isEmpty()) {
            while (node != null) {
                if (node.data == data) {
                    return node;
                }
                node = node.next;
            }
        }
        return null;
    }

    // n finishes pointing to the last existing node
    // node is the newly created node
}


/*Advantage vs array & ArrayList
    LL can dynamically resize
    LL very fast for insert/remove
    LL more memory/space efficient than ArrayList as only ever has exactly as much capacity as it needs for number of elements

  Drawbacks
    LL is slow for retrieval as must traverse LL

 */

