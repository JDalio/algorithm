package recursion;

import trie.Trie;

import java.util.*;

public class Solution
{
    //Word Search
    class TrieNode
    {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    private TrieNode buildTrie(String[] words)
    {
        TrieNode root = new TrieNode();
        for (String word : words)
        {
            TrieNode p = root;
            for (char c : word.toCharArray())
            {
                int i = c - 'a';
                if (p.next[i] == null)
                    p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = word;
        }
        return root;
    }

    private void findWords(char[][] board, TrieNode p, int i, int j, List<String> result)
    {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;

        int n = board[i][j] - 'a';
        char ch = board[i][j];
        if (board[i][j] == '#' || p.next[n] == null) return;
        p = p.next[n];
        if (p.word != null)
        {
            result.add(p.word);
            p.word = null;
        }

        board[i][j] = '#';
        findWords(board, p, i + 1, j, result);
        findWords(board, p, i - 1, j, result);
        findWords(board, p, i, j + 1, result);
        findWords(board, p, i, j - 1, result);
        board[i][j] = ch;
    }

    public List<String> findWords(char[][] board, String[] words)
    {
        TrieNode root = buildTrie(words);
        List<String> result = new LinkedList<>();
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
                findWords(board, root, i, j, result);
        }
        return result;
    }

    private boolean exist(char[][] board, char[] charArray, int i, int j, int start)
    {
        if (start == charArray.length) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if (board[i][j] != charArray[start]) return false;

        board[i][j] ^= 256;
        boolean exist = exist(board, charArray, i + 1, j, start + 1)
                || exist(board, charArray, i - 1, j, start + 1)
                || exist(board, charArray, i, j + 1, start + 1)
                || exist(board, charArray, i, j - 1, start + 1);
        if (exist)
            return true;
        else
        {
            board[i][j] ^= 256;
            return false;
        }

    }

    public boolean exist(char[][] board, String word)
    {
        if (board == null)
            return false;
        char[] charArray = word.toCharArray();

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
                if (exist(board, charArray, i, j, 0))
                    return true;
        }
        return false;
    }

    //end word search
    //Top K Frequent numbers
    public List<Integer> topKFrequent(int[] nums, int k)
    {
        List<Integer> result = new ArrayList<>();
        List<Integer>[] bucket = new ArrayList[nums.length + 1];

        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int n : nums)
        {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet())
        {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null)
                bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(key);
        }

        for (int i = bucket.length - 1; i >= 0 && k > 0; i--)
        {
            if (bucket[i] != null)
            {
                for (int ii : bucket[i])
                {
                    result.add(ii);
                    k--;
                }
            }

        }
        return result;
    }

    //End Top K Frequent numbers
    //Sort Characters By Frequency
    public String frequencySort(String s)
    {
        String result = new String();
        List<Character>[] buckets = new List[s.length() + 1];
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : s.toCharArray())
        {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        for (Character ch : frequencyMap.keySet())
        {
            int frequency = frequencyMap.get(ch);
            if (buckets[frequency] == null)
            {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(ch);
        }

        for (int i = buckets.length - 1; i >= 0; i--)
        {
            int len = i;
            if (buckets[i] != null)
            {
                for (Character ch : buckets[i])
                {
                    for (int j = 0; j < len; j++)
                        result += ch.toString();
                }

            }
        }
        return result;
    }

    //End Sort Characters By Frequency
    //Top K Frequent Words
    public List<String> topKFrequent(String[] words, int k)
    {
        List<String> result = new ArrayList<>();

        HashMap<String, Integer> wordMap = new HashMap<>();
        for (String word : words)
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);

        PriorityQueue<String>[] bucket = new PriorityQueue[words.length + 1];
        for (String key : wordMap.keySet())
        {
            int frequency = wordMap.get(key);
            if (bucket[frequency] == null)
                bucket[frequency] = new PriorityQueue<>();
            bucket[frequency].add(key);
        }

        for (int i = bucket.length; i >= 0 && k > 0; i--)
        {
            if (bucket[i] != null)
            {
                while (!bucket[i].isEmpty())
                {
                    result.add(bucket[i].poll());
                    k--;
                }
            }
        }
        return result;
    }
    //End Top K Frequent Words
}
