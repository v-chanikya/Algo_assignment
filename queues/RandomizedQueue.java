/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private final ArrayList<Item> myQueue = new ArrayList<>();

    // Empty constructor
    public RandomizedQueue() {
    }

    public boolean isEmpty() {
        return myQueue.isEmpty();
    }

    public int size() {
        return myQueue.size();
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("null argument not accepted");
        }
        myQueue.add(item);
    }

    public Item dequeue() {
        if (myQueue.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return myQueue.remove(StdRandom.uniform(myQueue.size()));
    }

    public Item sample() {
        if (myQueue.isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return myQueue.get(StdRandom.uniform(myQueue.size()));
    }

    public static void main(String[] args) {
        // Intentionally left blank;
    }

    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Item> {

        private final int[] randSeq;
        private int currentIndex = 0;

        public MyIterator() {
            randSeq = StdRandom.permutation(myQueue.size());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This method is not supported");
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
            return myQueue.get(randSeq[currentIndex++]);
        }
    }
}
