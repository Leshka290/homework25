package stringAlgorithm;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class StringListImpl implements StringList {

    private String[] array;

    public StringListImpl(String[] array) {
        this.array = array;
    }

    @Override
    public String add(String item) {
        int n = array.length + 1;
        String[] newArray = new String[n];

        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[n - 1] = item;
        array = newArray;
        return item;
    }

    @Override
    public String add(int index, String item) {

        if (index > array.length) {
            throw new IndexOutOfBoundsException();
        } else {
            int n = array.length + 1;
            String[] newArray = new String[n];

            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index - 1, newArray, index, newArray.length - index);
            newArray[index] = item;
            array = newArray;
            return item;
        }
    }

    @Override
    public String set(int index, String item) {
        if (index > array.length) {
            throw new IndexOutOfBoundsException();
        } else {
            array[index] = item;
            return item;
        }
    }

    @Override
    public String remove(String item) {
        String[] newArray = new String[array.length - 1];
        int index = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        } else {
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);
            array = newArray;
        }
        return item;
    }

    @Override
    public String remove(int index) {
        String[] newArray = new String[array.length - 1];
        String removeString;

        if (index > array.length) {
            throw new IndexOutOfBoundsException();
        } else {
            removeString = array[index];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);
            array = newArray;
        }
        return removeString;
    }

    @Override
    public boolean contains(String item) {
        int index = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index != -1;
    }

    @Override
    public int indexOf(String item) {
        int index = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        int index = -1;

        if (array[array.length - 1].equals(item)) {
            index = array.length - 1;
        }
        return index;
    }

    @Override
    public String get(int index) {
        if(index > array.length) {
            throw new IndexOutOfBoundsException();
        } else {
            return array[index];
        }
    }

    @Override
    public boolean equals(StringList otherList) {
        if(otherList == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i < array.length; i++) {
            if(!array[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
    }

    @Override
    public String[] toArray() {
        String[] newArray = new String[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
        return newArray;
    }
}
