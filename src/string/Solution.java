package string;

import java.util.*;

public class Solution {
    //Ugly Number
    public boolean isUgly(int num) {
        if (num == 2 || num == 3 || num == 5) return true;
        if (num % 2 == 0) return isUgly(num / 2);
        if (num % 3 == 0) return isUgly(num / 3);
        if (num % 5 == 0) return isUgly(num / 5);
        return false;
    }
    //End Ugly Number

    //Word Subsets
    private int[] counter(String string) {

        int[] bucket = new int[26];
        for (char ch : string.toCharArray())
            bucket[ch - 'a']++;
        return bucket;
    }

    public List<String> wordSubsets(String[] A, String[] B) {
        int[] bucketb = new int[26], bucketa;
        for (String b : B) {
            int[] tmp = counter(b);
            for (int i = 0; i < 26; i++)
                bucketb[i] = bucketb[i] < tmp[i] ? tmp[i] : bucketb[i];
        }
        List<String> result = new ArrayList<>();
        for (String a : A) {
            bucketa = counter(a);
            int i = 0;
            for (; i < 26; i++) {
                if (bucketa[i] < bucketb[i])
                    break;
            }
            if (i == 26)
                result.add(a);
        }
        return result;
    }

    //End Word Subsets
    public boolean backspaceCompare(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sp = 0, tp = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '#') {
                s[sp > 0 ? --sp : 0] = '#';
            } else {
                s[sp++] = s[i];
            }
        }
        for (int i = 0; i < t.length; i++) {
            if (t[i] == '#') {
                t[tp > 0 ? --tp : 0] = '#';
            } else {
                t[tp++] = t[i];
            }
        }

        if (sp != tp) {
            return false;
        }
        for (int i = 0; i < sp; i++) {
            if (s[i] != t[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean canTransform(String start, String end) {
        int l = 0, r = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != 'X') {
                if (start.charAt(i) == 'R') {
                    r++;
                    l = 0;
                } else {
                    l--;
                    r = 0;
                }
            }

            if (end.charAt(i) != 'X') {
                if (end.charAt(i) == 'R') {
                    r--;
                    l = 0;
                } else {
                    l++;
                    r = 0;
                }
            }
            if(r<0||l<0){
                return false;
            }
        }
        if (l == 0 && r == 0) {
            return true;
        }
        return false;
    }
}