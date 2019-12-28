/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */


import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.Scanner;

public class Permutation {
    public static void main(String[] args) {
        if (args.length > 1) {
            throw new IllegalArgumentException("This reqiuires one argumrnt exzactly");
        }
        int k = Integer.parseInt(args[0]);
        String inStream = null;

        Deque<String> myque = new Deque<>();
        Scanner myInScanner = new Scanner(System.in);
        if (myInScanner.hasNext()) {
            inStream = (myInScanner.nextLine());
        }

        assert inStream != null;
        String[] strArr = inStream.split(" ");

        int[] intArr = StdRandom.permutation(strArr.length);
        int i;
        for (i = 0; i < strArr.length; i++) {
            myque.addLast(strArr[intArr[i]]);
        }

        Iterator<String> iter = myque.iterator();
        for (i = 0; i < k; i++) {
            if (iter.hasNext()) {
                System.out.println(iter.next());
            }
        }
    }
}
