package divide_conquer;

import java.util.Stack;

public class Solution {
    private int MOD = (int) 1e9 + 7;

    public int sumSubarrayMins(int[] A) {
        int[] ple = new int[A.length];
        int[] nle = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            nle[i] = -1;
        }
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            while (!stk.isEmpty() && A[i] < A[stk.peek()]) {
                int x = stk.pop();
                nle[x] = i;
            }
            ple[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(i);
        }

        int result = 0;
        for (int i = 0; i < A.length; i++) {
            int lspan = i - ple[i];
            int rspan = (nle[i] == -1 ? A.length : nle[i]) - i;
            result += lspan * rspan * A[i];
            if (result > MOD) {
                result -= MOD;
            }
        }
        return result % MOD;
    }
}
