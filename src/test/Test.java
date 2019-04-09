package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Test {
    public static void main(String[] args) {
        int[] a = new int[]{15, 3, 7, 1, 9, 8, 4, 5};

        for (int i : Arrays.copyOfRange(a,0,a.length)) {
            System.out.println(i + " ");
        }
    }
}
