package librarycontrolhw;

import java.util.Hashtable;

public class LibraryControlHW {

    public static void main(String[] args) {

        System.out.println("hello welcome to CBUmerkezKutup");
        System.out.println("""
                           1:kitap ekle  hash map ve bst ye ekleyecek
                           2:üye ekle       ""          ""
                           3:ödünç al  //  hash maptan kitapın isAvailable false,, popüler kitaplara +1 puanvis,,, available ffalssa bekleme listesine
                           4:geri al kitabım //isAvalabşe trıe
                           5:kitap arama  bst çalıştıırır filtreleme yapılır mı allah bilir ,, id den ararsan hash ile bulursubn
                           6:popüler kitaplar heap e priority ekleme
                           7:undo acyion ları stack den alır
                           8:kitabın beklerlerini gösterecek queue den
                           0:çikiç
                           """);//
        GenerallibManager generalManager = new GenerallibManager();
        Hashtable<Integer, Book> table = generalManager.generateBooks();
        BSTree bst = generalManager.getBstT();
        
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
        bst.seeTree();
   

    }

}
