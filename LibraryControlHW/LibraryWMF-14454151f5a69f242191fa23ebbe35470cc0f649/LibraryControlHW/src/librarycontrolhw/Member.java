package librarycontrolhw;

public class Member implements Comparable<Member> {
    private String name;
    private String surName;
    private int id;
    private BookLinkedList borrowedBooks = new BookLinkedList();

    public Member(String name, String surName, int id) {
        this.name = name;
        this.surName = surName;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookLinkedList getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(BookLinkedList borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public int compareTo(Member other) {// this method also for override the comparable class
        return this.name.compareTo(other.name);
    }

    public String toString() {
        return "[ID: " + getId() + "] " + getName() + " " + getSurName();
    }

}
