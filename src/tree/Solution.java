package tree;

import java.util.*;

public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //level order
    class Node {
        TreeNode treeNode;
        int depth;

        public Node(TreeNode treeNode, int depth) {
            this.treeNode = treeNode;
            this.depth = depth;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0));
        List<Integer> element = new ArrayList<>();
        int preDepth = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.depth == preDepth)
                element.add(node.treeNode.val);
            else {
                result.add(element);
                element = new ArrayList<>();
                element.add(node.treeNode.val);
                preDepth++;
            }
            if (node.treeNode.left != null)
                queue.offer(new Node(node.treeNode.left, node.depth + 1));

            if (node.treeNode.right != null)
                queue.offer(new Node(node.treeNode.right, node.depth + 1));
        }
        result.add(element);
        return result;
    }

    //zig zag level order
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean left2right = true;
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 0));
        List<Integer> element = new ArrayList<>();
        int preDepth = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.depth == preDepth) {
                if (left2right)
                    element.add(node.treeNode.val);
                else
                    element.add(0, node.treeNode.val);
            } else {
                result.add(element);
                element = new ArrayList<>();
                element.add(node.treeNode.val);
                preDepth++;
                left2right = !left2right;
            }

            if (node.treeNode.left != null)
                queue.offer(new Node(node.treeNode.left, node.depth + 1));
            if (node.treeNode.right != null)
                queue.offer(new Node(node.treeNode.right, node.depth + 1));


        }
        result.add(element);
        return result;
    }

    //insert
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        TreeNode node = root;
        while (true) {
            if (val > node.val) {
                if (node.right == null) {
                    node.right = new TreeNode(val);
                    break;
                } else
                    node = node.right;
            } else {
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    break;
                } else
                    node = node.left;
            }
        }
        return root;
    }

    //search
    public TreeNode searchBST(TreeNode root, int val) {
        if (root.val == val)
            return root;
        if (val > root.val)
            return searchBST(root.right, val);
        return searchBST(root.left, val);
    }

    //Verify Preorder Serialization of a Binary Tree
    public boolean isValidSerialization(String preorder) {
        Stack<String> stk = new Stack<>();
        for (String str : preorder.split(",")) {
            if (str.equals(",")) continue;
            if (stk.size() == 1 && stk.peek().equals("#"))
                return false;

            if (!str.equals("#"))
                stk.push(str);
            else {
                while (!stk.isEmpty() && stk.peek().equals("#")) {
                    stk.pop();
                    stk.pop();
                }
                stk.push("#");
            }
        }
        // char ch1=stk.peek();
        // System.out.println("****\n"+(stk.peek().equals("#"))+(stk.peek()=="#")+" "+stk.size());

        if (stk.size() == 1 && stk.peek() == "#")
            return true;
        return false;
    }
    //End Verify Preorder Serialization of a Binary Tree

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root.right == null && root.left == null && root.val == sum) {
            return true;
        }
        if (root.left != null) {
            root.left.val = 1 + root.val;
            if (dfs(root.left, sum)) {
                return true;
            }
        }
        if (root.right != null) {
            root.right.val = 1 + root.val;
            if (dfs(root.right, sum)) {
                return true;
            }
        }
        return false;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        int subSum = 0;
        Stack<Integer> stk = new Stack<>();
        pathSum(root, result, stk, subSum, sum);
        return result;
    }

    private void pathSum(TreeNode root, List<List<Integer>> result, Stack<Integer> stk, int subSum, int sum) {

        subSum += root.val;
        stk.push(root.val);
        if (root.left == null && root.right == null && subSum == sum) {
            result.add(new ArrayList<>(stk));
            subSum -= root.val;
            stk.pop();
        } else {
            if (root.left != null) {
                pathSum(root.left, result, stk, subSum, sum);
                subSum -= root.val;
                stk.pop();
            }

            if (root.right != null) {
                pathSum(root.right, result, stk, subSum, sum);
                subSum -= root.val;
                stk.pop();
            }
        }

    }
}
