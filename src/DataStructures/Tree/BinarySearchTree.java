package DataStructures.Tree;


import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    static TreeNode root = new TreeNode(1);
    static ArrayList<Integer> integerList = new ArrayList<>();


    static class TreeNode{
        private int data;
        private TreeNode left, right;

        public TreeNode(int data){
            this.data = data;
            left = right = null;
        }
    }

    /*////////////TRAVERSALS////////////// all O(n) space & time
    All examples of Depth First Search; traverse a entire depth (left/right subtree, before going back up to higher levels of other side)
                    7
                 _/   \_
                1       9
               / \     / \
              0   3   8  10
                 / \
                2   5
                   / \
                  4   6
    Preorder Traversal = traverse from root -> to left subtree -> then right subtree: ROOT->LEFT->RIGHT
        7,1,0,3,2,5,4,6,9,8,10 -> to explore roots before any leaves to create copy of tree
    Inorder Traversal = traverse from left subtree -> to root -> then right subtree: LEFT->ROOT->RIGHT
        0,1,2,3,4,5,6,7,8,9,10 -> flattens tree back to original sequence used to create it. get ascending sorted order
    Postorder Traversal = traverse from left subtree -> to right subtree -> then root: LEFT->RIGHT->ROOT
        0,2,4,6,5,3,1,8,10,9,7 -> to explore leaves before roots. to delete tree from leaf to root */
    static void preOrderTraversal(TreeNode root, ArrayList<Integer> tree) {
        if (root==null) return;
        tree.add(root.data);  //push root node into the tree
        preOrderTraversal(root.left, tree);  //recursively run traversal from left child
        preOrderTraversal(root.right, tree); //the right child
    }
    static void preOrderTraversal(){   //wrapper method, so user doesn't have to pass parameters
        preOrderTraversal(root, integerList);
    }

    static void preOrderPrint(TreeNode root) {
        System.out.print(root.data + " ");
        preOrderPrint(root.left);
        preOrderPrint(root.right);
    }

    static void inOrderTraversal(TreeNode root, ArrayList<Integer> tree) {
        if (root==null) return;
        inOrderTraversal(root.left,tree);
        tree.add(root.data);
        inOrderTraversal(root.right, tree);
    }
    static void inOrderTraversal(){
        inOrderTraversal(root, integerList);
    }

    static void inOrderPrint(TreeNode root){
        inOrderPrint(root.left);
        System.out.print(root.data + " ");
        inOrderPrint(root.right);
    }

    static void postOrderTraversal(TreeNode root, ArrayList<Integer> tree) {
        postOrderTraversal(root.left, tree);
        postOrderTraversal(root.right, tree);
        tree.add(root.data);
    }

    static void postOrderTraversal(){
        postOrderTraversal(root, integerList);
    }

    static void postOrderPrint(TreeNode root){
        postOrderPrint(root.left);
        postOrderPrint(root.right);
        System.out.print(root.data + " ");
    }


    /////////////////BINARY SEARCH TREE METHODS///////////////
    public boolean insert(int data){   //best case = O(logn)   worst case = O(h) (height of tree)
        root = insert(root, data);   //tries to insert the data from the root of the tree
        return true;
    }

    private TreeNode insert(TreeNode node, int data){   //private helper method containing actual logic
        if (node==null) return new TreeNode(data);  //either tree is empty, or this is the leaf we can just create and initialize
        if (node.data > data) {    //data is less than current element, navigate leftwards of the current node
            node.left = insert(node.left, data);
        }
        if (node.data < data) {    //data is more than current element, navigate rightwards of the current node
            node.right = insert(node.right, data);
        } else return node;        //data is already present in the tree. this approach just eliminates duplicates
        return node;
    }


    public static void main(String[] args) {
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.right.left = new TreeNode(9);
        root.right.right.left = new TreeNode(10);
        root.right.right.right = new TreeNode(11);
        root.left.left.left.right = new TreeNode(12);

        //creating a tree with all the nodes, using an arrayList
        System.out.println("Pre-order traversal:");
        preOrderTraversal();
        for (int i : integerList) System.out.print(i + " ");
        integerList.clear();
        System.out.println("\nIn-order traversal:");
        inOrderTraversal();
        for (int i : integerList) System.out.print(i + " ");
        integerList.clear();
        System.out.println("\nPost-order traversal:");
        postOrderTraversal();
        for (int i : integerList) System.out.print(i + " ");

        //simple prints
        preOrderPrint(root);
        inOrderPrint(root);
        postOrderPrint(root);


        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(14); bst.insert(9); bst.insert(39); bst.insert(22); bst.insert(2); bst.insert(19);

    }


}
