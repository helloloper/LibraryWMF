package librarycontrolhw;

public class BSTree {

    private bstNode root;

    public void insert(Book book) {
        root = insertRec(root, book);
    }

    private bstNode insertRec(bstNode root, Book book) {
        
        if (root == null) {
            bstNode newNode = new bstNode(book);
            return newNode;
            
        } else if (book.getbName().compareTo(root.data.getbName()) > 0) {
            
           root.right= insertRec(root.right, book);
        } else {
           root.left= insertRec(root.left, book);
        }
    
    return root;}

    public void seeTree() {
        if (root == null) {
            System.out.println("no books");
        } else {
            inorderRec(root);
            
        }
        
    }

    public void inorderRec(bstNode node) {
        if (node == null) {
            return;
        }
            inorderRec(node.left);
            System.out.println(node.data.getbName());
            inorderRec(node.right);
        }
        
    }
    

