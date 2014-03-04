import junit.framework.Assert;
import org.testng.annotations.Test;

public class SolverTest {
    @Test
    public void solverTest_solvesGoalTask() {
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0},
        });
        Solver solver = new Solver(board);
        Assert.assertTrue(solver.isSolvable());
        Assert.assertEquals(0, solver.moves());
    }

    @Test
    public void solverTest_solvesPrimitiveTask() {
        Board board = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8},
        });
        Solver solver = new Solver(board);
        Assert.assertTrue(solver.isSolvable());
        Assert.assertEquals(1, solver.moves());
    }

    @Test
    public void solverTest_unsolvableTask() {
        Board board = new Board(new int[][]{
                {1, 3},
                {0, 2},
        });
        Solver solver = new Solver(board);

        Assert.assertFalse(solver.isSolvable());
        Assert.assertEquals(-1, solver.moves());
        Assert.assertEquals(null, solver.solution());
    }

    @Test
    public void solverTest_complexTask() {
        Board board = new Board(new int[][]{
                {6, 13, 0, 22, 72, 18, 50, 55, 21},
                {61, 73, 32, 43, 57, 67, 4, 2, 48},
                {74, 41, 27, 60, 66, 44, 23, 24, 14},
                {56, 75, 30, 46, 47, 19, 1, 68, 35},
                {64, 7, 11, 29, 25, 10, 59, 77, 78},
                {8, 20, 5, 28, 51, 69, 34, 33, 45},
                {3, 16, 39, 53, 12, 62, 17, 26, 63},
                {15, 80, 38, 79, 54, 76, 70, 37, 42},
                {36, 40, 31, 49, 58, 65, 52, 71, 9}
        });
        Solver solver = new Solver(board);

        Assert.assertTrue(solver.isSolvable());
    }
}
