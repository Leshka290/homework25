package test;

import org.junit.Test;
import stringAlgorithm.StringList;
import stringAlgorithm.StringListImpl;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class StringListImplTest {

    private final String[] array = new String[]{"one", "two", "free"};
    private final String[] arrayCopy = new String[]{"one", "two", "free", "four"};
    private final StringList stringList = new StringListImpl(array);
    private final static int INDEX = 3;
    private final static int INDEXSET = 2;
    private final static int HIGHINDEX = 6;

    @Test
    public void shouldAddTest() {
        String item = "four";

        assertEquals(arrayCopy[INDEX], stringList.add(item));
    }

    @Test
    public void shouldAddIndexTest() {
        int n = array.length + 1;
        String item = "four";
        String[] newArray = new String[n];

        System.arraycopy(array, 0, newArray, 0, INDEX);
        System.arraycopy(array, INDEX - 1, newArray, INDEX, newArray.length - INDEX);
        newArray[INDEX] = stringList.add(INDEX, item);

        assertArrayEquals(arrayCopy, newArray);
        assertEquals(arrayCopy[INDEX], stringList.add(INDEX, item));
        assertThrows(IndexOutOfBoundsException.class, () -> stringList.add(HIGHINDEX, ""));
    }

    @Test
    public void shouldSetTest() {
        String item = "free";

        assertThrows(IndexOutOfBoundsException.class, () -> stringList.set(HIGHINDEX, ""));
        assertEquals(arrayCopy[INDEXSET], stringList.set(INDEXSET, item));
    }

    @Test
    public void shouldRemoveTest() {
        String item = "free";

        assertEquals(array[INDEXSET], stringList.remove(item));
        assertThrows(NoSuchElementException.class, () -> stringList.remove(""));
    }

    @Test
    public void shouldRemoveIndexTest() {
        assertEquals(array[INDEXSET], stringList.remove(INDEXSET));
        assertThrows(NoSuchElementException.class, () -> stringList.remove(""));
    }

    @Test
    public void shouldContainsTest() {
        String item = "free";

        assertTrue(stringList.contains(item));
    }

    @Test
    public void shouldIndexOfTest() {
        String item = "free";

        assertEquals(INDEXSET, stringList.indexOf(item));
    }

    @Test
    public void shouldLastIndexOfTest() {
        String item = "free";

        assertEquals(INDEXSET, stringList.lastIndexOf(item));
    }

    @Test
    public void shouldGetStringTest() {
        String item = "free";

        assertEquals(item, stringList.get(INDEXSET));
        assertThrows(IndexOutOfBoundsException.class, () -> stringList.get(HIGHINDEX));
    }

    @Test
    public void shouldEqualsStringTest() {
        StringList stringListCopy = new StringListImpl(array);

        assertThrows(NullPointerException.class, () -> stringList.equals(null));
        assertTrue(stringList.equals(stringListCopy));
    }

    @Test
    public void shouldSizeTest() {
        assertEquals(array.length, stringList.size());
    }

    @Test
    public void shouldIsEmptyTest() {
        assertFalse(stringList.isEmpty());
    }

    @Test
    public void shouldClearTest() {
        StringList stringListCopy = new StringListImpl(null);

        stringList.clear();

        assertThrows(NullPointerException.class, () -> stringList.equals(stringListCopy));
    }

    @Test
    public void shouldToArrayTest() {
        String[] newArray = new String[array.length];

        System.arraycopy(array, 0, newArray, 0, array.length);

        assertArrayEquals(newArray, stringList.toArray());
    }
}
