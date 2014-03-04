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
}
