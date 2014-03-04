import junit.framework.Assert;
import org.testng.annotations.Test;

public class QuickUnionUFTest {
    @Test
    public void union_connected() {
        QuickUnionUF uf = new QuickUnionUF(10);
        Assert.assertFalse(uf.connected(1, 2));
        uf.union(1, 2);
        Assert.assertTrue(uf.connected(1, 2));
    }

    @Test
    public void union_connected_transitivity() {
        QuickUnionUF uf = new QuickUnionUF(10);
        Assert.assertFalse(uf.connected(1, 3));
        uf.union(1, 2);
        Assert.assertFalse(uf.connected(1, 3));
        uf.union(2, 3);
        Assert.assertTrue(uf.connected(1, 3));
    }
}
