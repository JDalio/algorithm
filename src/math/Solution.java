package math;

import java.util.*;

public class Solution {
    private int base = 1337;

    private int powmod(int a, int k) {
        int core = a % base;
        int result = 1;
        for (int i = 0; i < k; i++) {
            result = result * core % base;
        }
        return result;
    }

    public int superPow(int a, int[] b) {
        if (b.length == 1) {
            return powmod(a, b[0]);
        }

        int sub1 = superPow(superPow(a, Arrays.copyOf(b, b.length - 1)), new int[]{10});
        int sub2 = superPow(a, new int[]{b[b.length - 1]});
        return sub1 * sub2 % base;

    }

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> board = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (board.containsKey(target - nums[i])) {
                result[0] = i;
                result[1] = board.get(target - nums[i]);
                return result;
            } else {
                board.put(target - nums[i], i);
            }
        }
        return result;
    }

    private List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0 || nums[0] > 0) {
            return result;
        }
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            int lo = i + 1, hi = nums.length - 1;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (lo < hi) {
                if (nums[lo] + nums[hi] == target - 1 * nums[i]) {
                    result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                }
                if (nums[lo] + nums[hi] <= target - 1 * nums[i]) {
                    while (lo + 1 < hi && nums[lo + 1] == nums[lo]) {
                        lo++;
                    }
                    lo++;
                } else {
                    while (hi - 1 > lo && nums[hi - 1] == nums[hi]) {
                        hi--;
                    }
                    hi--;
                }
            }
        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length == 0 || nums[0] > 0) {
            return result;
        }
        for (int i = 0; i < nums.length - 3; i++) {
            List<List<Integer>> tmp = threeSum(Arrays.copyOfRange(nums, i + 1, nums.length), -1 * (target - nums[i]));
            for (List<Integer> l : tmp) {
                l.add(nums[i]);
                result.add(l);
            }
        }
        return result;
    }

    public int findMinMoves(int[] machines) {
        int[] board = new int[machines.length + 1];
        for (int i = 0; i < machines.length; i++) {
            board[i + 1] = board[i] + machines[i];
        }
        int total = board[board.length];
        if (total % machines.length != 0) {
            return -1;
        }
        int level = total / machines.length;
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < machines.length; i++) {
            int lcnt = board[i] - level * i;
            int rcnt = total - board[i + 1] - level * (machines.length - i - 1);
            if (lcnt < 0 && rcnt < 0) {
                result = Math.max(result, -1 * lcnt - rcnt);
            } else if (lcnt > 0 && rcnt > 0) {
                result = Math.max(result, Math.max(lcnt, rcnt));
            } else {
                result = Math.max(result, Math.max(Math.abs(lcnt), Math.abs(rcnt)));
            }
        }
        return result;
    }

//    public int mySqrt(int x) {
//        if (x == 0) {
//            return 0;
//        }
//        if (x < 4) {
//            return 1;
//        }
//        int i = 0;
//        while(i < x / 2&&i * i > x){
//            i++;
//        }
//        return i - 1;
//    }
//    public static void main(String[] args) {
//        int[] ints = new int[]{1, 2, 3, 4, 5};
//        for (int i : Arrays.copyOf(ints, 4)) {
//            System.out.println(i);
//        }
//    }
}




