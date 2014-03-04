import com.sun.tools.javac.util.Pair;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class Quiz1_1Test {
    //    (seed = 118295)
    //    Give the id[] array that results from the following sequence of 6 union
    //    operations on a set of 10 items using the quick-find algorithm.
    //
    //        9-2 0-8 0-3 6-1 2-4 6-3
    //
    //    Recall: our quick-find convention for the union operation p-q is to change id[p]
    //    (and perhaps some other entries) but not id[q].
    @Test
    public void Task1() throws NoSuchFieldException, IllegalAccessException {
        final int len = 10;
        QuickFindUF uf = new QuickFindUF(len);

        List<Pair<Integer, Integer>> input = parsePairs("9-2 0-8 0-3 6-1 2-4 6-3");

        for (Pair<Integer, Integer> pair : input) {
            uf.union(pair.fst, pair.snd);
        }

        Class clazz = QuickFindUF.class;
        Field field = clazz.getDeclaredField("data");
        field.setAccessible(true);
        int[] id = (int[]) field.get(uf);

        System.out.println("Task 1:");
        for (int i = 0; i < len; i++) {
            System.out.printf("%d ", id[i]);
        }
        System.out.println();
    }


    //     (seed = 69520)
    //     Give the id[] array that results from the following sequence of 9 union
    //     operations on a set of 10 items using the weighted quick-union algorithm from lecture.
    //
    //      8-7 8-2 3-5 9-6 5-9 0-2 4-7 2-3 8-1
    //
    //     Recall: when joining two trees of equal size, our weighted quick union convention is to
    //     make the root of the second tree point to the root of the first tree. Also, our weighted
    //     quick union algorithm uses union by size (number of nodes), not union by height.
    @Test
    public void Task2() throws NoSuchFieldException, IllegalAccessException {
        final int len = 10;
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(len);

        List<Pair<Integer, Integer>> input = parsePairs("8-7 8-2 3-5 9-6 5-9 0-2 4-7 2-3 8-1");

        for (Pair<Integer, Integer> pair : input) {
            uf.union(pair.fst, pair.snd);
        }

        Class clazz = WeightedQuickUnionUF.class;
        Field field = clazz.getDeclaredField("id");
        field.setAccessible(true);
        int[] id = (int[]) field.get(uf);

        System.out.println("Task 2:");
        for (int i = 0; i < len; i++) {
            System.out.printf("%d ", id[i]);
        }
        System.out.println();
    }

    private List<Pair<Integer, Integer>> parsePairs(String input) {
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<Pair<Integer, Integer>>();

        for (String pair : input.split(" ")) {
            String[] currPair = pair.split("-");
            int a = Integer.parseInt(currPair[0]);
            int b = Integer.parseInt(currPair[1]);
            result.add(new Pair<Integer, Integer>(a, b));
        }

        return result;
    }

}
