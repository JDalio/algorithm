package trie;

import java.util.Arrays;

class WordDictionary
{
    private WordDictionary[] root = new WordDictionary[26];
    private boolean isWord;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary()
    {
        isWord = false;
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word)
    {
        //System.out.println("word: "+word);
        WordDictionary[] p = root;
        WordDictionary wordp = null;
        int i = -1;
        for (char ch : word.toCharArray())
        {
            //System.out.println(ch);
            i = ch - 'a';
            if (p[i] == null)
            {
                p[i] = new WordDictionary();
                wordp = p[i];
            }

            p = p[i].root;
        }
        //System.out.println("here");
        if (wordp != null && i != -1 && !wordp.isWord)
            wordp.isWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(char[] word, WordDictionary[] dict)
    {
        if (word.length == 0)
            return false;
        if (word[0] == '.')
        {
            if (word.length == 1)
            {
                for (WordDictionary d : dict)
                {
                    if (d != null && d.isWord)
                        return true;
                }
            }
            for (WordDictionary d : dict)
            {
                if (d != null && search(Arrays.copyOfRange(word, 1, word.length), d.root))
                    return true;
            }
        }

        if (word[0] < 'a' || word[0] > 'z')
            return false;

        int i = word[0] - 'a';
        if (dict[i] == null)
            return false;
        if (word.length == 1 && dict[i].isWord)
            return true;
        return search(Arrays.copyOfRange(word, 1, word.length), dict[i].root);
    }

    public boolean search(String word)
    {
        System.out.println(word);
        return search(word.toCharArray(), root);
    }
}
