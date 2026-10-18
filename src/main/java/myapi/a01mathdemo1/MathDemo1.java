package myapi.a01mathdemo1;

public class MathDemo1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("isPrime(133333) = " + isPrime(133333));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start) + " ms");
    }


    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
