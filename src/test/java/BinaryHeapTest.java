import org.testng.Assert;
import org.testng.annotations.Test;

public class BinaryHeapTest {
    @Test
    public void isEmpty_WhenEmpty() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        Assert.assertTrue(testHeap.isEmpty());
    }

    @Test
    public void isEmpty_WhenNotEmpty() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        testHeap.insert(10);
        Assert.assertFalse(testHeap.isEmpty());
    }

    @Test
    public void insert_OneItem() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        testHeap.insert(10);
        int result = testHeap.getMax();
        Assert.assertEquals(result, 10);
    }

    @Test
    public void insert_TwoItems() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        testHeap.insert(10);
        testHeap.insert(20);
        int result = testHeap.getMax();
        Assert.assertEquals(result, 20);
    }

    @Test
    public void insert_TwoItems_Reverse() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        testHeap.insert(20);
        testHeap.insert(10);
        int result = testHeap.getMax();
        Assert.assertEquals(result, 20);
    }

    @Test
    public void removeMax_OneItem() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        testHeap.insert(20);
        int result = testHeap.removeMax();
        Assert.assertEquals(result, 20);
    }

    @Test
    public void removeMax_TwoItems() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        testHeap.insert(20);
        testHeap.insert(30);
        testHeap.insert(40);
        testHeap.insert(40);
        testHeap.insert(70);
        int result = testHeap.removeMax();
        Assert.assertEquals(result, 70);
    }

    @Test
    void insert_remove_Grows_and_Shrinks() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        for (int i = 1; i < 100; i++) {
            testHeap.insert(i);
        }
        for (int i = 99; i > 0; i--) {
            int result = testHeap.removeMax();
            Assert.assertEquals(result, i);
        }
    }

    @Test
    void invariantsMaintained_returnsTrue_forValidSeq() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        for (int i = 1; i < 100; i++) {
            testHeap.insert(i);
            Assert.assertTrue(testHeap.invariantsMaintained());
        }
        for (int i = 99; i > 0; i--) {
            testHeap.removeMax();
            Assert.assertTrue(testHeap.invariantsMaintained());
        }

        testHeap = new BinaryHeap<Integer>();
        testHeap.setData(new Integer[]{2, 2, 2});
        Assert.assertTrue(testHeap.invariantsMaintained());
    }

    @Test
    void invariantsMaintained_returnsFalse_forNotValidSeq() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        testHeap.setData(new Integer[]{1, 2, 3});
        Assert.assertFalse(testHeap.invariantsMaintained());

        testHeap = new BinaryHeap<Integer>();
        testHeap.setData(new Integer[]{2, 2, 2, 3});
        Assert.assertFalse(testHeap.invariantsMaintained());
    }
}
