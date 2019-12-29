/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int queueDepth = 0;

    private class MyQueue {
        private Item currentNode;
        private MyQueue next;
        private MyQueue prev;
    }

    private MyQueue head = null;
    private MyQueue tail = null;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return queueDepth == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return queueDepth;
    }

    // returns nth item in a queue
    private MyQueue getN(int n) {
        MyQueue temp = head;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null value not accepted");
        }
        MyQueue temp = new MyQueue();
        temp.currentNode = item;
        if (head == null && tail == null) {
            head = temp;
            tail = temp;
        }
        else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        queueDepth++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (queueDepth == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        MyQueue temp = getN(StdRandom.uniform(queueDepth));
        if (temp.prev == null && temp.next == null) {
            head = null;
            tail = null;
        }
        else if (temp.prev == null) {
            head = temp.next;
            temp.next.prev = null;
        }
        else if (temp.next == null) {
            tail = temp.prev;
            temp.prev.next = null;
        }
        else {
            temp.next.prev = temp.prev;
            temp.prev.next = temp.next;
        }
        queueDepth--;
        return temp.currentNode;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (queueDepth == 0) {
            throw new NoSuchElementException("The queue is empty");
        }
        return getN(StdRandom.uniform(queueDepth)).currentNode;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandQueueIter();
    }

    private class RandQueueIter implements Iterator<Item> {

        private final int[] randSeq;
        private Object[] myQueuesInstance;
        private int currentIndex = 0;
        private MyQueue qiter;

        public RandQueueIter() {
            randSeq = StdRandom.permutation(queueDepth);
            this.qiter = head;
            myQueuesInstance = new Object[queueDepth];
            for (int i = 0; i < queueDepth; i++) {
                myQueuesInstance[i] = this.qiter;
                this.qiter = this.qiter.next;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            if (currentIndex < randSeq.length) {
                return true;
            }
            return false;
        }

        @Override
        public Item next() {
            if (currentIndex == randSeq.length) {
                throw new NoSuchElementException("The iterator has reached the end");
            }
            Item temp = ((MyQueue) myQueuesInstance[randSeq[currentIndex]]).currentNode;
            myQueuesInstance[randSeq[currentIndex]] = null;
            currentIndex++;
            return temp;
        }
    }

    public static void main(String[] args) {
        // Intentionally left empty
    }
}
