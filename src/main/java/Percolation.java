public class Percolation {
    private final int N;
    private final int topSite;
    private final int bottomSite;
    private final boolean[][] sites;
    private final WeightedQuickUnionUF uf;
    // additional uf data to check full sites
    // does not have bottom virtual site
    // and make "upside percolation" impossible.
    private final WeightedQuickUnionUF ufFull;

    public Percolation(int N) {
        this.N = N;

        topSite = N * N;
        bottomSite = topSite + 1;

        // will be automatically initialized with false values.
        // one additional element in both dimentions to use 1-based indexing
        sites = new boolean[N + 1][N + 1];
        // two additional items are those on top and at the bottom
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufFull = new WeightedQuickUnionUF(N * N + 1);

        for (int i = 1; i <= N; i++) {
            // connect top row with additional top element
            unionBoth(topSite, index(1, i));
            // connect bottom row with additional bottom element
            uf.union(bottomSite, index(N, i));
        }
    }

    // i - y axis
    // j - x axis
    public void open(int i, int j) {
        checkIndexes(i, j);
        sites[i][j] = true;
        // on top
        if (i > 0 && sites[i - 1][j]) {
            unionBoth(index(i - 1, j), index(i, j));
        }
        // on the bottom
        if (i < N - 1 && sites[i + 1][j]) {
            unionBoth(index(i + 1, j), index(i, j));
        }
        // on the left
        if (j > 0 && sites[i][j - 1]) {
            unionBoth(index(i, j - 1), index(i, j));
        }
        // on the right
        if (j < N - 1 && sites[i][j + 1]) {
            unionBoth(index(i, j + 1), index(i, j));
        }
    }

    private void unionBoth(int a, int b) {
        uf.union(a, b);
        ufFull.union(a, b);
    }

    private int index(int i, int j) {
        return ((i - 1) * N) + j - 1;
    }

    public boolean isOpen(int i, int j) {
        checkIndexes(i, j);
        return sites[i][j];
    }

    public boolean isFull(int i, int j) {
        checkIndexes(i, j);
        return isOpen(i, j) && ufFull.connected(topSite, index(i, j));
    }

    public boolean percolates() {
        return uf.connected(topSite, bottomSite);
    }

    private void checkIndexes(int i, int j) {
        if (i < 1 || j < 1 || i > N || j > N) {
            throw new IndexOutOfBoundsException();
        }
    }
}