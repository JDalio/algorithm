package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Sort
{
    public static void insertSort(int[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            int temp = a[i];
            //insert temp into the prev array
            while (i > 0 && a[i - 1] > temp)
            {
                a[i] = a[i - 1];
                i--;
            }
            a[i] = temp;
        }
    }

    public int[] bucketSort(int[] arr, int num)
    {
        int min = arr[0], max = arr[0];
        for (int i : arr)
        {
            min = i < min ? i : min;
            max = i > max ? i : max;
        }

        float size = (max - min) / num;
        PriorityQueue<Integer>[] buckets = new PriorityQueue[num];
        for (int n : arr)
        {
            int i = (int) ((n - min) / size);
            if (i == num) i--;
            if (buckets[i] == null)
                buckets[i] = new PriorityQueue<>();
            buckets[i].add(n);
        }


        int n = 0;
        for (PriorityQueue<Integer> queue : buckets)
        {
            while (!queue.isEmpty())
                arr[n++] = queue.poll();

        }
        return arr;
    }

    public ListNode mergeSort(ListNode head)
    {
        if (head == null || head.next == null)
            return head;
        ListNode prev = head, slow = head, fast = head;
        while (fast != null && fast.next != null)
        {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        ListNode left = mergeSort(head);
        ListNode right = mergeSort(slow);

        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right)
    {
        ListNode head = new ListNode(0), p = head;
        while (left != null && right != null)
        {
            if (left.val > right.val)
            {
                p.next = right;
                right = right.next;
            } else
            {
                p.next = left;
                left = left.next;
            }
            p = p.next;
        }
        if (left != null)
            p.next = left;
        else
            p.next = right;
        return head.next;
    }

    private void swap(int a, int b)
    {
        int tmp = a;
        a = b;
        b = tmp;
    }

    public void sortColors(int[] nums)
    {
        int end = nums.length - 1, start = 0;
        for (int i = 0; i <= end; i++)
        {
            if (nums[i] == 0)
                swap(nums[start++], nums[i]);
            if (nums[i] == 2)
                swap(nums[i--], nums[end--]);
        }
    }
}
