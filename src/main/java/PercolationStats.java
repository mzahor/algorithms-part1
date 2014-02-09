public class PercolationStats {
    private double[] results;
    private int N;
    private int T;
    private double mean;
    private double stddev;
    private double confLo;
    private double confHi;


    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        results = new double[this.T];
        for (int i = 0; i < this.T; i++) {
            results[i] = getPercolation();
        }

        mean = StdStats.mean(results);
        stddev = StdStats.stddev(results);

        confLo = mean - (1.96 * stddev / Math.sqrt(T));
        confHi = mean + (1.96 * stddev / Math.sqrt(T));
    }

    // test client, described below
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(N, T);

        StdOut.printf("%-30s = %10.10f\n", "mean", stats.mean());
        StdOut.printf("%-30s = %10.10f\n", "stddev", stats.stddev());
        StdOut.printf("%-30s = %10.10f, %10.10f\n", "95% confidence interval", stats.confidenceLo(), stats.confidenceHi());
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        //return StdStats.
        return confLo;
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return confHi;
    }

    private double getPercolation() {
        Percolation percolation = new Percolation(N);
        int rndI, rndJ;
        double openedSitesCount = 0;
        while (!percolation.percolates()) {
            rndI = StdRandom.uniform(N) + 1;
            rndJ = StdRandom.uniform(N) + 1;

            if (!percolation.isOpen(rndI, rndJ)) {
                percolation.open(rndI, rndJ);
                openedSitesCount++;
            }
        }
        return openedSitesCount / (N * N);
    }
}