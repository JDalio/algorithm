package test.container;

import java.util.ArrayList;
class TestObj extends Object
{
    public long id(){return -1;}
}
class Apple extends TestObj
{
    private static long counter;
    private final long id = counter++;

    public long id()
    {
        return id;
    }
}

class Orange
{
}

public class ApplesAndOrangesWithoutGenerics
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args)
    {
        TestObj apple=new Apple();
        System.out.println(apple.id());
//        ArrayList appels=new ArrayList();
//        for(int i=0;i<3;i++)
//            appels.add(new Apple());
//        appels.add(new Orange());
//        for (int i=0;i<appels.size();i++)
//        {
//            System.out.println(appels.get(i).getClass());
//        }
    }
}
