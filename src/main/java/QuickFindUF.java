
public class QuickFindUF {
    private int[] data;

    public QuickFindUF(int n) {
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
    }

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
