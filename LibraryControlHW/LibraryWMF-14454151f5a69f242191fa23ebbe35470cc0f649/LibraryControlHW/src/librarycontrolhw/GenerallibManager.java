package librarycontrolhw;

import com.sun.source.tree.BinaryTree;
//import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GenerallibManager {
    HashTable<Integer, Book> allBooks = new HashTable<Integer, Book>();
    HashTable<Integer, Member> allMembers = new HashTable<Integer, Member>();
    private BSTree<Book> bstT = new BSTree<>();
    private BSTree<Member> bstMembers = new BSTree<>();
    private ArrayList<Book> masterBookList = new ArrayList<>();
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
            masterBookList.add(book); // Popüler kitap listesine eklemektedir

        }
        System.out.println("[i] Sample books have been generated successfully.");

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
            // System.out.println("---"+id);
            // System.out.println(newMember.getName() + "14654654654"); //
            getBstMembers().insert(newMember);
        }
        System.out.println("[i] Sample members have been generated successfully.");

    }

    public void addMember() {
        Scanner scn = new Scanner(System.in);
        System.out.print(">> Enter first name: ");
        String name = scn.nextLine();
        System.out.print(">> Enter last name: ");
        String surName = scn.nextLine();
        System.out.print(">> Enter member ID: ");
        int id = scn.nextInt();
        scn.nextLine();
        Member nMember = new Member(name, surName, id);
        getBstMembers().insert(nMember);
        getAllMembers().put(id, nMember);

        System.out.println("[✓] Member added successfully!");

        undoStack.push("ADD_MEMBER: " + id);
    }

    public void addBook() {
        Scanner scn = new Scanner(System.in);
        System.out.print(">> Enter book title: ");
        String bName = scn.nextLine();
        System.out.print(">> Enter author name: ");
        String author = scn.nextLine();
        System.out.print(">> Enter book ID: ");
        int id = scn.nextInt();
        scn.nextLine();
        Book newBook = new Book(bName, author, id);
        getAllBooks().put(id, newBook);
        getBstT().insert(newBook);
        System.out.println("[✓] Book added successfully!");
        masterBookList.add(newBook);
        undoStack.push("ADD_BOOK: " + id);
    }

    public void borrowBook(Member whoBorrow) {
        Scanner scn = new Scanner(System.in);
        System.out.print(">> Enter book ID to borrow: ");
        int id = scn.nextInt();
        scn.nextLine();
        Book book = getAllBooks().get(id);
        if (whoBorrow != null) {
            if (book == null) {
                System.out.println("[!] Book not found in the library.");
            } else {
                if (book.isIsAvailable()) {
                    whoBorrow.getBorrowedBooks().add(book);
                    book.setIsAvailable(false);
                    book.setBorrowCount(book.getBorrowCount() + 1);
                    System.out.println("[✓] Book borrowed successfully!");
                    undoStack.push("BORROW_BOOK: " + whoBorrow.getId() + ":" + book.getId());
                } else {
                    book.getWaitList().enqueue(whoBorrow);
                    System.out.println(
                            "[i] Book is unavailable. " + whoBorrow.getName() + " has been added to the waitlist.");
                }
            }
        } else {
            System.out.println("[!] Invalid member ID.");
        }
    }

    public void showMostPopularBooks() {
        if (masterBookList.isEmpty()) {
            System.out.println("[!] There are no books in the system.");
            return;
        }
        System.out.println("\n┌─────────────────────────────────────────────┐");
        System.out.println("│          MOST POPULAR BOOKS (Heap)          │");
        System.out.println("└─────────────────────────────────────────────┘");
        BookHeap tempHeap = new BookHeap();
        int addedCount = 0;
        for (Book b : masterBookList) {
            if (b.getBorrowCount() > 0) {
                tempHeap.add(b);
                addedCount++;
            }
        }
        if (addedCount == 0) {
            System.out.println("[i] No books have been borrowed yet.");
            return;
        }
        int count = 1;
        while (true) {
            Book topBook = tempHeap.removeTop();
            if (topBook == null)
                break;
            System.out.println("  " + count + ". " + topBook.getbName() +
                    " | Author: " + topBook.getAuthor() +
                    " | Borrowed: " + topBook.getBorrowCount() + " times");
            count++;
        }
    }

    public void seeBorrowedBookList() {
        Scanner scn = new Scanner(System.in);
        System.out.print(">> Enter member ID: ");
        int id = scn.nextInt();
        scn.nextLine();
        if (getAllMembers().get(id) != null) {
            System.out.println("\n--- Borrowed Books List ---");
            getAllMembers().get(id).getBorrowedBooks().seeList();
        } else {
            System.out.println("[!] Member not found.");
        }
    }

    public void returnBook(Member whoBorrow) {
        Scanner scn = new Scanner(System.in);
        System.out.println("\n--- Your Borrowed Books ---");
        whoBorrow.getBorrowedBooks().seeList();
        System.out.print(">> Enter book ID to return: ");
        int bookId = scn.nextInt();
        scn.nextLine();
        if (whoBorrow.getBorrowedBooks().getBook(bookId) != null) {
            whoBorrow.getBorrowedBooks().getBook(bookId).setIsAvailable(true);
            whoBorrow.getBorrowedBooks().remove(whoBorrow.getBorrowedBooks().getBook(bookId));
            System.out.println("[✓] Book returned successfully!");
        } else {
            System.out.println("[!] Book not found in your borrowed list.");
        }
    }

    // waitlist yazdıran metod
    public void showWaitList() {
        Scanner scn = new Scanner(System.in);
        System.out.print(">> Enter book ID to view waitlist: ");
        int bookId = scn.nextInt();
        scn.nextLine();

        Book book = getAllBooks().get(bookId);

        if (book != null) {
            System.out.println("\n┌─────────────────────────────────────────────┐");
            System.out.println("│               WAITLIST (Queue)              │");
            System.out.println("└─────────────────────────────────────────────┘");
            System.out.println("  Book: " + book.getbName() + " (ID: " + bookId + ")");
            book.getWaitList().printQueue();
        } else {
            System.out.println("[!] Book not found.");
        }
    }

    public void undoLastAction() {
        String action = undoStack.pop();

        if (action == null) {
            System.out.println("[!] No action to undo.");
            return;
        }

        String[] parts = action.split(":");
        String actionType = parts[0];
        int id = Integer.parseInt(parts[1]);

        switch (actionType) {
            case "ADD_BOOK":
                getAllBooks().remove(id);
                System.out.println("[✓] Undo successful: Book addition reversed.");
                break;
            case "ADD_MEMBER":
                getAllMembers().remove(id);
                System.out.println("[✓] Undo successful: Member addition reversed.");
                break;
            case "BORROW_BOOK":
                int bookId = Integer.parseInt(parts[2]);
                Member member = getAllMembers().get(id);
                Book book = getAllBooks().get(bookId);
                if (member != null && book != null) {
                    member.getBorrowedBooks().remove(book);
                    book.setIsAvailable(true);
                    if (book.getBorrowCount() > 0) {
                        book.setBorrowCount(book.getBorrowCount() - 1);
                    }
                    System.out.println("[✓] Undo successful: Book borrowing reversed.");
                }
                break;
            default:
                System.out.println("[!] This action cannot be undone.");
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

    public void searchBookByID(int id) {
        Book foundBook = allBooks.get(id);

        if (foundBook != null) {
            System.out.println(foundBook.toString());
        } else {
            System.out.println("[!] No book found with ID: " + id);
        }
    }

    public void searchBookByName(String name) {
        boolean found = false;
        System.out.println("\n--- Search Results for: '" + name + "' ---");
        for (Book b : masterBookList) {
            if (b.getbName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("  " + b.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("[!] No books found with that name.");
        }
    }

    public void searchBookByAuthor(String author) {
        boolean found = false;
        System.out.println("\n--- Books by Author: '" + author + "' ---");
        for (Book b : masterBookList) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                System.out.println("  " + b.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("[!] No books found by that author.");
        }
    }
}
