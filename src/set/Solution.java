package set;

import java.util.Arrays;
import java.util.HashSet;

public class Solution
{
    private int distance(int[] p1, int[] p2)
    {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4)
    {
        HashSet<Integer> set = new HashSet<>(Arrays.asList(distance(p1, p2), distance(p1, p3), distance(p1, p4), distance(p2, p3), distance(p2, p4), distance(p3, p4)));
        return !set.contains(0) && set.size() == 2;
    }
}
