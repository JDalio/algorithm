package set;

import sun.reflect.generics.tree.Tree;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class TypesForSets
{
    static <T> Set<T> fill(Set<T> set, Class<T> type)
    {
        try
        {
            for (int i = 0; i < 10000; i++)
            {
                set.add(type.getConstructor(int.class).newInstance(i));
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return set;
    }

    static <T> void test(Set<T>set,Class<T>type)
    {
        fill(set,type);
        fill(set,type);
        fill(set,type);
        System.out.println(set);
    }

    public static void main(String[] args)
    {
//        test(new HashSet<HashType>(),HashType.class);
//        test(new LinkedHashSet<HashType>(),HashType.class);
        test(new TreeSet<>(),TreeType.class);
//        test(new HashSet<>(),SetType.class);
//        test(new HashSet<>(), TreeType.class);


    }
}
