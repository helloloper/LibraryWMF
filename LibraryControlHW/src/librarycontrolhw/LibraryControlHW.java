package librarycontrolhw;

import java.util.Hashtable;

public class LibraryControlHW {
    public static void main(String[] args) {
        System.out.println("hello welcome to CBUmerkezKutup");
        GenerallibManager generalManager = new GenerallibManager();
        
    Hashtable<Integer,Book> table = generalManager.generateBooks();
    Book b = table.get(12);
        System.out.println("book name:" + b.getbName());
    BookLinkedList bLL = new BookLinkedList();
bLL.add(1);
bLL.add(5);
bLL.add("MAKARA");
bLL.add(4);
bLL.add(8);
bLL.add(9);
bLL.seeList();

    }
    
}
