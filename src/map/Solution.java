package map;

import java.util.HashMap;
import java.util.Map;

public class Solution
{
    //Word Pattern
    public boolean wordPattern(String pattern, String str)
    {
        String[] strs = str.split(" ");
        for(String s: strs)
            System.out.print(s+" ");
        System.out.println("\n"+strs.length+" "+pattern.length());

        if (pattern.length() != strs.length)
            return false;
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++)
        {
            Character key = pattern.charAt(i);
            if (!map.containsKey(key))
                map.put(key, strs[i]);
            else if (map.get(key) != strs[i])
                return false;
        }
        return true;
    }
    //End Word Pattern
}
