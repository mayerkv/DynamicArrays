package model;

public class FactorArray<T> implements IArray<T> {

    private Object[] array;
    private int factor;
    private int size;

    public FactorArray(int factor, int initLength) {
        this.factor = factor;
        array = new Object[initLength];
        size = 0;
    }

    public FactorArray() {
        this(50, 10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() == array.length)
            resize();
        array[size] = item;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    private void resize() {
        Object[] newArray = new Object[array.length + array.length * factor / 100];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public void add(T item, int index) {
        Object[] newArray = alloc(index);

        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index, newArray, index + 1, size() - index);
        newArray[index] = item;
        array = newArray;
        size++;
    }

    private Object[] alloc(int index) {
        int capacity = array.length;
        int newSize = index > array.length ? index : size() + 1;
        if (newSize <= capacity) {
            return array;
        }

        while (newSize > capacity) {
            capacity += array.length + array.length * factor / 100;
        }
        return new Object[capacity];
    }

    @Override
    public T remove(int index) {
        int lastIdx = size() - 1;

        if (index > lastIdx) {
            return null;
        }

        T o = (T) array[index];

        System.arraycopy(array, index + 1, array, index, lastIdx - index);
        array[lastIdx] = null;
        size--;

        return o;
    }
}
