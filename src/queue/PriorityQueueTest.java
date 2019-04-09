package queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueTest
{
    static class Student
    {
        private int id;
        private String name;

        public Student(int id, String name)
        {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString()
        {
            return id + "-" + name;
        }
    }

    public static void main(String[] args)
    {
        //base use
        Queue<Integer> queue1 = new PriorityQueue<>();
        queue1.add(2);
        queue1.add(1);
        queue1.add(3);
        while (!queue1.isEmpty())
        {
            System.out.println(queue1.poll());
        }

        //custom
        Comparator<Student> comparator = new Comparator<Student>()
        {
            @Override
            public int compare(Student s1, Student s2)
            {
                return s1.id - s2.id;
            }
        };

        Queue<Student> queue2 = new PriorityQueue<>(comparator);
        queue2.add(new Student(2,"B"));
        queue2.add(new Student(-1,"A"));
        queue2.add(new Student(3,"C"));

        while (!queue2.isEmpty())
        {
            System.out.println(queue2.poll());
        }

    }
}
