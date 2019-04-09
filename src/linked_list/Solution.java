package linked_list;


public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode head = new ListNode(0);
        if (l1.val + l2.val < 10) {
            head.val = l1.val + l2.val;
            head.next = addTwoNumbers(l1.next, l2.next);
        } else {
            head.val = l1.val + l2.val - 10;
            if (l1.next != null) {
                l1.next.val++;
                head.next = addTwoNumbers(l1.next, l2.next);
            } else if (l2.next != null) {
                l2.next.val++;
                head.next = addTwoNumbers(l1.next, l2.next);
            } else
                head.next = new ListNode(1);
        }
        return head;
    }

    // Partition List
    public ListNode partition(ListNode head, int x) {
        ListNode bh_cur = new ListNode(-1), sh_cur = new ListNode(-1), bh = bh_cur, sh = sh_cur;
        while (head != null) {
            ListNode tmp = head;
            head = head.next;
            if (tmp.val >= x) {
                bh_cur.next = tmp;
                bh_cur = bh_cur.next;
                bh_cur.next = null;
            } else {
                sh_cur.next = tmp;
                sh_cur = sh_cur.next;
                sh_cur.next = null;
            }
        }
        sh_cur.next = bh.next;
        return sh.next;
    }

    //End Partition List
    // Swap Nodes in Pairs
    public ListNode swapPairs(ListNode head) {
        ListNode p1 = head, p2 = head.next, result = p2;
        while (p2 != null) {
            ListNode p11 = p1, p22 = p2;
            p1 = p2.next;
            if (p1 == null) {
                p2 = null;
            } else {
                p2 = p1.next;
            }
            p11.next = p22.next;
            p22.next = p1;
        }
        return result;
    }
    //End Swap Nodes in Pairs

    public ListNode[] splitListToParts(ListNode root, int k) {
        int nums = 0;
        ListNode head = root;
        while (head != null) {
            nums++;
            head = head.next;
        }
        int q, r;
        if (nums < k) {
            q = 1;
            r = 0;
        } else {
            q = nums / k;
            r = nums % k;
        }
        head = root;
        ListNode[] result = new ListNode[k];
        int i = 0;
        while (k > 0) {
            result[i++] = head;
            ListNode prev = null;
            int step;
            if (r == 0) {
                step = q;
            } else {
                r--;
                step = q + 1;
            }
            for (int n = 0; n < step; n++) {
                prev = head;
                head = head.next;
            }
            prev.next = null;
            k--;
        }
        return result;
    }
}
