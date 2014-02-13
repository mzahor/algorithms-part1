public class Subset {
    public static void main(String[] args) {
        String currentItem;
        RandomizedQueue<String> queue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            queue.enqueue(s);
        }

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            StdOut.println(queue.dequeue());
        }

//        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
//        for (int i = 1; i < 10; i ++)
//        {
//            rq.enqueue(i);
//        }
//
//        while (!rq.isEmpty())
//        {
//            StdOut.println(rq.dequeue());
//        }
    }
}