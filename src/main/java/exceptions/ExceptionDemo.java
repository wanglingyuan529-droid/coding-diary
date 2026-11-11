package exceptions;

public class ExceptionDemo {

    public static void check(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("不能为负");
        }
    }

    static class MyEx extends RuntimeException {
        MyEx(String m) {
            super(m);
        }
    }

    public static void main(String[] args) {
        try {
            @SuppressWarnings("unused")
            int x = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("除零: " + e.getMessage());
        }

        try {
            System.out.println("try");
        } finally {
            System.out.println("finally");
        }

        try {
            String s = null;
            s.length();
            System.out.println("不会执行");
        } catch (NullPointerException e) {
            System.out.println("NPE");
        } catch (Exception e) {
            System.out.println("兜底");
        }

        try {
            check(-1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            throw new MyEx("自定义");
        } catch (MyEx e) {
            System.out.println(e.getMessage());
        }
    }
}
