package test;

public class Test /*implements ClassInInterface*/ {

    public static void main(String[] args) {
        new Thread(() -> System.out.println("Lambda Runnable in Thread")).start();
    }
}
