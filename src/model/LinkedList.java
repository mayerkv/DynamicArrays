package model;

public class LinkedList<T> implements IArray<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        Node<T> node = new Node<>(item);

        if (size == 0) {
            head = node;
        } else {
            tail.next = node;
        }

        tail = node;
        size++;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.value;
    }

    @Override
    public void add(T item, int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 0 || size == index) {
            add(item);
            return;
        }

        int pos = 0;
        Node<T> curr = head;
        Node<T> prev = null;
        Node<T> node = new Node<>(item);

        while (pos < index) {
            prev = curr;
            curr = curr.next;
            pos++;
        }
        if (prev != null) {
            prev.next = node;
        }
        node.next = curr;
        if (index == 0) {
            head = node;
        }
        if (index == size - 1) {
            tail = node;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int pos = 0;
        Node<T> curr = head;
        Node<T> prev = null;

        while (pos < index) {
            prev = curr;
            curr = curr.next;
            pos++;
        }
        if (prev != null) {
            prev.next = curr.next;
        }

        if (index == 0) {
            head = curr.next;
        }
        if (index == size - 1) {
            tail = prev;
        }

        T value = curr.value;

        size--;

        return value;
    }

    static class Node<T> {
        private final T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
