package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BaseOpt
{
    public void inorder(TreeNode root)
    {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty())
        {
            while (root != null)
            {
                stack.push(root);
                root = root.left;
            }

            if (!stack.isEmpty())
            {
                root = stack.pop();
                System.out.println(root.val);
                root = root.right;
            }
        }

    }

    public void preorder(TreeNode root)
    {
        if (root != null)
        {
            System.out.println(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public void insert(TreeNode root, TreeNode node)
    {
        if (root == null)
        {
            root = node;
        } else
        {
            TreeNode tmp = null;
            while (root != null)
            {
                tmp = root;
                if (root.val < node.val)
                    root = root.right;
                else
                    root = root.left;
            }
            if (node.val > tmp.val)
                tmp.right = root;
            else
                tmp.left = root;

        }
    }

    public void delete(TreeNode node)
    {
        if (node.right == null && node.left == null)
        {
            node = null;
        } else if (node.right != null && node.left != null)
        {
            //get predecessor
            TreeNode tmp = node.right;
            while (tmp.left != null)
                tmp = tmp.left;
            tmp.p.left = null;
            tmp.p = node.p;
            tmp.right = node.right;
            tmp.left = node.left;
            node = null;
        } else
        {
            //replace with node's child
        }

    }

    public void dfs(TreeNode root)
    {
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        while (!stk.isEmpty())
        {
            TreeNode node = stk.pop();
            System.out.println(node.val);
            if (node.right != null)
                stk.push(node.right);
            if (node.left != null)
                stk.push(node.left);
        }
    }

    public void bfs(TreeNode root)
    {
        Queue<TreeNode>queue=new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty())
        {
            TreeNode node=queue.poll();
            System.out.println(node.val);
            if(node.left!=null)
                queue.offer(node.left);
            if(node.right!=null)
                queue.offer(node.right);
        }


    }

}
