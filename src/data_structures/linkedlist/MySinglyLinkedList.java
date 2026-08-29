package data_structures.linkedlist;

public class MySinglyLinkedList<T> {

    class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MySinglyLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);

        newNode.next = head;
        head = newNode;

        if (tail == null)
            tail = newNode;

        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            tail = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void add(int index, T data) {
        checkIndex(index);

        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }

        int startIndex = 0;
        Node<T> previous = head; // 1 2 3 4 => 1 2 5 3 4 i/p=(index=2,data=5)
        while (startIndex < index - 1) {
            previous = previous.next;
            startIndex++;
        }
        newNode.next = previous.next;
        previous.next = newNode;

        size++;
    }

    public T get(int index) {

        checkIndex(index);

        Node<T> current = head;
        int startIndex = 0;
        while (startIndex < index) {
            current = current.next;
            startIndex++;
        }
        return current.data;
    }

    public T set(int index, T data) {

        checkIndex(index);

        Node<T> current = head;
        int startIndex = 0;
        while (startIndex < index) {
            current = current.next;
            startIndex++;
        }
        current.data = data;
        return current.data;
    }

    public T removeFirst() {
        if (head == null)
            throw new IllegalStateException("No element exists in this list");

        T removedData = head.data;
        head = head.next;
        size--;

        if (size == 0)
            tail = null;

        return removedData;
    }

    public T removeLast() {
        if (head == null)
            throw new IllegalStateException("No element exists in this list");

        if (head == tail) {
            T removedData = head.data;
            size--;
            head = null;
            tail = null;
            return removedData;
        }

        T removedData = tail.data;

        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        current.next = null;
        tail = current;

        size--;

        return removedData;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0)
            removeFirst();
        if (index == size - 1)
            removeLast();

        int startIndex = 0;
        Node<T> previous = head; // 1 2 3 4 => 1 2 4 i/p=(index=2)
        while (startIndex < index - 1) {
            previous = previous.next;
            startIndex++;
        }
        Node<T> nodeToBeRemoved = previous.next;
        previous.next = nodeToBeRemoved.next;
        nodeToBeRemoved.next = null;

        size--;

        return nodeToBeRemoved.data;
    }

    public boolean contains(T data){
        if(head == null) throw new IllegalStateException("List is empty");

        Node<T> current = head;

        while(current!=null){
            if(current.data == data) return true;
            current=current.next;
        }

        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(index + "is out of range");
    }

}
