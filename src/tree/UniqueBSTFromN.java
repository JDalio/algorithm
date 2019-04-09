package tree;

import java.util.ArrayList;
import java.util.List;

public class UniqueBSTFromN
{
    //use dynamic programming
    //计算二叉树的数量 首先是二叉树的构造，然后是相同构造内取值的数量
    //构造的数量和取值无关，由左子树的构造数量*右子树的构造数量得到，是一个动态规划的过程
    public int numTrees(int n)
    {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n; i++)
            for (int j = 0; j < i; j++)
                dp[i] += dp[j] * dp[i - j - 1];

        return dp[n - 1];
    }

    // generate bst from n with diverse structures
    public List<TreeNode> generateTrees(int n)
    {
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int start, int end)
    {
        List<TreeNode> list = new ArrayList<>();
        if (start == end)
        {
            list.add(new TreeNode(start));
            return list;
        }

        if (start > end)
        {
            list.add(null);
            return list;
        }

        for (int i = start; i <= end; i++)
        {
            List<TreeNode> lTrees = buildTrees(start, i - 1);
            List<TreeNode> rTrees = buildTrees(i + 1, end);

            for (TreeNode lroot : lTrees)
                for (TreeNode rroot : rTrees)
                {
                    TreeNode root = new TreeNode(i);
                    root.left = lroot;
                    root.right = rroot;
                    list.add(root);
                }
        }
        return list;
    }
}
