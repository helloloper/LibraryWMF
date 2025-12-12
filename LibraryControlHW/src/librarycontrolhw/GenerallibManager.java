package librarycontrolhw;

import com.sun.source.tree.BinaryTree;
import java.util.Random;
import java.util.Scanner;
import java.util.Hashtable;
public class GenerallibManager {
   private BSTree<Book> bstT = new BSTree<>();
   private BSTree<Member> bstMembers = new BSTree<>();
 private Hashtable <Integer,Book> allBooks = new Hashtable<Integer,Book>();
private Hashtable<Integer,Member> allMembers = new Hashtable<>();
 public void generateBooks(){
    Random rnd = new Random(230316067);
    Random rnd1 = new Random(230316067);
    
    for (int i = 0; i < 10; i++) {
        
        String bName = "book" + rnd.nextInt(1000);
            String author = "kapak1" + rnd.nextInt(1000);
        int id = rnd1.nextInt(500);
       // System.out.println("bookid:"+id);
Book book = new Book(bName, author, id);
            getAllBooks().put(book.getId(), book);
                getBstT().insert(book);
        
    }
    System.out.println("books are exist");

}   
public void generateMembers(){
    Random rnd = new Random(230316067);
    Random rnd2 = new Random(230316067);
    
    
    for (int i = 0; i < 10; i++) {
        String mName = "member" + rnd.nextInt(500);
    String surName = "lastNameM" + rnd.nextInt(1000);
    int id = rnd2.nextInt(750);
    Member newMember = new Member(mName, surName, id);
            getAllMembers().put(newMember.getId(), newMember);
          //  System.out.println("---"+id);
        //System.out.println(newMember.getName() + "14654654654"); //
    getBstMembers().insert(newMember);
    }
        System.out.println("members are exist");

    
    

}
public void addMember(){
    Scanner scn = new Scanner(System.in);
    System.out.print("enter name:");
    String name =scn.nextLine();
    System.out.println("enter lastNAME");
    String surName =scn.nextLine();
    System.out.println("enter id");
    int id = scn.nextInt();scn.nextLine();
    Member nMember = new Member(name, surName, id);
    getBstMembers().insert(nMember);//to insert onto bst for all members
    getAllMembers().put(id, nMember); //to add new member in hashtable
    
         System.out.println("member added succesfully");

}
 public void addBook(){
  Scanner scn = new Scanner(System.in);
     System.out.print("enter book name:");
     String bName = scn.nextLine();
     System.out.print("enter author name:");
     String author = scn.nextLine();
     System.out.println("id:");
     int id = scn.nextInt();scn.nextLine();
     Book newBook = new Book(bName, author, id);
     getAllBooks().put(id, newBook);
     getBstT().insert(newBook);
     System.out.println("boook added succesfully");
 }
 public void borrowBook(Member whoBorrow){
       Scanner scn = new Scanner(System.in);
     System.out.println("enter or scan book id:");
     int id = scn.nextInt();scn.nextLine();
     Book book = getAllBooks().get(id);
     if(whoBorrow!=null){
     if(book==null){System.out.println("sorry we dont have that book");}
     else{
             if(book.isIsAvailable()){
                 whoBorrow.getBorrowedBooks().add(book);
                 book.setIsAvailable(false);
             System.out.println("basarili odunc almak");
             }
             else{System.out.println("listeye eklendin");}
             }
     
     }else{System.out.println("invalid member id");}
 }
 public void seeBorrowedBookList(){
      Scanner scn = new Scanner(System.in);
     System.out.println("enter member id:");
     int id = scn.nextInt();scn.nextLine();
     if(getAllMembers().get(id)!=null){
     getAllMembers().get(id).getBorrowedBooks().seeList();
 }
 }
 public void returnBook(Member whoBorrow){
     Book book = new Book("", "", 0);
 whoBorrow.getBorrowedBooks().remove(book);
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

    public Hashtable<Integer,Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(Hashtable<Integer,Book> allBooks) {
        this.allBooks = allBooks;
    }

    public Hashtable<Integer,Member> getAllMembers() {
        return allMembers;
    }

    public void setAllMembers(Hashtable<Integer,Member> allMembers) {
        this.allMembers = allMembers;
    }


}
