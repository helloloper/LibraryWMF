package librarycontrolhw;

import com.sun.source.tree.BinaryTree;
import java.util.Random;
import java.util.Hashtable;
public class GenerallibManager {
   private BSTree<Book> bstT = new BSTree<>();
   private BSTree<Member> bstMembers = new BSTree<>();

public Hashtable generateBooks(){
    Random rnd = new Random(230316067);
    Random rnd1 = new Random(230316067);
    Hashtable<Integer,Book> allBooks = new Hashtable<Integer,Book>();
    for (int i = 0; i < 10; i++) {
        
        String bName = "brvn" + rnd.nextInt(1000);
            String author = "DVT" + rnd.nextInt(1000);
        int id = rnd1.nextInt(500);
        System.out.println(bName);
Book book = new Book(bName, author, id);
        allBooks.put(book.getId(), book);
                getBstT().insert(book);
        
    }
    System.out.println("books are exist");

return allBooks;}   

public Hashtable generateMembers(){
    Random rnd = new Random(230316067);
    Random rnd2 = new Random(230316067);
    Hashtable<Integer,Member> allMembers = new Hashtable<>();
    
    for (int i = 0; i < 10; i++) {
        String mName = "emn" + rnd.nextInt(500);
    String surName = "david" + rnd.nextInt(1000);
    int id = rnd2.nextInt();
    Member newMember = new Member(mName, surName, id);
    allMembers.put(newMember.getId(), newMember);
        System.out.println(newMember.getName() + "14654654654");
    getBstMembers().insert(newMember);
        System.out.println("members are exist");
    }

    
    
return allMembers;
}

    public BSTree<Book> getBstT() {
        return bstT;
    }

    public void setBstT(BSTree bstT) {
        this.bstT = bstT;
    }

    public BSTree<Member> getBstMembers() {
        return bstMembers;
    }

    public void setBstMembers(BSTree bstMembers) {
        this.bstMembers = bstMembers;
    }


}
