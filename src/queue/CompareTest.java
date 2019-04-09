package queue;

import java.util.ArrayList;
import java.util.Collections;

public class CompareTest
{

    static class Person implements Comparable<Person>
    {
        String name;
        int age;

        Person(String n, int a)
        {
            name = n;
            age = a;
        }

        @Override
        public String toString()
        {
            return String.format("{name=%s, age=%d}", name, age);
        }

        @Override
        public int compareTo(Person person)
        {
            return name.compareTo(person.name);
        }
    }

    public static void main(String[] args)
    {
        //test comparable
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(new Person("aaa", 10));
        list.add(new Person("bbb", 20));
        list.add(new Person("ccc", 30));
        list.add(new Person("ddd", 40));
        Collections.sort(list);
        System.out.println(list);


    }
}
