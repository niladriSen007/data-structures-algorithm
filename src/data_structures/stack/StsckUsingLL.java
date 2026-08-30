package data_structures.stack;

public class StsckUsingLL<T> {
    class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> top;
    private int size;

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");
        Node<T> elementToBeRemoved = top;
        top = top.next;
        size--;
        return elementToBeRemoved.data;
    }

    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");
        return top.data;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }
}
