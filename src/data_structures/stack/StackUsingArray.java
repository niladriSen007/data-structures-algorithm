package data_structures.stack;

public class StackUsingArray<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULY_CAPACITY = 10;

    public StackUsingArray() {
        elements = new Object[DEFAULY_CAPACITY];
    }

    public void push(T data) {
        if (size == elements.length)
            resize();

        elements[size++] = data;
    }

    public T pop() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");
        T elementToBeRemoved = (T) elements[size - 1];
        elements[size - 1] = null;
        size--;
        return elementToBeRemoved;
    }

    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");
        return (T) elements[size - 1];
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    private void resize() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, size);
    }
}
