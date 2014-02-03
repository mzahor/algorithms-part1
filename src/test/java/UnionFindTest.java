import junit.framework.Assert;
import org.testng.annotations.Test;

public class UnionFindTest {
    @Test
    public void union_connected() {
        UnionFind uf = new UnionFind(10);
        Assert.assertFalse(uf.connected(1,2));
        uf.union(1,2);
        Assert.assertTrue(uf.connected(1,2));
    }
}
