package heap;

import java.util.*;

public class KthLargest
{
    final private PriorityQueue<Integer> pq;
    final private int kth;

    //Kth Largest Element in a Stream
    public KthLargest(int k, int[] nums)
    {
        this.pq = new PriorityQueue<>();
        for (int num : nums)
            pq.add(num);
        this.kth = k;
    }

    public int add(int val)
    {
        if (pq.size() < kth)
            pq.offer(val);
        else if (val > pq.peek())
        {
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();

    }

    //End Kth Largest Element in a Stream
    //Kth Largest Element in an Array
    public int findKthLargest(int[] nums, int k)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i : nums)
        {
            if (pq.size() < k)
                pq.offer(i);
            else if (i > pq.peek())
            {
                pq.poll();
                pq.offer(i);
            }
        }
        return pq.peek();
    }

    //End Kth Largest Element in an Array
    public int thirdMax(int[] nums)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>(3);
        for (int i : nums)
        {
            if (pq.size() < 3 && !pq.contains(i))
                pq.offer(i);
            else if (i > pq.peek())
            {
                pq.poll();
                pq.offer(i);
            }
        }

        if (pq.size() < 3)
            pq.poll();
        return pq.peek();
    }

    //Swim in Rising Water

    //End Swim in Rising Water

//    public int trapRainWater(int[][] heightMap) {
//
//    }
}
