package trie;

public class Trie
{
    class TrieNode
    {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    private TrieNode root;

    public Trie()
    {
        this.root = new TrieNode();
    }

    public void insert(String word)
    {
        TrieNode p = root;
        for (char ch : word.toCharArray())
        {
            int i = ch - 'a';
            if (p.next[i] == null)
                p.next[i] = new TrieNode();
            p = p.next[i];
        }
        if (p.word == null)
            p.word = word;
    }

    public boolean search(String word)
    {
        TrieNode p = root;
        for (char ch : word.toCharArray())
        {
            int i = ch - 'a';
            if (p.next[i] == null)
                return false;
            p = p.next[i];
        }
        if (p.word == word)
            return true;
        return false;
    }

    public boolean startsWith(String prefix)
    {
        TrieNode p = root;
        for (char ch : prefix.toCharArray())
        {
            int i = ch - 'a';
            if (p.next[i] == null)
                return false;
            p = p.next[i];
        }
        return true;
    }
}
