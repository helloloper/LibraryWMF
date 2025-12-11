package librarycontrolhw;

import com.sun.source.tree.BinaryTree;
import java.util.Random;
import java.util.Hashtable;
public class GenerallibManager {
   private BSTree bstT = new BSTree();

public Hashtable generateBooks(){
    Random rnd = new Random(230316067);
    Random rnd1 = new Random(230316067);
    Hashtable<Integer,Book> allBooks = new Hashtable<Integer,Book>();
    for (int i = 0; i < 10; i++) {
        
        String bName = "berivan" + rnd.nextInt(1000);
        String author = "DVT" + rnd.nextInt(1000);
        int id = rnd1.nextInt(500);
        System.out.println(bName);
Book book = new Book(bName, author, id);
        allBooks.put(book.getId(), book);
                getBstT().insert(book);
        
    }
    System.out.println("books are exist");

return allBooks;}    
public void generateMembers(){

}

    public BSTree getBstT() {
        return bstT;
    }

    public void setBstT(BSTree bstT) {
        this.bstT = bstT;
    }


}
