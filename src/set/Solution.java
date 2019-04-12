package set;

import java.util.*;

public class Solution {
    private int distance(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashSet<Integer> set = new HashSet<>(Arrays.asList(distance(p1, p2), distance(p1, p3), distance(p1, p4), distance(p2, p3), distance(p2, p4), distance(p3, p4)));
        return !set.contains(0) && set.size() == 2;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        createSubSet(nums, nums.length - 1, subset, result);
        return result;
    }

    private void createSubSet(int[] nums, int end, List<Integer> subset, List<List<Integer>> result) {
        if (end == 0) {
            result.add(new ArrayList<>(subset));
        } else {
            subset.add(nums[end]);
            createSubSet(nums, end - 1, subset, result);
            subset.remove(subset.size() - 1);
            createSubSet(nums, end - 1, subset, result);
        }
    }

    public List<List<Integer>> superSet(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> subres = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int n : nums) {
            subres.clear();
            for (List<Integer> sublist : result) {
                List<Integer> sub = new ArrayList<>(sublist);
                sub.add(n);
                subres.add(sub);
            }
            result.addAll(subres);
        }
        return result;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Map<Integer, Integer> board = new HashMap<>();
        for (int n : nums) {
            board.put(n, board.getOrDefault(n, 0) + 1);
        }
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> subres = new ArrayList<>();
        result.addAll(new ArrayList<>());
        for (Map.Entry<Integer, Integer> e : board.entrySet()) {
            subres.clear();
            for (List<Integer> subset : result) {
                if (e.getValue() != 1) {
                    List<Integer> tmp = new ArrayList<>(subset);
                    tmp.add(e.getValue());
                    subres.add(tmp);
                } else {
                    for (int i = 1; i <= e.getValue(); i++) {
                        List<Integer> tmp = new ArrayList<>(subset);
                        for (int j = 0; j < i; j++) {
                            tmp.add(e.getKey());
                        }
                        subres.add(tmp);
                    }
                }
            }
            result.addAll(subres);
        }
        return result;
    }
}
