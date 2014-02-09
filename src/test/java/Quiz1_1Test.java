import org.testng.annotations.Test;

import java.lang.reflect.Field;


public class Quiz1_1Test {
    /**
     (seed = 69520)
     Give the id[] array that results from the following sequence of 9 union
     operations on a set of 10 items using the weighted quick-union algorithm from lecture.

     6-0 7-2 9-3 5-7 7-4 6-9 2-1 2-0 0-8

     Recall: when joining two trees of equal size, our weighted quick union convention is to
     make the root of the second tree point to the root of the first tree. Also, our weighted
     quick union algorithm uses union by size (number of nodes), not union by height.
     */
    @Test
    public void Task2() throws NoSuchFieldException, IllegalAccessException {
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);

        uf.union(6, 0);
        uf.union(7, 2);
        uf.union(9, 3);
        uf.union(5, 7);
        uf.union(7, 4);
        uf.union(6, 9);
        uf.union(2, 1);
        uf.union(2, 0);
        uf.union(0, 8);

        Class clazz = WeightedQuickUnionUF.class;
        Field field =  clazz.getDeclaredField("id");
        field.setAccessible(true);
        int[] id = (int[]) field.get(uf);

        for (int i = 0 ; i < 10; i ++){
            System.out.printf("%d ", id[i]);
        }
        System.out.println();
    }

}
