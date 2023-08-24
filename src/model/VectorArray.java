package model;

public class VectorArray<T> implements IArray<T> {

    private Object[] array;
    private int vector;
    private int size;

    public VectorArray(int vector) {
        this.vector = vector;
        array = new Object[0];
        size = 0;
    }

    public VectorArray() {
        this(10);
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
        Object[] newArray = new Object[array.length + vector];
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
            capacity += vector;
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
