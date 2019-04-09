package sort;

public class Solution {
    public int[] sortedSquares(int[] A) {
        int j = A.length;
        int[] result = new int[j--];
        int i = 0;
        for (int p = 0; p < A.length; p++) {
            if (Math.abs(A[i]) < Math.abs(A[j])) {
                result[p] = A[i++];
            } else {
                result[p] = A[j++];
            }
        }
        return result;
    }
}