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
    public void insert_remove_Grows_and_Shrinks() {
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
    public void invariantsMaintained_returnsTrue_forValidSeq() {
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
    public void invariantsMaintained_returnsFalse_forNotValidSeq() {
        BinaryHeap<Integer> testHeap = new BinaryHeap<Integer>();
        testHeap.setData(new Integer[]{1, 2, 3});
        Assert.assertFalse(testHeap.invariantsMaintained());

        testHeap = new BinaryHeap<Integer>();
        testHeap.setData(new Integer[]{2, 2, 2, 3});
        Assert.assertFalse(testHeap.invariantsMaintained());
    }

    //    (seed = 489602)
    //    Give the sequence of the 13 keys in the array that results after inserting the sequence of 3 keys
    //
    //    51 59 64
    //
    //    into the following maximum-oriented binary heap of size 10:
    //
    //            92 78 85 19 18 11 39 16 10 17
    @Test
    public void quiz_solver_1() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
        heap.setData(new Integer[]{92, 78, 85, 19, 18, 11, 39, 16, 10, 17});
        heap.insert(51);
        heap.insert(59);
        heap.insert(64);

        heap.printData();
    }

    //    (seed = 403083)
    //    Give the sequence of the 7 keys in the array that results after performing 3 successive delete-the-max
    //    operations on the following maximum-oriented binary heap of size 10:
    //
    //            87 76 68 56 49 54 24 39 46 35
    //
    // answer: 56 49 54 46 39 35 24
    @Test
    public void quiz_solver_2() {
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
        heap.setData(new Integer[]{87, 76, 68, 56, 49, 54, 24, 39, 46, 35});
        heap.removeMax();
        heap.removeMax();
        heap.removeMax();
        heap.printData();
    }
}
