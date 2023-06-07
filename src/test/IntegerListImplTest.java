package test;

import integerAlgorithm.IntegerList;
import integerAlgorithm.IntegerListImpl;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class IntegerListImplTest {

    private final Integer[] array = new Integer[]{1, 2, 3};
    private final Integer[] arrayCopy = new Integer[]{1, 2, 3, 4};
    private final IntegerList integerList = new IntegerListImpl(array);
    private final static int INDEX = 3;
    private final static int INDEXSET = 2;
    private final static int HIGHINDEX = 6;

    @Test
    public void shouldAddTest() {
        Integer item = 4;

        assertEquals(arrayCopy[INDEX], integerList.add(item));
    }

    @Test
    public void shouldAddIndexTest() {
        int n = array.length + 1;
        Integer item = 4;
        Integer[] newArray = new Integer[n];

        System.arraycopy(array, 0, newArray, 0, INDEX);
        System.arraycopy(array, INDEX - 1, newArray, INDEX, newArray.length - INDEX);
        newArray[INDEX] = integerList.add(INDEX, item);

        assertArrayEquals(arrayCopy, newArray);
        assertEquals(arrayCopy[INDEX], integerList.add(INDEX, item));
        assertThrows(IndexOutOfBoundsException.class, () -> integerList.add(HIGHINDEX, 5));
    }

    @Test
    public void shouldSetTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> integerList.set(HIGHINDEX, 5));
        assertEquals(arrayCopy[INDEXSET], integerList.set(INDEXSET, INDEX));
    }

    @Test
    public void shouldRemoveTest() {
        Integer item = 3;

        assertEquals(array[INDEXSET], integerList.remove(item));
        assertThrows(NoSuchElementException.class, () -> integerList.remove(item));
    }

    @Test
    public void shouldRemoveIndexTest() {
        Integer item = 3;

        assertEquals(array[INDEXSET], integerList.remove(INDEXSET));
        assertThrows(NoSuchElementException.class, () -> integerList.remove(item));
    }

    @Test
    public void shouldContainsTest() {
        Integer item = 3;

        assertTrue(integerList.contains(item));
    }

    @Test
    public void shouldIndexOfTest() {
        Integer item = 3;

        assertEquals(INDEXSET, integerList.indexOf(item));
    }

    @Test
    public void shouldLastIndexOfTest() {
        Integer item = 3;

        assertEquals(INDEXSET, integerList.lastIndexOf(item));
    }

    @Test
    public void shouldGetStringTest() {
        Integer item = 3;

        assertEquals(item, integerList.get(INDEXSET));
        assertThrows(IndexOutOfBoundsException.class, () -> integerList.get(HIGHINDEX));
    }

    @Test
    public void shouldEqualsStringTest() {
        IntegerList integerListCopy = new IntegerListImpl(array);

        assertThrows(NullPointerException.class, () -> integerList.equals(null));
        assertTrue(integerList.equals(integerListCopy));
    }

    @Test
    public void shouldSizeTest() {
        assertEquals(array.length, integerList.size());
    }

    @Test
    public void shouldIsEmptyTest() {
        assertFalse(integerList.isEmpty());
    }

    @Test
    public void shouldClearTest() {
        IntegerList integerListCopy = new IntegerListImpl(null);

        integerList.clear();

        assertThrows(NullPointerException.class, () -> integerList.equals(integerListCopy));
    }

    @Test
    public void shouldToArrayTest() {
        Integer[] newArray = new Integer[array.length];

        System.arraycopy(array, 0, newArray, 0, array.length);

        assertArrayEquals(newArray, integerList.toArray());
    }

    @Test
    public void shouldQuickSortTest() {
        IntegerList integerListSorted = new IntegerListImpl(new Integer[]{2, 1, 3});
        integerListSorted.quickSort(0, 2);
        assertArrayEquals(integerList.toArray(), integerListSorted.toArray());
    }

    @Test
    public void shouldSelectionSortTest() {
        IntegerList integerListSorted = new IntegerListImpl(new Integer[]{2, 1, 3});
        integerListSorted.selectionSort();
        assertArrayEquals(integerList.toArray(), integerListSorted.toArray());
    }

    @Test
    public void shouldBubbleSortTest() {
        IntegerList integerListSorted = new IntegerListImpl(new Integer[]{2, 1, 3});
        integerListSorted.bubbleSort();
        assertArrayEquals(integerList.toArray(), integerListSorted.toArray());
    }
}
