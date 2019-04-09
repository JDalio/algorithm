package dp;

import java.util.*;

public class Solution {
    //Ugly Number II
    public int nthUglyNumber(int n) {
        if (n == 1) return 1;
        int[] result = new int[n];
        int t2 = 0, t3 = 0, t5 = 0;
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = Math.min(result[t2] * 2, Math.min(result[t3] * 3, result[t5] * 5));
            if (result[i] == result[t2] * 2) t2++;
            if (result[i] == result[t3] * 3) t3++;
            if (result[i] == result[t5] * 5) t5++;
        }
        return result[n - 1];
    }
    //End Ugly Number II

    //Super Ugly Number
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) return 1;
        int[] result = new int[n];
        result[0] = 1;
        int[] indexs = new int[primes.length];
        int minIndex;
        for (int i = 1; i < n; i++) {
            minIndex = 0;
            result[i] = result[indexs[0]] * primes[0];
            for (int j = 1; j < primes.length; j++) {
                if (result[indexs[j]] * primes[j] == result[i - 1])
                    indexs[j]++;
                if (result[indexs[j]] * primes[j] < result[i]) {
                    result[i] = result[indexs[j]] * primes[j];
                    minIndex = j;
                }
            }
            indexs[minIndex]++;
        }

        return result[n - 1];
    }

    //Super Ugly Number
    //Word Break
    public boolean wordBreak1(String s, List<String> wordDict) {
        boolean[] board = new boolean[s.length() + 1];
        board[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (board[j] && wordDict.contains(s.substring(j, i))) {
                    board[i] = true;
                    break;
                }

            }
        }
        return board[s.length()];
    }

    //End Word Break
    //Word Break II
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }

    private List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> cache) {
        if (cache.containsKey(s))
            return cache.get(s);

        List<String> result = new ArrayList<>();
        if (s.length() == 0)
            return result;

        for (String sub : wordDict) {
            if (s.startsWith(sub)) {
                List<String> sublist = dfs(s.substring(sub.length()), wordDict, cache);
                for (String subs : sublist)
                    result.add(sub + (subs.equals("") ? "" : " ") + subs);
            }
        }

        cache.put(s, result);
        return result;
    }
    //End Word Break II

    //  Partition to K Equal Sum Subsets
    private boolean canPartition(int[] nums, boolean[] board, int k, int start, int cur_sum, int target) {
        if (k == 1) {
            return true;
        }
        if (cur_sum == target) {
            return canPartition(nums, board, k - 1, 0, 0, target);
        }
        if (cur_sum < target) {
            for (int i = start; i < nums.length; i++) {
                if (!board[i]) {
                    board[i] = true;
                    if (canPartition(nums, board, k, i, cur_sum + nums[i], target)) {
                        return true;
                    }
                    board[i] = false;
                }
            }
        }

        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % k != 0) {
            return false;
        }
        boolean[] board = new boolean[nums.length];
        return canPartition(nums, board, k, 0, 0, sum / k);
    }
    //  Partition to K Equal Sum Subsets

}