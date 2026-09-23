package luogu_test;

import java.util.Scanner;

public class P1425 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int d = sc.nextInt();

            // 全部换算成分钟再相减，避免跨小时借位问题
            int start = a * 60 + b;
            int end = c * 60 + d;
            int total = end - start;

            System.out.println(total / 60 + " " + total % 60);
        }
    }
}
