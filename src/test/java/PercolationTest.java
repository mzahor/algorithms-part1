import junit.framework.Assert;
import org.testng.annotations.Test;

public class PercolationTest {
    @Test
    public void testOpen_isOpen() throws Exception {
        Percolation perc = new Percolation(2);
        Assert.assertFalse(perc.isOpen(1, 1));
        perc.open(1, 1);
        Assert.assertTrue(perc.isOpen(1, 1));
    }

    @Test
    public void testIsFull() throws Exception {
        Percolation perc = new Percolation(2);
        Assert.assertFalse(perc.isFull(1, 1));
        perc.open(1, 1);
        Assert.assertTrue(perc.isFull(1, 1));
        perc.open(2, 1);
        Assert.assertTrue(perc.isFull(2, 1));
    }

    @Test
    public void testPercolates() throws Exception {
        Percolation perc = new Percolation(2);
        Assert.assertFalse(perc.percolates());
        perc.open(1, 1);
        Assert.assertFalse(perc.percolates());
        perc.open(2, 1);
        Assert.assertTrue(perc.percolates());
    }

    @Test
    public void testPercolates_sigleSite() {
        Percolation perc = new Percolation(1);
        Assert.assertFalse(perc.percolates());
        perc.open(1, 1);
        Assert.assertTrue(perc.percolates());
    }
}
