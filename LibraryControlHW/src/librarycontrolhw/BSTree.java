package librarycontrolhw;

public class BSTree {
    private bstNode root;
    public void insert(Book book){
    root =insertRec(root, book);
    }
    private bstNode insertRec(bstNode root,Book book){
        
    if(root ==null){
        bstNode newNode = new bstNode(book);
        return newNode;
    
    }
    else if(book.getbName().compareTo(root.data.getbName())>0){
    
        return insertRec(root.right, book);
    }
    else{
    return insertRec(root.left, book);
    }
    }
    
}
