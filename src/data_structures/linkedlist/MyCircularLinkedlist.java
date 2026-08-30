package data_structures.linkedlist;

public class MyCircularLinkedlist<T> {
    class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public int size;
    public Node<T> head;
    public Node<T> tail;

    public MyCircularLinkedlist() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            tail = newNode;
        } else {
            newNode.next = head;
        }
        head = newNode;
        tail.next = head;
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        tail.next = head;
        size++;
    }

    public void add(int index, T data) {
        checkIndex(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size) {
            addLast(data);
            return;
        }

        Node<T> newNode = new Node<>(data);
        int startIndex = 0;
        Node<T> previous = head;
        while (startIndex < index - 1) {
            previous = previous.next;
            startIndex++;
        }

        newNode.next = previous.next;
        previous.next = newNode;

        size++;

    }

    public T removeFirst() {
        if (head == null)
            throw new IllegalArgumentException("Not enough element");

        Node<T> nodeToBeRemoved = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = nodeToBeRemoved.next;
            tail.next = head;
        }

        size--;
        return nodeToBeRemoved.data;
    }

    public T removeLast() {
        if (head == null)
            throw new IllegalArgumentException("Not enough element");

        Node<T> nodeToBeRemoved = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> tailsPrevious = head;
            while (tailsPrevious.next != tail) {
                tailsPrevious = tailsPrevious.next;
            }
            tailsPrevious.next = head;
            tail = tailsPrevious;
        }

        size--;
        return nodeToBeRemoved.data;
    }

    public T remove(int index) {
        checkIndex(index);

        if (head == null)
            throw new IllegalArgumentException("Not enough element");

        if (index == 0)
            removeFirst();

        if (index == size - 1)
            removeLast();

        int startIndex = 0;
        Node<T> previous = head;
        while (startIndex < index - 1) {
            previous = previous.next;
            startIndex++;
        }

        Node<T> nodeToBeRemoved = previous.next;
        previous.next = nodeToBeRemoved.next;

        size--;
        return nodeToBeRemoved.data;

    }

    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IllegalStateException("INDEX IS OUT OF RANGE");
    }
}
