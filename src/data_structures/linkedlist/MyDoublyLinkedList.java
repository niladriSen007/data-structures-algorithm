package data_structures.linkedlist;

public class MyDoublyLinkedList<T> {
    class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public int size;
    public Node<T> head;
    public Node<T> tail;

    public MyDoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
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
        newNode.prev = previous;
        previous.next.prev = newNode;
        previous.next = newNode;

        size++;
    }

    public T get(int index) {
        checkIndex(index);
        int startIndex = 0;
        Node<T> current = head;
        while (startIndex < index) {
            current = current.next;
            startIndex++;
        }
        return current.data;
    }

    public T removeFirst() {
        if (head == null)
            throw new IllegalArgumentException("Not enough element");

        Node<T> nodeToBeRemoved = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return nodeToBeRemoved.data;
    }

    public T removeLast() {
        if (head == null)
            throw new IllegalArgumentException("Not enough element");

        Node<T> nodeToBeRemoved = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
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
        nodeToBeRemoved.next.prev = previous;
        nodeToBeRemoved.prev = null;
        nodeToBeRemoved.next = null;

        size--;
        return nodeToBeRemoved.data;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IllegalStateException("INDEX IS OUT OF RANGE");
    }

}
