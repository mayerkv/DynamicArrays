package model;

public class SingleArray<T> implements IArray<T> {

    private Object[] array;

    public SingleArray() {
        array = new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        resize();
        array[size() - 1] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    private void resize() {
        Object[] newArray = new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }

    @Override
    public void add(T item, int index) {
        int newSize = index > array.length ? index : size() + 1;
        Object[] newArray = new Object[newSize];

        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index, newArray, index + 1, size() - index);
        newArray[index] = item;

        array = newArray;
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

        return o;
    }
}
