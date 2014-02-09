// implements QuickFind union-find algorithm
// more info about this algorithm you can find here http://algs4.cs.princeton.edu/15uf/
// this is my own implementation

public class QuickUnionUF {
    private int[] data;
    private int[] weight;

    public QuickUnionUF(int n) {
        data = new int[n];
        weight = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = i;
            weight[i] = 1;
        }
    }

    // can be optimized with tail recursion
    private int getRoot(int a) {
        if (data[a] == a) return a;
        // this line flattens the tree
        data[a] = data[data[a]];
        return getRoot(data[a]);
    }

    public void union(int a, int b) {
        int rootA = getRoot(a);
        int rootB = getRoot(b);

        if (rootA == rootB) {
            return;
        }

        if (weight[rootA] < weight[rootB]) {
            data[rootA] = rootB;
            weight[rootB] += weight[rootA];
        }
        else {
            data[rootB] = rootA;
            weight[rootA] += weight[rootB];
        }
    }

    public boolean connected(int a, int b) {
        return getRoot(a) == getRoot(b);
    }
}
