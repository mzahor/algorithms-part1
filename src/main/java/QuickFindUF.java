// This class implements Union-Find algorithm.
public class QuickFindUF {
    private int[] data;

    public QuickFindUF(int n) {
        data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
    }

    public void union(int a, int b) {
        if (data[a] == data[b]) return;

        int prev = data[a];
        int curr = data[b];

        for(int i = 0; i < data.length; i++) {
            if (data[i] == prev) {
                data[i] = curr;
            }
        }
    }

    public boolean connected(int a, int b) {
        return data[a] == data[b];
    }
}
