/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("This reqiuires one argumrnt exzactly");
        }
        int k = Integer.parseInt(args[0]);

        Deque<String> myque = new Deque<>();
        while (!StdIn.isEmpty()) {
            myque.addLast(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            Iterator<String> iter = myque.iterator();
            int rand = StdRandom.uniform(myque.size());
            for (int j = 1; j < rand; j++) {
                if (iter.hasNext()) {
                    iter.next();
                }
            }
            System.out.println(iter.next());
        }
    }
}
