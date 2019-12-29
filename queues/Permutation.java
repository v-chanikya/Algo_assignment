/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("This reqiuires one argumrnt exzactly");
        }
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> myque = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            myque.enqueue(StdIn.readString());
        }

        Iterator<String> iter = myque.iterator();
        for (int i = 0; i < k; i++) {
            if (iter.hasNext()) {
                System.out.println(iter.next());
            }
        }
    }
}
