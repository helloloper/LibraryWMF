package librarycontrolhw;

public class BSTree <T extends Comparable<T>> {

    private bstNode<T> root;

    public void insert(T value) {
        root = insertRec(root, value);
    }

    private bstNode<T> insertRec(bstNode<T> root, T value) {
        
        if (root == null) {
            bstNode newNode = new bstNode<>(value);
            return newNode;
            
        } else if (value.compareTo(root.data) > 0) {
            
           root.right= insertRec(root.right, value);
        } else {
           root.left= insertRec(root.left, value);
        }
    
    return root;}

    public void seeTree() {
        if (root == null) {
            System.out.println("no books");
        } else {
            inorderRec(root);
            
        }
        
    }

    public void inorderRec(bstNode<T> node) {
        if (node == null) {
            return;
        }
            inorderRec(node.left);
            System.out.println(node.data);
            inorderRec(node.right);
        }
        
    }
    

