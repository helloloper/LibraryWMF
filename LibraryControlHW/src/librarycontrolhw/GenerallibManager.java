package librarycontrolhw;

import com.sun.source.tree.BinaryTree;
//import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GenerallibManager {
    HashTable <Integer, Book> allBooks = new HashTable<Integer,Book>();
    HashTable <Integer, Member> allMembers = new HashTable<Integer,Member>();
    private BSTree<Book> bstT = new BSTree<>();
    private BSTree<Member> bstMembers = new BSTree<>();
    private ArrayList<Book> popularsList = new ArrayList<>(); //En çok ödünç alınan kitapları tutmak içindir
    private Stack<String> undoStack = new Stack<>(); // undo işlemi için field
   // private Hashtable<Integer, Book> allBooks = new Hashtable<Integer, Book>();
   // private Hashtable<Integer, Member> allMembers = new Hashtable<>();

    public void generateBooks() {
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
            popularsList.add(book); //Popüler kitap listesine eklemektedir
            
        }
        System.out.println("books are exist");
        
    }    

    public void generateMembers() {
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

    public void addMember() {
        Scanner scn = new Scanner(System.in);
        System.out.print("enter name:");
        String name = scn.nextLine();
        System.out.println("enter lastNAME");
        String surName = scn.nextLine();
        System.out.println("enter id");
        int id = scn.nextInt();
        scn.nextLine();
        Member nMember = new Member(name, surName, id);
        getBstMembers().insert(nMember);//to insert onto bst for all members
        getAllMembers().put(id, nMember); //to add new member in hashtable
        
        System.out.println("member added succesfully");
        
    }

    public void addBook() {
        Scanner scn = new Scanner(System.in);
        System.out.print("enter book name:");
        String bName = scn.nextLine();
        System.out.print("enter author name:");
        String author = scn.nextLine();
        System.out.println("id:");
        int id = scn.nextInt();
        scn.nextLine();
        Book newBook = new Book(bName, author, id);
        getAllBooks().put(id, newBook);
        getBstT().insert(newBook);
        System.out.println("boook added succesfully");
        popularsList.add(newBook);//Yeni kitapı popülerler arasına eklemek üzerine çalışır
        undoStack.push("ADD_BOOK: " + id); // undo
        // undo işlemleri bu şekilde yazılıyormuş ondan böyle yazdım
        
    }

    public void borrowBook(Member whoBorrow) {
        Scanner scn = new Scanner(System.in);
        System.out.println("enter or scan book id:");
        int id = scn.nextInt();
        scn.nextLine();
        Book book = getAllBooks().get(id);
        if (whoBorrow != null) {
            if (book == null) {
                System.out.println("sorry we dont have that book");
            } else {
                if (book.isIsAvailable()) {
                    whoBorrow.getBorrowedBooks().add(book);
                    book.setIsAvailable(false);
                    // waitlist için gerekli değişiklikler
                    book.setBorrowCount(book.getBorrowCount() + 1);
                    System.out.println("basarili odunc almak");
                    // undo için gerekli değişiklikler
                    undoStack.push("BORROW_BOOK: " + whoBorrow.getId() + ":" + book.getId());
                } else {
                    // waitlist için gerekli değişiklikler
                    book.getWaitList().enqueue(whoBorrow);
                    System.out.println(whoBorrow.getName() + " listeye alındı.");
                }
            }

        } else {
            System.out.println("invalid member id");
        }
    }
  
    public void showMostPopularBooks() { //popüler kitaplar için metod
        if (popularsList.isEmpty()) {
            System.out.println("There are no books in the system");
            return;
        }
        System.out.println("\n--- Most Popular Books ---");
        BookHeap tempHeap = new BookHeap();
        int addedCount=0;
        for (Book b : popularsList) {
            if (b.getBorrowCount() > 0) {
                tempHeap.add(b);
                addedCount++;
            }
        }
        if (addedCount==0){
            System.out.println(" There aren't any books borrowed from the library");
            return;
        }
        int count = 1;
        while (true) {
            Book topBook = tempHeap.removeTop();
            if (topBook == null) break;

            System.out.println(count + ". " + topBook.getbName() +
                    " | Author: " + topBook.getAuthor() +
                    " | Borrow Count: " + topBook.getBorrowCount());
            count++;
        }
    }

    public void seeBorrowedBookList() {
        Scanner scn = new Scanner(System.in);
        System.out.println("enter member id:");
        int id = scn.nextInt();
        scn.nextLine();
        if (getAllMembers().get(id) != null) {
            getAllMembers().get(id).getBorrowedBooks().seeList();
        }
    }

    public void returnBook(Member whoBorrow) {
        Scanner scn = new Scanner(System.in);
        System.out.println("u borrowed that books:");
        whoBorrow.getBorrowedBooks().seeList();
        System.out.println("enter which u remove");
        int bookId = scn.nextInt();scn.nextLine();
        if(whoBorrow.getBorrowedBooks().getBook(bookId)!=null){
        whoBorrow.getBorrowedBooks().getBook(bookId).setIsAvailable(true);}
        else{System.out.println("there is no boooookkkkkkkkkkk");}
        whoBorrow.getBorrowedBooks().remove(whoBorrow.getBorrowedBooks().getBook(bookId));
    }

    // waitlist yazdıran metod
    public void showWaitList() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter a book id that you want to see the waitlist : ");
        int bookId = scn.nextInt();
        scn.nextLine();

        Book book = getAllBooks().get(bookId);

        if(book != null) {
            System.out.println("Name : " + book.getbName() +" ID : " + bookId + " --- waitlist  ----->   " );
            book.getWaitList().printQueue();
            System.out.println("----------------------");
        } else {
            System.out.println("wrong id");
        }
    }

    // undoing action method
    public void undoLastAction() {
        String action = undoStack.pop();

        if(action == null) {
            System.out.println("no action.");
            return;
        }

        String[] parts = action.split(":");
        String actionType = parts[0];
        int id = Integer.parseInt(parts[1]);

        switch (actionType) {
            case "ADD_BOOK" :
                getAllBooks().remove(id);
                System.out.println("successful");
                break;
            case "ADD_MEMBER" :
                getAllMembers().remove(id);
                System.out.println("successful");
                break;
            case "BORROW_BOOK" :
                int bookId = Integer.parseInt(parts[2]);
                Member member = getAllMembers().get(id);
                Book book = getAllBooks().get(bookId);
                if(member != null && book != null) {
                    member.getBorrowedBooks().remove(book);
                    book.setIsAvailable(true);
                    if (book.getBorrowCount() > 0) {
                        book.setBorrowCount(book.getBorrowCount() - 1);
                    }
                    System.out.println("successful");
                }
                break;
            default:
                System.out.println("hacı böyle bi işlem geri alınamıyo");
        }
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
    
    public HashTable<Integer, Book> getAllBooks() {
        return allBooks;
    }
    
    public void setAllBooks(HashTable<Integer, Book> allBooks) {
        this.allBooks = allBooks;
    }
    
    public HashTable<Integer, Member> getAllMembers() {
        return allMembers;
    }
    
    public void setAllMembers(HashTable<Integer, Member> allMembers) {
        this.allMembers = allMembers;
    }
    
}
