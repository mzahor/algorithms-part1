public class BinaryHeap<T extends Comparable<T>> {
    private T[] data;
    // always points to last item
    private int pointer;
    private int size;

    public BinaryHeap() {
        this(4);
    }

    public BinaryHeap(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size of heap is too small.");
        }

        this.size = size;
        data = (T[]) new Comparable[size];
        pointer = -1;
    }

    public BinaryHeap(T[] initialData) {
        this(initialData.length);

        for (T anInitialData : initialData) {
            insert(anInitialData);
        }
    }

    public void setData(T[] data) {
        this.data = data;
        this.size = data.length;
        this.pointer = this.size - 1;
    }

    public boolean invariantsMaintained() {
        return invariantsMaintained(0);
    }

    private boolean invariantsMaintained(int node) {
        if (getData(node) == null) {
            return true;
        }

        if ((getData(node * 2 + 1) != null && data[node * 2 + 1].compareTo(data[node]) > 0)
                || (getData(node * 2 + 2) != null && data[node * 2 + 2].compareTo(data[node]) > 0))
            return false;
        return invariantsMaintained(node * 2 + 1) && invariantsMaintained(node * 2 + 2);
    }

    public T getMax() {
        return data[0];
    }

    private void swim(int i) {
        if (i == 0) return;
        int parent = (i - 1) / 2;
        if (data[parent].compareTo(data[i]) < 0) {
            exchange(parent, i);
        }

        swim(parent);
    }

    private void exchange(int i, int j) {
        T tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private void sink(int i) {
        int maxIndex = max(i * 2 + 1, i * 2 + 2);
        if (maxIndex < 0) return;
        if (getData(maxIndex).compareTo(getData(i)) > 0) {
            exchange(i, maxIndex);
            sink(maxIndex);
        }
    }

    private int max(int i, int j) {
        if (getData(j) == null && getData(i) != null) return i;
        if (getData(i) == null && getData(j) != null) return j;
        if (getData(i) == null) return -1;
        return getData(i).compareTo(getData(j)) > 0
                ? i : j;
    }

    public T removeMax() {
        if (size > 4 && pointer < size / 4) {
            shrinkStorage();
        }

        T result = data[0];
        exchange(0, pointer);
        // loitering prevention
        data[pointer--] = null;
        sink(0);
        return result;
    }

    public void insert(T item) {
        if (pointer >= size - 1) {
            growStorage();
        }

        data[++pointer] = item;
        swim(pointer);
    }

    private void growStorage() {
        T[] newData = (T[]) new Comparable[size * 2];
        System.arraycopy(data, 0, newData, 0, pointer + 1);
        data = newData;
        size = pointer + 1;
    }

    private void shrinkStorage() {
        T[] newData = (T[]) new Comparable[size / 2];
        System.arraycopy(data, 0, newData, 0, pointer + 1);
        data = newData;
        size = pointer + 1;
    }

    private T getData(int i) {
        return size <= i ? null : data[i];

    }

    public boolean isEmpty() {
        return pointer == -1;
    }

    public int getSize() {
        return size;
    }
}
