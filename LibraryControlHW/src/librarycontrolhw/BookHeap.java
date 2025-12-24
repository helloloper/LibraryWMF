package librarycontrolhw;

import java.util.ArrayList;

public class BookHeap {

    private ArrayList<Book> heap;

    public BookHeap() {
        heap = new ArrayList<>();
    }

    public Book getMostPopular() {
        if (heap.isEmpty()) {
            return null;
        } else {
            return heap.get(0);
        }
    }

    public void add(Book book) {
        heap.add(book);
        moveUp(heap.size() - 1);
    }

    public void moveUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).getBorrowCount() <= heap.get(parent).getBorrowCount()) {
                break;
            }
            Book temp = heap.get(index);
            heap.set(index, heap.get(parent));
            heap.set(parent, temp);

            index = parent;
        }

    }


    public Book removeTop() {
        if (heap.isEmpty()) {
            return null;
        }
        Book root = heap.get(0);
        Book last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            moveDown(0);
        }
        return root;
    }

    public void moveDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && heap.get(left).getBorrowCount() > heap.get(largest).getBorrowCount()) {
                largest = left;
            }

            if (right < size && heap.get(right).getBorrowCount() > heap.get(largest).getBorrowCount()) {
                largest = right;
            }
            if (largest == index) {
                break;
            }
            Book temp = heap.get(index);
            heap.set(index, heap.get(largest));
            heap.set(largest, temp);

            index = largest;
        }
    }

}
