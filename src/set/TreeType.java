package set;

public class TreeType extends SetType implements Comparable<TreeType>
{
    public TreeType(int i)
    {
        super(i);
    }

    @Override
    public int compareTo(TreeType arg)
    {
        if (arg.i == i)
            return 0;
        if (arg.i > i)
            return -1;
        return 1;
    }
}
