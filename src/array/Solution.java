package array;

import java.util.HashMap;

public class Solution
{   //Unique Paths
    public int uniquePaths(int m, int n)
    {
        int[][] board = new int[m][n];
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (i == 0 || j == 0) board[i][j] = 1;
                else board[i][j] = board[i - 1][j] + board[i][j - 1];
            }
        }
        return board[m - 1][n - 1];
    }
    //End Unique Paths

    //Unique Paths II
    public int uniquePathsWithObstacles(int[][] obstacleGrid)
    {
        if (obstacleGrid[0][0] == 1) return 0;
        int rows = obstacleGrid.length, cols = obstacleGrid[0].length;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
            {
                if (obstacleGrid[i][j] == 1) obstacleGrid[i][j] = 0;
                else if (i == 0 && j == 0) obstacleGrid[i][j] = 1;
                else if (i == 0 && j != 0) obstacleGrid[i][j] = obstacleGrid[i][j - 1];
                else if (i != 1 && j == 0) obstacleGrid[i][j] = obstacleGrid[i - 1][j];
                else obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }
        return obstacleGrid[rows - 1][cols - 1];
    }
    //End Unique Paths II

    //X of a Kind in a Deck of Cards
    private int gcd(int n1, int n2)
    {
        if (n1 < n2)
        {
            int tmp = n1;
            n1 = n2;
            n2 = tmp;
        }

        int remainder = n1 % n2;
        while (remainder != 0)
        {
            n1 = n2;
            n2 = remainder;
            remainder = n1 % n2;
        }
        return n2;
    }

    public boolean hasGroupsSizeX(int[] deck)
    {
        if (deck.length == 1) return false;
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int i : deck)
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1);
        int gcd = frequencyMap.get(deck[0]);
        for (int frequency : frequencyMap.values())
            gcd = gcd(gcd, frequency);
        return gcd != 1;
    }
    //End X of a Kind in a Deck of Cards

}
