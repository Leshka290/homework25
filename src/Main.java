import integerAlgorithm.IntegerList;
import integerAlgorithm.IntegerListImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            int el = new Random().nextInt(100);
            list.add(el);
        }
        IntegerList integerList = new IntegerListImpl(list.toArray(new Integer[0]));
        IntegerList integerList2 = new IntegerListImpl(list.toArray(new Integer[0]));
        IntegerList integerList3 = new IntegerListImpl(list.toArray(new Integer[0]));

//         самая быстрая сортировка
        long start = System.currentTimeMillis();
        integerList.quickSort(0, integerList.toArray().length - 1);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        integerList2.bubbleSort();
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        integerList3.selectionSort();
        System.out.println(System.currentTimeMillis() - start);

        System.out.println(integerList.add(2, 100000));
        System.out.println(integerList.add(3, 100000));
        System.out.println(integerList.add(100000));
        System.out.println(integerList.add(100000));
        System.out.println(Arrays.toString(integerList.toArray()));
        System.out.println(integerList.size());
        System.out.println(integerList.toArray().length);
        // System.out.println(Arrays.toString(integerList.toArray()));
    }
}