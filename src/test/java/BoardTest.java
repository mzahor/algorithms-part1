import junit.framework.Assert;
import org.testng.annotations.Test;

public class BoardTest {
    @Test
    public void ctorTest() {
        Board board = new Board(new int[][]{
                {3, 2, 1},
                {6, 5, 4},
                {8, 7, 0}
        });
        Assert.assertNotNull(board);
    }

    @Test
    public void equalsTest() {
        Board board1 = new Board(new int[][]{
                {3, 2, 1},
                {6, 5, 4},
                {8, 7, 0}
        });

        Board board2 = new Board(new int[][]{
                {3, 2, 1},
                {6, 5, 4},
                {8, 7, 0}
        });
        Assert.assertTrue(board1.equals(board2));
        Assert.assertTrue(board1.equals(board1));
        Assert.assertTrue(board2.equals(board1));
    }

    @Test
    public void equalsTest_WhenNotEquals() {
        Board board1 = new Board(new int[][]{
                {3, 2, 1},
                {6, 5, 4},
                {8, 7, 0}
        });

        Board board2 = new Board(new int[][]{
                {3, 1, 2},
                {6, 5, 4},
                {8, 7, 0}
        });
        Assert.assertFalse(board1.equals(board2));
    }

    @Test
    public void dimensionTest() {
        Board board = new Board(new int[][]{
                {3, 2, 1},
                {6, 5, 4},
                {8, 7, 0}
        });

        Assert.assertEquals(3, board.dimension());
    }

    @Test
    public void hammingTest_0result() {
        Board board = new Board(new int[][]{
                {3, 2, 1},
                {6, 5, 4},
                {8, 7, 0}
        });

        Assert.assertEquals(3, board.dimension());
    }
}
