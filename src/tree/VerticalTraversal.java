package tree;

import java.util.*;

class VerticalTraversal
{
    class Node implements Comparable<Node>
    {
        int x;
        int depth;
        TreeNode treeNode;

        public Node(int x, int depth, TreeNode node)
        {
            this.treeNode = node;
            this.depth = depth;
            this.x = x;
        }

        @Override
        public int compareTo(Node node)
        {
            if (this.x != node.x)
                return this.x - node.x;
            if (this.depth != node.depth)
                return this.depth - node.depth;
            return this.treeNode.val - node.treeNode.val;
        }
    }

    private void bfs(TreeNode root, List<Node> nodeList)
    {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, 0, root));
        while (!queue.isEmpty())
        {
            Node cur = queue.poll();
            nodeList.add(cur);
            if (cur.treeNode.left != null)
                queue.offer(new Node(cur.x - 1, cur.depth + 1, cur.treeNode.left));
            if (cur.treeNode.right != null)
                queue.offer(new Node(cur.x + 1, cur.depth + 1, cur.treeNode.right));
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root)
    {
        List<Node> nodeList = new ArrayList<>();
        bfs(root, nodeList);
        Collections.sort(nodeList);
        List<List<Integer>> result = new ArrayList<>();
        Node prev = null;
        for (Node node : nodeList)
        {
            //System.out.println(node.treeNode.val);
            if (prev == null || prev.x != node.x)
            {
                prev = node;
                List<Integer> list = new ArrayList<>();
                list.add(node.treeNode.val);
                result.add(list);
            } else if (prev.x == node.x)
            {
                prev = node;
                int index = result.size() - 1;
                List<Integer> list = result.get(index);
                list.add(node.treeNode.val);
                result.set(index, list);
            }
        }
        return result;
    }
}
