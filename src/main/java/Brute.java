import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        int[] params = In.readInts(args[0]);
        int N = params[0];
        Point[] points = new Point[N];
        Point[] sorted = new Point[4];
        for (int i = 1; i <= N; i++) {
            points[i - 1] = new Point(params[i * 2 - 1], params[i * 2]);
            points[i - 1].draw();
        }

//        for (int i = 0; i < N; i++) {
//            StdOut.println(points[i]);
//        }

        StdDraw.setScale(0, 32768);

        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int l = k + 1; l < N; l++) {
                        if ((points[i].slopeTo(points[j])
                                == points[i].slopeTo(points[k])) &&
                                (points[i].slopeTo(points[k])
                                        == points[i].slopeTo(points[l])
                                )) {

                            sorted[0] = points[i];
                            sorted[1] = points[j];
                            sorted[2] = points[k];
                            sorted[3] = points[l];

                            Arrays.sort(sorted);

                            StdOut.println(sorted[0] + " -> " + sorted[1] + " -> " + sorted[2] + " -> " + sorted[3]);

                            points[i].drawTo(points[l]);
                        }
                    }
                }
            }
        }
    }
}