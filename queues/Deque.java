/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int queueDepth = 0;

    private class Node {
        private Item nodeValue;
        private Node next;
        private Node prev;
    }

    private Node head = null;
    private Node tail = null;

    public Deque() {

    }

    public boolean isEmpty() {
        return (queueDepth == 0);
    }

    public int size() {
        return queueDepth;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null item not supported");
        }
        Node temp = new Node();
        temp.nodeValue = item;
        if (queueDepth == 0) {
            head = temp;
            tail = temp;
        }
        else {
            temp.next = head;
            head.prev = temp;
            head = temp;
        }
        queueDepth++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null item not supported");
        }
        Node temp = new Node();
        temp.nodeValue = item;
        if (queueDepth == 0) {
            head = temp;
            tail = temp;
        }
        else {
            temp.prev = tail;
            tail.next = temp;
            tail = temp;
        }
        queueDepth++;
    }

    public Item removeFirst() {
        if (queueDepth == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        Node temp = head;
        if (queueDepth == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }
        queueDepth--;
        return temp.nodeValue;
    }

    public Item removeLast() {
        if (queueDepth == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        Node temp = tail;
        if (queueDepth == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        queueDepth--;
        return temp.nodeValue;
    }

    public Iterator<Item> iterator() {
        return (new DequeIterator());
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public Item next() {
            Node temp = current;
            if (current == null) {
                throw new NoSuchElementException("There are no more items");
            }
            else {
                current = current.next;
            }
            return temp.nodeValue;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This method is not implemented");
        }
    }

    public static void main(String[] args) {

    }
}
