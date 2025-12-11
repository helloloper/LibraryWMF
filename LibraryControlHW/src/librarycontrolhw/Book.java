package librarycontrolhw;
public class Book {
   
    private String bName;
    private String author;
    private int id;
    private boolean isAvailable;
    private int borrowCount;
    private int howManyisThere;

    public Book(String name, String author, int id) {
        this.bName = name;
        this.author = author;
        this.id = id;
      this.isAvailable=true;
      this.borrowCount=0;
    
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String name) {
        this.bName = name;
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

    public int getHowManyisThere() {
        return howManyisThere;
    }

    public void setHowManyisThere(int howManyisThere) {
        this.howManyisThere = howManyisThere;
    }
    
    
}
