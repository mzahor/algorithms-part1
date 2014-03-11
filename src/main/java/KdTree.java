import java.util.ArrayList;

public class KdTree {
    private int size;

    private class Node {
        public Point2D point;
        public Node left;
        public Node right;
        public boolean red;
        public boolean vertical;
    }

    private Node root;

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        root = insert(p, root, true);
    }

    private Node insert(Point2D p, Node node, boolean vertical) {
        if (node == null) {
            node = new Node();
            node.point = p;
            node.vertical = vertical;
            return node;
        }
        if ((node.vertical && node.point.x() > p.x())
                || (!node.vertical && node.point.y() > p.y()))
            node.left = insert(p, node.left, !vertical);
        else
            node.right = insert(p, node.right, !vertical);
        return node;
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return contains(p, root);
    }

    private boolean contains(Point2D p, Node node) {
        if (node == null) return false;
        if (node.point.compareTo(p) == 0) return true;
        if ((node.vertical && node.point.x() > p.x())
                || (!node.vertical && node.point.y() > p.y()))
            return contains(p, node.left);
        else
            return contains(p, node.right);
    }

    // draw all of the points to standard draw
    public void draw() {

    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return range(rect, root, new ArrayList<Point2D>());
    }

    private boolean inside(RectHV rect, Point2D p) {
        return p.x() >= rect.xmin()
                && p.x() <= rect.xmax()
                && p.y() >= rect.ymin()
                && p.y() <= rect.ymax();
    }

    private Iterable<Point2D> range(RectHV rect, Node node, ArrayList<Point2D> result) {
        if (node == null) return result;
        if (inside(rect, node.point)) result.add(node.point);

        if ((node.vertical && rect.xmin() < node.point.x())
                || (!node.vertical && rect.ymin() < node.point.y()))
            range(rect, node.left, result);

        if ((node.vertical && rect.xmax() > node.point.x())
                || (!node.vertical && rect.ymax() > node.point.y()))
            range(rect, node.right, result);

        return result;
    }

    private Node nearest(Point2D p, Node node) {
        if (node == null) return null;
        if (node.left == null && node.right == null) return node;
        double parentDist = node.point.distanceTo(p);
        if ((node.left != null && node.left.point.distanceTo(p) < parentDist) || node.right == null) {
            return nearest(p, node.left);
        }
        if ((node.right != null && node.right.point.distanceTo(p) < parentDist) || node.left == null) {
            return nearest(p, node.right);
        }
        return min(p, nearest(p, node.left), nearest(p, node.right));
    }

    private Node min(Point2D p, Node n1, Node n2) {
        if (n1 == null) {
            return n2;
        }
        if (n2 == null) {
            return n1;
        }
        return p.distanceTo(n1.point) < p.distanceTo(n2.point)
                ? n1 : n2;
    }
}