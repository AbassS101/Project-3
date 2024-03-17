import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// Generic class for a basic doubly linked list
public class BasicDoubleLinkedList<T> implements Iterable<T> {
    // Node class for the elements in the linked list
    protected static class Node<T> {
        protected T data;
        protected Node<T> prev;
        protected Node<T> next;

        protected Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    protected Node<T> head; // Reference to the first node in the list
    protected Node<T> tail; // Reference to the last node in the list
    protected int size;     // Number of elements in the list

    // Constructor to initialize an empty linked list
    public BasicDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Method to add a new element to the end of the list
    public void addToEnd(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Method to add a new element to the front of the list
    public void addToFront(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Method to get the data of the first element in the list
    public T getFirst() {
        if (head != null)
            return head.data;
        else return null;
    }

    // Method to get the data of the last element in the list
    public T getLast() {
        if (tail != null)
            return tail.data;
        else
            return null;
    }

    // Method to get the size of the list
    public int getSize() {
        return size;
    }

    // Iterator implementation for the linked list
    @Override
    public ListIterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    // Method to remove a node with specified data from the list
    public Node<T> remove(T targetData, Comparator<T> comparator) {
        Node<T> current = head;
        while (current != null) {
            if (comparator.compare(current.data, targetData) == 0) {
                if (current == head) {
                    head = current.next;
                } else {
                    current.prev.next = current.next;
                }
                if (current == tail) {
                    tail = current.prev;
                } else {
                    current.next.prev = current.prev;
                }
                size--;
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Method to retrieve and remove the first element from the list
    public T retrieveFirstElement() {
        if (head != null) {
            T data = head.data;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
            return data;
        }
        return null;
    }

    // Method to retrieve and remove the last element from the list
    public T retrieveLastElement() {
        if (tail != null) {
            T data = tail.data;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
            size--;
            return data;
        }
        return null;
    }

    // Method to convert the linked list to an ArrayList
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayList = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            arrayList.add(current.data);
            current = current.next;
        }
        return arrayList;
    }

    // Inner class for implementing ListIterator
    protected class DoubleLinkedListIterator implements ListIterator<T> {
        private Node<T> nextNode;
        private Node<T> lastReturned;
        private int nextIndex;

        // Constructor for the iterator
        public DoubleLinkedListIterator() {
            this.nextNode = head;
            this.nextIndex = 0;
        }

        // Method to check if there is a next element
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        // Method to get the next element
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = nextNode;
            nextNode = nextNode.next;
            nextIndex++;
            return lastReturned.data;
        }

        // Method to check if there is a previous element
        @Override
        public boolean hasPrevious() {
            if (nextNode != head && size != 0)
                return true;
            return false;
        }

        // Method to get the previous element
        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextNode == null) {
                nextNode = tail;
            } else {
                nextNode = nextNode.prev;
            }
            lastReturned = nextNode;
            nextIndex--;
            return lastReturned.data;
        }

        // Unsupported operations for the ListIterator
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }
}

