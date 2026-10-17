package MathapI.a01mathdemo1;

public class MathDemo3 {
    public static void main(String[] args) {
        System.out.println("水仙花数的个数为：" + Narcissistic_number());
        System.out.println("证明没有两位的自幂数：" + Narcissistic_number_no());
        System.out.println("四位自幂数的个数为：" + Narcissistic_number_4());
        System.out.println("五位自幂数的个数为：" + Narcissistic_number_5());
    }

    public static int Narcissistic_number() {
        int count = 0;
        for (int i = 100; i < 1000; i++) {
            int a = i % 10;
            int b = i / 10 % 10;
            int c = i / 100;
            if ((a * a * a + b * b * b + c * c * c) == i) {
                count += 1;
            }
        }
        return count;
    }


    public static boolean Narcissistic_number_no() {
        for (int i = 10; i < 100; i++) {
            int a = i % 10;
            int b = i / 10;
            if (a * a + b * b == i) {
                return false;   // 找到一个反例 → 「没有两位自幂数」被推翻
            }
        }
        return true;            // 一个都没找到 → 「没有两位自幂数」成立
    }

    
    public static int Narcissistic_number_4() {
        int count = 0;
        for (int i = 1000; i < 10000; i++) {
            int a = i % 10;
            int b = i / 10 % 10;
            int c = i / 100 % 10;
            int d = i / 1000;
            if ((a * a * a * a + b * b * b * b + c * c * c * c + d * d * d * d) == i) {
                count += 1;
            }
        }
        return count;
    }

    public static int Narcissistic_number_5() {
        int count = 0;
        for (int i = 10000; i < 100000; i++) {
            int a = i % 10;
            int b = i / 10 % 10;
            int c = i / 100 % 10;
            int d = i / 1000 % 10;
            int e = i / 10000;
            if ((a * a * a * a * a + b * b * b * b * b + c * c * c * c * c
                + d * d * d * d * d + e * e * e * e * e) == i) {
                count += 1;
            }
        }
        return count;
    }
}
