package model;

public class ArrayList<T> implements IArray<T> {
    java.util.ArrayList<T> array = new java.util.ArrayList<T>();

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public void add(T item) {
        array.add(item);
    }

    @Override
    public T get(int index) {
        return array.get(index);
    }

    @Override
    public void add(T item, int index) {
        array.add(index, item);
    }

    @Override
    public T remove(int index) {
        return array.remove(index);
    }
}
