package set;

public class HashType extends SetType
{
    public HashType(int n)
    {
        super(n);
    }

    public int hashCode()
    {
        return i % 123;
    }
}
