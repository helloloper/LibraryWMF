package librarycontrolhw;

import java.util.Hashtable;

import java.util.Scanner;

public class LibraryControlHW {
/*
    WE USED SCHOOL NUMBER FOR BOOKS AND MEMBERS;
    */
    public static void main(String[] args) {
        GenerallibManager generalManager = new GenerallibManager();
        generalManager.generateBooks();//to create books just for this program
        generalManager.generateMembers();//to create members just for this program
//there is 10 books but iam using only one which has 12 id number
//there is 10 members but iam using only one which has 96 id number
        System.out.println("hello welcome to CBUmerkezKutup");
        while (true) {
            System.out.print("""
                           1:||kitap ekle  ||
                           2:||uye ekle    ||   
                           3:||odunc al    ||
                           4:||geri teslim ||
                           5:kitap arama  bst çalıştıırır filtreleme yapılır mı allah bilir ,, id den ararsan hash ile bulursubn
                           6:|| heap ile popular list (enes enayisi burayı değiştirmeyi unutmus) ||
                           7:|| undo (halledilmiştir by ozkancoding ||
                           8:|| waitlist (made by ozkancoding) ||
                           9:||tum kitaplari gosterir in order||
                          10:||tum uyeleri gosterir in order
                          11:||kullanicinin odunc aldıgı listeyi görebilme||
                           0:çikiç
                           """);//1  (hash map ve bst ye ekleyecek) 2 aynı şekil || 3 
            //hash maptan kitapın isAvailable false,, popüler kitaplara +1 puanvis,,, available ffalssa bekleme listesine

            Scanner scn = new Scanner(System.in);
            int a = scn.nextInt();
            BookLinkedList bLL = new BookLinkedList();

            BSTree bstForBooks = generalManager.getBstT();//to put all the books in tree
            BSTree bstForMembers = generalManager.getBstMembers();//to put all the members in tree
            switch (a) {
                case 1:
                    generalManager.addBook();
                    break;//to create new books like if new book exist
                case 2:
                    generalManager.addMember();
                    break;//to create new member
                case 3:
                    System.out.print("borrower id:");
                    int memberId = scn.nextInt();
                    scn.nextLine();
                    generalManager.borrowBook(generalManager.getAllMembers().get(memberId));
                    break;
                case 4:System.out.println("whats ur id:");
                    int id = scn.nextInt();scn.nextLine();
                    Member borrowedMember = generalManager.getAllMembers().get(id);
                    generalManager.returnBook(borrowedMember);
                    break;
                case 6:
                    generalManager.showMostPopularBooks(); // Popüler kitaplar kullanıcıya sunulmaktadır
                    break;
                case 7: 
                    generalManager.undoLastAction(); // undoingggggg
                    break;    
                case 8: 
                    generalManager.showWaitList(); // waitlisttttttt
                    break;
                case 9:
                    bstForBooks.seeTree();
                    break;
                case 10:
                    bstForMembers.seeTree();
                    break;
                case 11:
                    generalManager.seeBorrowedBookList();break;

                case 0:
                    return;

                default:
                    System.out.println("please only number between 0-10");
            }

            //  Book b = generalManager.getAllBooks().get(12);
            // System.out.println("we found the book, name:" + b.getbName());
            //generalManager.getAllMembers().get(96).getBorrowedBooks().seeList();
            // System.out.println(generalManager.getAllBooks().get(12).isIsAvailable());
        }
    }

}
