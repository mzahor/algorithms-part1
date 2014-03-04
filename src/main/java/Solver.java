import java.util.ArrayList;
import java.util.Comparator;

public class Solver {
    private class Search {
        public Search(Board board, int moves, Search prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }

        private final Board board;

        private final int moves;

        private final Search prev;

        public Board getBoard() {
            return this.board;
        }

        public Search getPrev() {
            return this.prev;
        }

        public int getMoves() {
            return this.moves;
        }

        public int getPriority() {
            return moves + board.manhattan();
        }
    }

    private Board[] solution;
    private int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initBoard) {
        Comparator<Search> priorityComparator = new Comparator<Search>() {
            @Override
            public int compare(Search s1, Search s2) {
                if (s1.getPriority() > s2.getPriority()) return 1;
                if (s1.getPriority() < s2.getPriority()) return -1;
                return 0;
            }
        };

        this.moves = -1;
        this.solution = null;

        MinPQ<Search> game = new MinPQ<Search>(priorityComparator);
        MinPQ<Search> gameTwin = new MinPQ<Search>(priorityComparator);

        Search search = new Search(initBoard, 0, null);
        Search searchTwin = new Search(initBoard.twin(), 0, null);

        game.insert(search);
        gameTwin.insert(searchTwin);

        // main solving cycle
        while (!(search.getBoard().isGoal() || searchTwin.getBoard().isGoal())) {
            search = game.delMin();
            searchTwin = gameTwin.delMin();
            for (Board b : search.board.neighbors()) {
                game.insert(new Search(b, search.getMoves() + 1, search));
            }
            for (Board b : searchTwin.board.neighbors()) {
                gameTwin.insert(new Search(b, searchTwin.getMoves() + 1, searchTwin));
            }
        }
        if (search.getBoard().isGoal()){
            this.moves = search.moves;
            solution = new Board[search.getMoves() + 1];
            do {
                solution[search.getMoves()] = search.board;
                search = search.getPrev();
                if (search == null) break;
            } while (true);
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return this.moves > -1;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        ArrayList<Board> result = new ArrayList<Board>(solution.length);
        for (Board b: solution) {
            result.add(b);
        }
        return result;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}