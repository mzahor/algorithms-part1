import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node<Item> first = null;
    private Node<Item> last = null;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            Node<Item> newFirst = new Node<Item>(item);
            newFirst.SetNext(first);
            assert !first.HasPrev();
            first.SetPrev(newFirst);
            first = newFirst;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            first = new Node(item);
            last = first;
        } else {
            Node<Item> newLast = new Node<Item>(item);
            newLast.SetPrev(last);
            assert !last.HasNext();
            last.SetNext(newLast);
            last = newLast;
        }
        size++;
    }

    public Item removeFirst() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        Item value = first.getValue();

        if (first.HasNext()) {
            first = first.getNext();
            first.SetPrev(null);
        } else {
            last = first = null;
        }
        size--;
        return value;
    }

    public Item removeLast() {
        if (size < 1) {
            throw new NoSuchElementException();
        }

        Item value = last.getValue();

        if (last.HasPrev()) {
            last = last.getPrev();
            last.SetNext(null);
        } else {
            last = first = null;
        }
        size--;
        return value;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Item value = current.getValue();
                current = current.getNext();
                return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
//                if (current == null) {
//                    throw new NoSuchElementException();
//                }
//                if (current == last) {
//                    removeLast();
//                }
//                if (current == first) {
//                    removeFirst();
//                }
//
//                current.getPrev().SetNext(current.getNext());
            }
        };
    }

    private class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T value;

        public Node(T value) {
            this.value = value;
            prev = next = null;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void SetPrev(Node<T> prev) {
            this.prev = prev;
        }

        public void SetNext(Node<T> next) {
            this.next = next;
        }

        public boolean HasNext() {
            return next != null;
        }

        public boolean HasPrev() {
            return prev != null;
        }

        public T getValue() {
            return value;
        }
    }
}