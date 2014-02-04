import junit.framework.Assert;
import org.testng.annotations.Test;

public class QuickFindUFTest {
    @Test
    public void union_connected() {
        QuickFindUF uf = new QuickFindUF(10);
        Assert.assertFalse(uf.connected(1, 2));
        uf.union(1,2);
        Assert.assertTrue(uf.connected(1,2));
    }

    @Test
    public void union_connected_transitivity() {
        QuickFindUF uf = new QuickFindUF(10);
        Assert.assertFalse(uf.connected(1, 3));
        uf.union(1,2);
        Assert.assertFalse(uf.connected(1, 3));
        uf.union(2,3);
        Assert.assertTrue(uf.connected(1,3));
    }
}
