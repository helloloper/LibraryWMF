package librarycontrolhw;
public class Book {
   
    private String name;
    private String author;
    private int id;
    private boolean isAvailable;
    private int borrowCount;

    public Book(String name, String author, int id) {
        this.name = name;
        this.author = author;
        this.id = id;
      this.isAvailable=true;
      this.borrowCount=0;
    
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }
    
    
}
