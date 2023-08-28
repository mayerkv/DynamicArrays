import model.*;

import java.util.Date;

public class Program {

    public static void main(String[] args) {
        for (int n = 100; n <= 100_000; n *= 10) {
            System.out.printf("Tests for %d elements\n", n);

            IArray[] arrays = {
                new SingleArray(),
                new VectorArray(),
                new FactorArray(),
                new ArrayList(),
                new LinkedList(),
            };

            for (IArray array : arrays) {
                testAddInTail(array, n);
                testAddInPosition(array, n);
                testRemoveFromPosition(array, n);
            }

            System.out.println("--------------------\n");
        }
    }

    private static void testAddInTail(IArray data, int total) {
        long start = System.currentTimeMillis();

        for (int j = 0; j < total; j++)
            data.add(new Date());

        System.out.println(data + " testAddInTail: " +
            (System.currentTimeMillis() - start));
    }

    private static void testAddInPosition(IArray data, int total) {
        long start = System.currentTimeMillis();

        for (int j = 0; j < total; j++) {
            int a = total - j;
            data.add(a, j);
        }

        System.out.println(data + " testAddInPosition: " +
            (System.currentTimeMillis() - start));
    }

    private static void testRemoveFromPosition(IArray data, int total) {
        long start = System.currentTimeMillis();

        for (int j = 1; j <= total; j++) {
            data.remove(total - j);
        }

        System.out.println(data + " testRemoveFromPosition: " +
            (System.currentTimeMillis() - start));
    }
}
