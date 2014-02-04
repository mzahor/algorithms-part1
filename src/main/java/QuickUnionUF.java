// implements QuickFind union-find algorithm
// more info about this algorithm you can find here http://algs4.cs.princeton.edu/15uf/
// this is my own implementation

public class QuickUnionUF {
    private int[] data;

    public QuickUnionUF(int n) {
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
    }

    // can be optimized with tail recursion
    private int getRoot(int a) {
        if (data[a] == a) return a;
        return getRoot(data[a]);
    }

    public void union(int a, int b) {
        int rootA = getRoot(a);
        int rootB = getRoot(b);

        data[rootA] = rootB;
    }

    public boolean connected(int a, int b) {
        return getRoot(a) == getRoot(b);
    }
}
