package librarycontrolhw;

import java.util.Hashtable;

import java.util.Scanner;

public class LibraryControlHW {

    public static void main(String[] args) {
        GenerallibManager generalManager = new GenerallibManager();
        generalManager.generateBooks();
        generalManager.generateMembers();

        Scanner scn = new Scanner(System.in);

        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           WELCOME TO CBU LIBRARY MANAGEMENT SYSTEM       ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        while (true) {
            System.out.println("┌──────────────────────────────────────────────────────────┐");
            System.out.println("│                      MAIN MENU                           │");
            System.out.println("├──────────────────────────────────────────────────────────┤");
            System.out.println("│  [1]  Add New Book                                       │");
            System.out.println("│  [2]  Add New Member                                     │");
            System.out.println("│  [3]  Borrow Book                                        │");
            System.out.println("│  [4]  Return Book                                        │");
            System.out.println("│  [5]  Search Book                                        │");
            System.out.println("│  [6]  Show Popular Books (Heap)                          │");
            System.out.println("│  [7]  Undo Last Action (Stack)                           │");
            System.out.println("│  [8]  Show Waitlist (Queue)                              │");
            System.out.println("│  [9]  Display All Books (BST In-Order)                   │");
            System.out.println("│ [10]  Display All Members (BST In-Order)                 │");
            System.out.println("│ [11]  View Member's Borrowed Books                       │");
            System.out.println("├──────────────────────────────────────────────────────────┤");
            System.out.println("│  [0]  Exit                                               │");
            System.out.println("└──────────────────────────────────────────────────────────┘");
            System.out.print("\n>> Enter your choice: ");

            int choice = scn.nextInt();

            BSTree bstForBooks = generalManager.getBstT();
            BSTree bstForMembers = generalManager.getBstMembers();

            System.out.println();

            switch (choice) {
                case 1:
                    generalManager.addBook();
                    break;
                case 2:
                    generalManager.addMember();
                    break;
                case 3:
                    System.out.print(">> Enter Member ID: ");
                    int memberId = scn.nextInt();
                    scn.nextLine();
                    generalManager.borrowBook(generalManager.getAllMembers().get(memberId));
                    break;
                case 4:
                    System.out.print(">> Enter Member ID: ");
                    int id = scn.nextInt();
                    scn.nextLine();
                    Member borrowedMember = generalManager.getAllMembers().get(id);
                    generalManager.returnBook(borrowedMember);
                    break;
                case 5:
                    System.out.println("┌─────────────────────────────────┐");
                    System.out.println("│        SEARCH OPTIONS           │");
                    System.out.println("├─────────────────────────────────┤");
                    System.out.println("│  [1]  Search by ID (Hash)       │");
                    System.out.println("│  [2]  Search by Name (BST)      │");
                    System.out.println("│  [3]  Search by Author (BST)    │");
                    System.out.println("└─────────────────────────────────┘");
                    System.out.print(">> Enter search option: ");
                    int searchChoice = scn.nextInt();
                    scn.nextLine();
                    if (searchChoice == 1) {
                        System.out.print(">> Enter Book ID: ");
                        int searchId = scn.nextInt();
                        scn.nextLine();
                        generalManager.searchBookByID(searchId);
                    } else if (searchChoice == 2) {
                        System.out.print(">> Enter Book Name: ");
                        String searchName = scn.nextLine();
                        generalManager.searchBookByName(searchName);
                    } else if (searchChoice == 3) {
                        System.out.print(">> Enter Author Name: ");
                        String searchAuthor = scn.nextLine();
                        generalManager.searchBookByAuthor(searchAuthor);
                    } else {
                        System.out.println("[!] Invalid choice. Please try again.");
                    }
                    break;
                case 6:
                    generalManager.showMostPopularBooks();
                    break;
                case 7:
                    generalManager.undoLastAction();
                    break;
                case 8:
                    generalManager.showWaitList();
                    break;
                case 9:
                    System.out.println("--- All Books (In-Order Traversal) ---");
                    bstForBooks.seeTree();
                    break;
                case 10:
                    System.out.println("--- All Members (In-Order Traversal) ---");
                    bstForMembers.seeTree();
                    break;
                case 11:
                    generalManager.seeBorrowedBookList();
                    break;
                case 0:
                    System.out.println("\n╔══════════════════════════════════════════════════════════╗");
                    System.out.println("║      Thank you for using CBU Library System!             ║");
                    System.out.println("║                     Goodbye!                             ║");
                    System.out.println("╚══════════════════════════════════════════════════════════╝\n");
                    scn.close();
                    return;
                default:
                    System.out.println("[!] Invalid option. Please enter a number between 0-11.");
            }
            System.out.println();
        }
    }

}
