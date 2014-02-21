import java.util.*;

public class Fast {
    private static int rec = 0;

    public static void main(String[] args) {
        int[] params = In.readInts(args[0]);
        int N = params[0];
        EPoint[] points = new EPoint[N];
        EPoint[] sortedPoints = new EPoint[N];

        for (int i = 1; i <= N; i++) {
            points[i - 1] = new EPoint(params[i * 2 - 1], params[i * 2]);
            points[i - 1].draw();
            sortedPoints[i - 1] = points[i - 1];
        }
        StdDraw.setScale(0, 32768);

        double slope = points[0].slopeTo(points[0]);

        TreeSet<Line> results = new TreeSet<Line>();
        ArrayList<EPoint> basePoint = new ArrayList<EPoint>();

        for (int i = 0; i < N; i++) {
            EPoint p = points[i];
            qsort(sortedPoints, 0, N - 1, p.SLOPE_ORDER);

            ArrayList<EPoint> currentResult = new ArrayList<EPoint>();
            currentResult.add(p);
            double prevSlope = p.slopeTo(sortedPoints[0]);

            for (int j = 1; j <= N; j++) {
                if (j < N && prevSlope == p.slopeTo(sortedPoints[j])) {
                    currentResult.add(sortedPoints[j]);
                } else {
                    if (currentResult.size() >= 4) {
                        basePoint.add(p);
                        Line line = new Line(currentResult);
                        Line ceiling = results.ceiling(line);
                        if (ceiling == null || ceiling.compareTo(line) != 0) {
                            results.add(line);
                            line.print();
                            line.draw();
                        }
                    }

                    if (j == N) break;

                    prevSlope = p.slopeTo(sortedPoints[j]);
                    currentResult = new ArrayList<EPoint>();
                    currentResult.add(p);
                    currentResult.add(sortedPoints[j]);
                }
            }
        }
    }

//    private static boolean contains(TreeSet<Line> list, Line line) {
//        for (Line l : list) {
//            if (l.compareTo(line) == 0) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    private static <T> void qsort(T[] arr, int lo, int hi, Comparator<T> cmp) {
        if (lo >= hi) return;

        int lt = lo;
        int i = lt + 1;
        int gt = hi;

        T pivot = arr[lt];

        while (i <= gt) {
            int comp = cmp.compare(pivot, arr[i]);
            if (comp > 0)
                swap(arr, lt++, i++);
            else if (comp < 0)
                swap(arr, i, gt--);
            else i++;
        }

        qsort(arr, lo, lt - 1, cmp);
        qsort(arr, gt + 1, hi, cmp);
    }

    private static <T> void printArray(T[] arr, int start, int count) {
        for (int i = start; i < count; i++) {
            StdOut.print(arr[i] + " ");
        }
        StdOut.println();
    }

    private static <T> void swap(T[] arr, int i1, int i2) {
        T tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    private static Comparator<Integer> intComparator() {
        return new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return (o1 > o2 ? 1 : (o1 == o2 ? 0 : -1));
            }
        };
    }

    private static class EPoint extends Point {
        private int x;
        private int y;

        public EPoint(int x, int y) {
            super(x, y);
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private static class Line implements Comparable<Line> {
        private List<EPoint> points;

        public Line(List<EPoint> points) {
            this.points = points;
            Collections.sort(this.points);
        }

        private int getX1() {
            return points.get(0).getX();
        }

        private int getY1() {
            return points.get(0).getY();
        }

        private int getX2() {
            return points.get(1).getX();
        }

        private int getY2() {
            return points.get(1).getY();
        }

        private double getA() {
            if (getX1() - getX2() == 0) return Double.POSITIVE_INFINITY;
            return (getY1() - getY2()) / (getX1() - getX2());
        }

        private double getB() {
            return getA() * getX1() - (double) getY1();
        }

        public int compareTo(Line other) {
            if (getA() == other.getA() && getB() == other.getB())
                return 0;

            if (getA() > other.getA())
                return 1;
            if (getA() < other.getA())
                return -1;

            return getB() > other.getB() ? 1 : -1;
        }

        public void print() {
            StdOut.print(points.get(0));
            for (int i = 1; i < points.size(); i++) {
                StdOut.print(" -> " + points.get(i));
            }
            StdOut.println();
        }

        public void draw() {
            for (int i = 0; i < points.size() - 1; i++) {
                points.get(i).drawTo(points.get(i + 1));
            }
        }
    }
}