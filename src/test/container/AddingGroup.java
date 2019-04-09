package test.container;

import java.util.*;

public class AddingGroup
{
    public static void main(String[] args)
    {
        Collection<Integer> collection = new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5)
        );
        Integer[] moreInts = {6, 7, 8, 9, 10};
        collection.addAll(Arrays.asList(moreInts));
        Collections.addAll(collection, 11, 12);
        for (Integer i : collection)
            System.out.println(i);
    }
}
