import java.util.ArrayList;

public class Board {
    private final int[][] board;
    private final int dimension;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        board = arrayClone(blocks);
        dimension = board.length;
    }

    private int[][] arrayClone(int[][] source) {
        int[][] result = new int[source.length][];
        for (int i = 0; i < source.length; i++) {
            result[i] = new int[source[i].length];
            System.arraycopy(source[i], 0, result[i], 0, source[i].length);
        }
        return result;
    }

    // board dimension N
    public int dimension() {
        return dimension;
    }

    // number of blocks out of place
    public int hamming() {
        int result = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == 0) continue;
                if (board[i][j] != i * dimension + j + 1) result++;
            }
        }
        return result;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int result = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == 0) continue;
                int val = board[i][j] - 1;
                int iA = val / dimension;
                int jA = val % dimension;
                result += Math.abs(iA - i) + Math.abs(jA - j);
            }
        }
        return result;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        Board result;
        if (dimension > 1) {
            if (board[0][0] != 0 && board[0][1] != 0) {
                result = new Board(board);
                exchange(result.board, 0, 0, 0, 1);
            } else {
                result = new Board(board);
                exchange(result.board, 1, 0, 1, 1);
            }
        } else {
            result = new Board(board);
        }

        return result;
    }

    private void exchange(int[][] data, int i1, int j1, int i2, int j2) {
        int tmp = data[i1][j1];
        data[i1][j1] = data[i2][j2];
        data[i2][j2] = tmp;
    }

    private void exchange(int[] data, int a, int b) {
        int tmp = data[a];
        data[a] = data[b];
        data[b] = tmp;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y == this) return true;
        if (!y.getClass().isAssignableFrom(Board.class)) return false;
        Board other = (Board) y;
        if (other.dimension() != dimension) return false;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (other.board[i][j] != this.board[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int i0, j0;
        int pos0 = search0();
        i0 = pos0 / dimension;
        j0 = pos0 % dimension;
        ArrayList<Board> result = new ArrayList<Board>();
        Board board;
        if (i0 > 0) {
            board = new Board(this.board);
            this.exchange(board.board, i0, j0, i0 - 1, j0);
            result.add(board);
        }
        if (j0 > 0) {
            board = new Board(this.board);
            this.exchange(board.board, i0, j0, i0, j0 - 1);
            result.add(board);

        }
        if (i0 < dimension - 1) {
            board = new Board(this.board);
            this.exchange(board.board, i0, j0, i0 + 1, j0);
            result.add(board);
        }
        if (j0 < dimension - 1) {
            board = new Board(this.board);
            this.exchange(board.board, i0, j0, i0, j0 + 1);
            result.add(board);
        }
        return result;
    }

    private int search0() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++)
                if (board[i][j] == 0)
                    return i * dimension + j;
        }
        throw new IllegalArgumentException("Board doesn't have a free block.");
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(Integer.toString(dimension) + "\n");
        int maxLen = Integer.toString(dimension * dimension - 1).length();
        String format = "%" + Integer.toString(maxLen) + "d ";
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result.append(String.format(format, board[i][j]));
            }
            result.append("\n");
        }
        return result.toString();
    }

    private void log(String logString){
        System.out.println(logString);
    }
}