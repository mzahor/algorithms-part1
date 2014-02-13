import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int current;
    private int arrSize;
    private int minArrSize;
    private Item[] data;

    public RandomizedQueue() {
        minArrSize = 4;
        arrSize = minArrSize;
        data = (Item[]) new Object[arrSize];
        current = -1;
    }

    public boolean isEmpty() {
        return size() < 1;
    }

    public int size() {
        return current + 1;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        checkArraySize(current + 1);
        data[++current] = item;
    }

    private void checkArraySize(int nextIndex) {
        if (nextIndex >= arrSize) {
            resizeDataArray(arrSize * 2);
        } else if (nextIndex < arrSize / 2 && arrSize > minArrSize) {
            resizeDataArray(arrSize / 2);
        }
    }

    private void resizeDataArray(int newSize) {
        arrSize = newSize;
        Item[] newArr = (Item[]) new Object[arrSize];
        System.arraycopy(data, 0, newArr, 0, size());
        data = newArr;
    }

    private void randomSwap() {
        if (size() < 2) return;
        int randomInd = StdRandom.uniform(1, size() + 1) - 1;
        Item tmp = data[current];
        data[current] = data[randomInd];
        data[randomInd] = tmp;
    }

    public Item dequeue() {
        if (size() < 1) throw new NoSuchElementException();
        checkArraySize(current);
        randomSwap();
        return data[current--];
    }

    public Item sample() {
        if (current < 0) throw new NoSuchElementException();
        randomSwap();
        return data[current];
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int iterArrSize = size();
            private Item[] iterData = (Item[]) new Object[iterArrSize];
            private int iterCurrent = 0;

            {
                System.arraycopy(data, 0, iterData, 0, size());
            }

            @Override
            public boolean hasNext() {
                return iterCurrent < iterArrSize;
            }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                iterRandomSwap();
                return iterData[iterCurrent++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            private void iterRandomSwap() {
                int randomInd = StdRandom.uniform(iterCurrent + 1, iterArrSize + 1) - 1;
                Item tmp = iterData[iterCurrent];
                iterData[iterCurrent] = iterData[randomInd];
                iterData[randomInd] = tmp;
            }
        };
    }
}