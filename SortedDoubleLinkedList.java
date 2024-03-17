import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

    private Comparator<T> comparator;

    public SortedDoubleLinkedList(Comparator<T> compareableObject) {
        super();
        this.comparator = compareableObject;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (comparator.compare(data, head.data) <= 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (comparator.compare(data, tail.data) >= 0) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<T> current = head;
            while (current != null && comparator.compare(data, current.data) > 0) {
                current = current.next;
            }
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    @Override
    public void addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    @Override
    public void addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    @Override
    public ListIterator<T> iterator() {
        return super.iterator();
    }

    @Override
    public Node<T> remove(T data, Comparator<T> comparator) {
        return super.remove(data, comparator);
    }
}
