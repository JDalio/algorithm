package graph;

import java.util.*;

public class Solution {

    public int orangesRotting(int[][] grid) {
        int fresh = 0;
        int rows = grid.length, cols = grid[0].length;
        Queue<Pos> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2)
                    queue.offer(new Pos(i, j));
                if (grid[i][j] == 1)
                    fresh++;
            }

        int count = -1;
        Pos p = new Pos(-1, -1);
        while (!queue.isEmpty()) {
            p = queue.poll();
            count++;
            int i = p.x, j = p.y;
            if (j + 1 < cols && grid[i][j + 1] == 1) {
                grid[i][j + 1] = grid[i][j] + 1;
                queue.offer(new Pos(i, j + 1));
            }
            if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                grid[i][j - 1] = grid[i][j] + 1;
                queue.offer(new Pos(i, j - 1));
            }
            if (i + 1 < rows && grid[i + 1][j] == 1) {
                grid[i + 1][j] = grid[i][j] + 1;
                queue.offer(new Pos(i + 1, j));
            }
            if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                grid[i - 1][j] = grid[i][j] + 1;
                queue.offer(new Pos(i - 1, j));
            }
        }

        if (count != fresh || p.x == -1)
            return -1;
        return grid[p.x][p.y] - 2;
    }

    //Surrounded Regions
    class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void bfs(int x, int y, char[][] board) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(x, y));
        int distance = 0;
        while (!queue.isEmpty()) {
            Pos p = queue.poll();
            int i = p.x, j = p.y;
            if (i < board.length - 1 && board[i + 1][j] == 'O') {
                board[i + 1][j] = '#';
                queue.offer(new Pos(i + 1, j));
            }

            if (j < board[0].length - 1 && board[i][j + 1] == 'O') {
                board[i][j + 1] = '#';
                queue.offer(new Pos(i, j + 1));
            }

            if (i > 0 && board[i - 1][j] == 'O') {
                board[i - 1][j] = '#';
                queue.offer(new Pos(i - 1, j));
            }

            if (j > 0 && board[i][j - 1] == 'O') {
                board[i][j - 1] = '#';
                queue.offer(new Pos(i, j - 1));
            }
        }
    }

    public void solve(char[][] board) {
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O')
                bfs(0, i, board);
            if (board[board.length - 1][i] == 'O')
                bfs(board.length - 1, i, board);
        }

        for (int i = 1; i < board.length - 1; i++) {
            if (board[i][0] == 'O')
                bfs(i, 0, board);
            if (board[i][board[0].length - 1] == 'O')
                bfs(i, board[0].length - 1, board);
        }

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
    }
    //End Surrounded Regions

    //01 Matrix
    private boolean adgacent0(int[][] board, int i, int j) {
        if ((i < board.length - 1 && board[i + 1][j] == 0)
                || (j < board[0].length - 1 && board[i][j + 1] == 0)
                || (i > 0 && board[i - 1][j] == 0)
                || (j > 0 && board[i][j - 1] == 0))
            return true;
        return false;

    }

    private void bfs(int[][] board, int x, int y) {
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(x, y));
        int distance = 0;
        List<Map<Pos, Integer>> maps = new ArrayList<>();
        while (!queue.isEmpty()) {
            distance++;
            Pos p = queue.poll();
            int i = p.x, j = p.y;
            if (adgacent0(board, i, j))
                break;
            if (i < board.length - 1 && board[i + 1][j] == 0)
                queue.offer(new Pos(i + 1, j));
            if (j < board[0].length - 1 && board[i][j + 1] == 0)
                queue.offer(new Pos(i, j + 1));
            if (i > 0 && board[i - 1][j] == 0)
                queue.offer(new Pos(i - 1, j));
            if (j > 0 && board[i][j - 1] == 0)
                queue.offer(new Pos(i, j - 1));
        }
        board[x][y] = distance;
    }

    public int[][] updateMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1)
                    bfs(matrix, i, j);
            }
        return matrix;
    }
    //End  01 Matrix

    //473. Matchsticks to Square77
    private boolean makesquare(List<Integer> list, int length, int mark) {
        if (mark == 0 && list.size() == 0)
            return true;
        Integer num1 = list.remove(0);
        boolean canMakeSquare;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) + num1 == length) {
                Integer tmp = list.remove(i);
                if (makesquare(list, length, mark - 1))
                    return true;
                list.add(tmp);
            }
        }
        return false;
    }

    public boolean makesquare(int[] nums) {
        int total = 0;
        List<Integer> numList = new LinkedList<>();
        for (int i : nums) {
            total += i;
            numList.add(i);
        }
        if (total % 4 != 0) return false;

        int length = total / 4, mark = 4;

        for (int i = 0; i < numList.size(); i++) {
            if (numList.get(i) > length)
                return false;
            if (numList.get(i).equals(length)) {
                numList.remove(i);
                mark--;
                i--;
            }

        }

        if (mark == 0 && numList.size() == 0)
            return true;

        return makesquare(numList, length, mark);

    }

    private static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int[][] cache = new int[rows][cols];
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                max = Math.max(max, dfs(matrix, i, j, rows, cols, cache));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int rows, int cols, int[][] cache) {
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        int max = 0;
        for (int[] dir : dirs) {
            int ii = i + dir[0], jj = j + dir[1];
            if (ii < 0 || ii == rows || jj < 0 || jj == cols || matrix[ii][jj] <= matrix[i][j]) {
                continue;
            }
            int len = dfs(matrix, ii, jj, rows, cols, cache) + 1;
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }

    //Dj algorithm, with source node first
    public static Map<Integer, Integer> dj(int[][] graph) {
        int rows = graph.length, cols = graph[0].length;

        Map<Integer, Integer> result = new HashMap<>();
        result.put(0, 0);

        int length, node;
        while (result.size() < rows) {
            length = Integer.MAX_VALUE;
            node = -1;
            for (Integer key : result.keySet()) {
                for (int i = 0; i < cols; i++) {
                    if (graph[key][i] > 0 && !result.containsKey(i)) {
                        int tmp = graph[key][i] + result.get(key);
                        if (tmp < length) {
                            length = tmp;
                            node = i;
                        }
                    }
                }
            }
            result.put(node, length);
        }
        return result;

    }

//    public static void main(String[] args) {
//        int[][] graph = {
//                {0, 12, 0, 0, 0, 16, 14},
//                {12, 0, 10, 0, 0, 7, 0},
//                {0, 10, 0, 3, 5, 6, 0},
//                {0, 0, 3, 0, 4, 0, 0},
//                {0, 0, 5, 4, 0, 2, 8},
//                {16, 7, 6, 0, 2, 0, 9},
//                {14, 0, 0, 0, 8, 9, 0}
//        };
//        Map<Integer, Integer> result = dj(graph);
//        for (Integer key : result.keySet()) {
//            System.out.println(key + " " + result.get(key));
//        }
//        Map<Integer,Integer>map=new HashMap<>();
//        map.put(1,2);
//        map.put(3,4);
//        for(Integer key:map.keySet()){
//            System.out.println(map.get(key));
//        }

    class Ant {
        int x;
        int y;
        int level;

        public Ant(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }

        public void update(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ant ant = (Ant) o;
            return x == ant.x &&
                    y == ant.y &&
                    level == ant.level;
        }
    }

    public int swimInWater(int[][] grid) {
        List<Ant> crawled = new ArrayList<>();
        crawled.add(new Ant(0, 0, grid[0][0]));
        int N = grid.length;
        int time = 0;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Ant cur = new Ant(0, 0, grid[0][0]), dst = new Ant(N - 1, N - 1, grid[N - 1][N - 1]);
        while (!cur.equals(dst)) {

        }
    }
}