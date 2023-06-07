package integerAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntegerListImpl implements IntegerList{

    private Integer[] array;
    private int size = 7;

    public IntegerListImpl(Integer[] array) {
        this.array = array;
    }

    @Override
    public Integer add(Integer item) {
            add(item, array, size);

//        int n = array.length + 1;
//        Integer[] newArray = new Integer[n];
//
//        System.arraycopy(array, 0, newArray, 0, array.length);
//        newArray[n - 1] = item;
//        array = newArray;
        return item;
    }

    private void add(Integer item, Integer[] array, int s) {

        if (s == array.length)
            array = grow();
        array[s] = item;
        size = s + 1;
    }

    @Override
    public Integer add(int index, Integer item) {

        rangeCheckForAdd(index);
        final int s;
        Integer[] elementData;
        if ((s = size) == (elementData = this.array).length)
            elementData = grow();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = item;
        size = s + 1;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index > array.length) {
            throw new IndexOutOfBoundsException();
        } else {
            array[index] = item;
            return item;
        }
    }

    @Override
    public Integer remove(Integer item) {
        Integer[] newArray = new Integer[array.length - 1];
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
    public Integer remove(int index) {
        Integer[] newArray = new Integer[array.length - 1];
        Integer removeInteger;
        if (index > array.length) {
            throw new IndexOutOfBoundsException();
        } else {
            removeInteger = array[index];
            System.arraycopy(array, 0, newArray, 0, index);
            System.arraycopy(array, index + 1, newArray, index, newArray.length - index);
            array = newArray;
        }
        return removeInteger;
    }

    @Override
    public boolean contains(Integer item) {
        quickSort(0, array.length - 1);

        if(item > array[array.length - 1] || item < array[0]) {
            throw new IndexOutOfBoundsException();
        }

        return binarySearch(array, item, 0, array.length) != -1;
    }

    @Override
    public int indexOf(Integer item) {
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
    public int lastIndexOf(Integer item) {
        int index = -1;

        if (array[array.length - 1].equals(item)) {
            index = array.length - 1;
        }
        return index;
    }

    @Override
    public Integer get(int index) {
        if(index > array.length) {
            throw new IndexOutOfBoundsException();
        } else {
            return array[index];
        }
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
        return size;
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
    public Integer[] toArray() {
        Integer[] newArray = new Integer[array.length];

        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
        return newArray;
    }

    @Override
    public void bubbleSort() {
        boolean sorted = false;
        int temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i+1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }

    @Override
    public void selectionSort() {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            int minId = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minId = j;
                }
            }
            int temp = array[i];
            array[i] = min;
            array[minId] = temp;
        }
    }

    @Override
    public void quickSort(int begin, int end) {
        if (end <= begin) return;

        int pivot = partition(array, begin, end);
        quickSort(begin, pivot-1);
        quickSort(pivot+1, end);
    }

    private static int partition(Integer[] array, int begin, int end) {
        int pivot = end;

        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                int temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        int temp = array[pivot];
        array[pivot] = array[counter];
        array[counter] = temp;

        return counter;
    }

    private Integer binarySearch(Integer[] values, int valueToFind, int l, int r) {

        if (l == r) {
            return (values[l] == valueToFind) ? l : -1;
        }

        int mid = l + (r - l) / 2;

        if (valueToFind > values[mid]) {
            return binarySearch(values, valueToFind, mid + 1, r);
        } else if (values[mid] > valueToFind) {
            return binarySearch(values, valueToFind, l, mid - 1);
        }
        return mid;
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final Integer[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 10;

    private Integer[] grow(int minCapacity) {
        return array = Arrays.copyOf(array,
                newCapacity(minCapacity));
    }

    private Integer[] grow() {
        return grow(array.length + 1);
    }

    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (array == DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                return Math.max(DEFAULT_CAPACITY, minCapacity);
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return minCapacity;
        }
        return (newCapacity - MAX_ARRAY_SIZE <= 0)
                ? newCapacity
                : hugeCapacity(minCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE)
                ? Integer.MAX_VALUE
                : MAX_ARRAY_SIZE;
    }

    private void rangeCheckForAdd(int index) {
        if (index > array.length || index < 0)
            throw new IndexOutOfBoundsException();
    }
}
