import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

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
                {1, 2},
                {3, 0}
        });

        Assert.assertEquals(0, board.hamming());
    }

    @Test
    public void hammingTest_returnsHammingFunction() {
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {6, 4, 5},
                {7, 0, 8},
        });

        Assert.assertEquals(4, board.hamming());
    }

    @Test
    public void manhattanTest_returnsManhattanFunction() {
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {6, 4, 5},
                {7, 0, 8},
        });

        Assert.assertEquals(5, board.manhattan());
    }

    @Test
    public void manhattanTest_returnsManhattanFunction_WithBothRowAndColShift() {
        Board board = new Board(new int[][]{
                {6, 2, 3},
                {1, 4, 5},
                {7, 0, 8},
        });

        Assert.assertEquals(7, board.manhattan());
    }

    @Test
    public void isGoal_returnsTrueIfGoal() {
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0},
        });

        Assert.assertTrue(board.isGoal());
    }

    @Test
    public void isGoal_returnsFalseIfNotGoal() {
        Board board = new Board(new int[][]{
                {1, 2, 5},
                {4, 3, 6},
                {7, 8, 0},
        });

        Assert.assertFalse(board.isGoal());
    }

    @Test
    public void toString_returnsString() {
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0},
        });

        Assert.assertEquals("3\n1 2 3 \n4 5 6 \n7 8 0 \n", board.toString());
    }

    @Test
    public void twin_returnsTwin() {
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0},
        });

        Assert.assertTrue(board.twin().equals(new Board(new int[][]{
                {2, 1, 3},
                {4, 5, 6},
                {7, 8, 0},
        })));
    }

    @Test
    public void twin_returnsTwin_firstItem0() {
        Board board = new Board(new int[][]{
                {0, 2, 3},
                {4, 5, 6},
                {7, 8, 1},
        });

        Assert.assertTrue(board.twin().equals(new Board(new int[][]{
                {0, 2, 3},
                {5, 4, 6},
                {7, 8, 1},
        })));
    }

    @Test
    public void twin_returnsTwin_secondItem0() {
        Board board = new Board(new int[][]{
                {1, 0, 3},
                {4, 5, 6},
                {7, 8, 2},
        });

        Assert.assertTrue(board.twin().equals(new Board(new int[][]{
                {1, 0, 3},
                {5, 4, 6},
                {7, 8, 2},
        })));
    }

    @Test
    public void neighborsTest_returnsNeighbors_center() {
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 5},
        });
        Iterable<Board> neighbors = board.neighbors();
        ArrayList<Board> n = makeCollection(neighbors);

        Assert.assertEquals(4, n.size());

        Assert.assertTrue(n.contains(new Board(new int[][]{
                {1, 0, 3},
                {4, 2, 6},
                {7, 8, 5},
        })));

        Assert.assertTrue(n.contains(new Board(new int[][]{
                {1, 2, 3},
                {4, 8, 6},
                {7, 0, 5},
        })));

        Assert.assertTrue(n.contains(new Board(new int[][]{
                {1, 2, 3},
                {0, 4, 6},
                {7, 8, 5},
        })));

        Assert.assertTrue(n.contains(new Board(new int[][]{
                {1, 2, 3},
                {4, 6, 0},
                {7, 8, 5},
        })));
    }

    @Test
    public void neighborsTest_returnsNeighbors_leftCorner() {
        Board board = new Board(new int[][]{
                {0, 2, 3},
                {4, 5, 6},
                {7, 8, 1},
        });
        Iterable<Board> neighbors = board.neighbors();
        ArrayList<Board> n = makeCollection(neighbors);

        Assert.assertEquals(2, n.size());

        Assert.assertTrue(n.contains(new Board(new int[][]{
                {2, 0, 3},
                {4, 5, 6},
                {7, 8, 1},
        })));

        Assert.assertTrue(n.contains(new Board(new int[][]{
                {4, 2, 3},
                {0, 5, 6},
                {7, 8, 1},
        })));
    }

    @Test
    public void neighborsTest_returnsNeighbors_rightCorner() {
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0},
        });
        Iterable<Board> neighbors = board.neighbors();
        ArrayList<Board> n = makeCollection(neighbors);

        Assert.assertEquals(2, n.size());

        Assert.assertTrue(n.contains(new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 0},
                {7, 8, 6},
        })));

        Assert.assertTrue(n.contains(new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8},
        })));
    }

    public static <E> ArrayList<E> makeCollection(Iterable<E> iter) {
        ArrayList<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}
