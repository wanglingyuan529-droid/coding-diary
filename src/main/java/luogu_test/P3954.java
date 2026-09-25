package luogu_test;

import java.util.Scanner;

public class P3954 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int a = sc.nextInt(); // 作业成绩 ×20%
            int b = sc.nextInt(); // 小测成绩 ×30%
            int c = sc.nextInt(); // 期末考试成绩 ×50%
            // 总成绩 = (a*20 + b*30 + c*50) / 100，通分约分后即 (a*2 + b*3 + c*5) / 10
            System.out.println((a * 2 + b * 3 + c * 5) / 10);
        }
    }
}
