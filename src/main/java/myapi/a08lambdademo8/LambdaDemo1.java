package myapi.a08lambdademo8;

public class LambdaDemo1 {
    public static void main(String[] args) {
        method(() -> {
            System.out.println("游泳");
        });
    }

    public static void method(Swim s) {
        s.swimming();
    }

    interface Swim {
        void swimming();
    }
}
