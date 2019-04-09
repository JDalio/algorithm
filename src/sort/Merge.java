package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Merge
{
    //Merge k Sorted Lists
    public ListNode mergeKLists(ListNode[] lists)
    {
        if (lists.length == 1)
            return lists[0];
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>()
        {
            @Override
            public int compare(ListNode n1, ListNode n2)
            {
                return n1.val - n2.val;
            }
        });

        for (ListNode head : lists)
        {
            if (head != null)
                queue.add(head);
        }


        ListNode head = new ListNode(0), p = head;
        while (!queue.isEmpty())
        {
            if (queue.size() == 1)
            {
                p.next = queue.peek();
                break;
            }
            ListNode tmp = queue.poll();
            p.next = tmp;
            p = p.next;
            tmp = tmp.next;
            if (tmp != null)
                queue.add(tmp);
        }
        return head.next;
    }
    //End Merge k Sorted Lists

}
