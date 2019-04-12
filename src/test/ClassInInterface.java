package test;

public interface ClassInInterface {
    void howdy();

    class Test1 implements ClassInInterface {
        @Override
        public void howdy() {
            System.out.println("Howdy!");
        }

        public static void main(String[] args) {
            new Test1().howdy();
        }
    }
}
