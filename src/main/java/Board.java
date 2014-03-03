import java.util.ArrayList;

public class Board {
    private final int[][] b;
    private final int d;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        b = arrayClone(blocks);
        d = b.length;
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
        return d;
    }

    // number of blocks out of place
    public int hamming() {
        int result = 0;
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if (b[i][j] == 0) continue;
                if (b[i][j] != i * d + j + 1) result++;
            }
        }
        return result;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int result = 0;
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if (b[i][j] == 0) continue;
                int val = b[i][j] - 1;
                int iA = val / d;
                int jA = val % d;
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
        if (d > 1) {
            if (b[0][0] != 0) {
                if (b[0][1] != 0) {
                    exchange(b, 0, 0, 0, 1);
                    result = new Board(b);
                    exchange(b, 0, 0, 0, 1);
                } else {
                    exchange(b, 0, 0, 1, 0);
                    result = new Board(b);
                    exchange(b, 0, 0, 1, 0);
                }
            } else {
                exchange(b, 1, 0, 1, 1);
                result = new Board(b);
                exchange(b, 1, 0, 1, 1);
            }
        } else {
            result = new Board(b);
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
        if (y.getClass() != Board.class) return false;
        Board other = (Board) y;
        if (other.dimension() != d) return false;

        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if (other.b[i][j] != this.b[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int i0, j0;
        int pos0 = search0();
        i0 = pos0 / d;
        j0 = pos0 % d;
        ArrayList<Board> result = new ArrayList<Board>();
        Board board;
        if (i0 > 0) {
            board = new Board(this.b);
            this.exchange(board.b, i0, j0, i0 - 1, j0);
            result.add(board);
        }
        if (j0 > 0) {
            board = new Board(this.b);
            this.exchange(board.b, i0, j0, i0, j0 - 1);
            result.add(board);
        }
        if (i0 < d - 1) {
            board = new Board(this.b);
            this.exchange(board.b, i0, j0, i0 + 1, j0);
            result.add(board);
        }
        if (j0 < d - 1) {
            board = new Board(this.b);
            this.exchange(board.b, i0, j0, i0, j0 + 1);
            result.add(board);
        }
        return result;
    }

    private int search0() {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++)
                if (b[i][j] == 0)
                    return i * d + j;
        }
        throw new IllegalArgumentException("Board doesn't have a free block.");
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder result = new StringBuilder();
        int maxLen = Integer.toString(d).length();
        String format = "%" + Integer.toString(maxLen) + "d ";
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if (b[i][j] == 0)
                    result.append("  ");
                else
                    result.append(String.format(format, b[i][j]));
            }
            result.append("\n");
        }
        return result.toString();
    }
}