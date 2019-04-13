package stack;

import java.util.Stack;

public class MonotoneStack {
    public int[] findPLE(int[] nums) {
        int[] result = new int[nums.length];
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stk.isEmpty() && nums[i] < nums[stk.peek()]) {
                stk.pop();
            }
            result[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(i);
        }
        return result;
    }

    public int[] findNLE(int[] nums) {
        int[] result = new int[nums.length];
        for(int i=0;i<result.length;i++){
            result[i]=-1;
        }
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stk.isEmpty() && nums[i] < nums[stk.peek()]) {
                int x = stk.pop();
                result[x] = i;
            }
            stk.push(i);
        }
        return result;
    }

//    public static void main(String[] args) {
//        int[]array=new int[]{3,7,2,8,4,6,9,10};
//        for(int n:findPLE(array)){
//            System.out.print(n+" ");
//        }
//        System.out.println(" ");
//        for(int n:findNLE(array)){
//            System.out.print(n+" ");
//        }
//    }
}
