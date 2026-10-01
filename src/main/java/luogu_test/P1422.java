package luogu_test;

import java.util.Scanner;

public class P1422 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt(); // 用电总计（千瓦时）
            double fee;
            if (n <= 150) {
                // 150 千瓦时及以下：0.4463 元/千瓦时
                fee = n * 0.4463;
            } else if (n <= 400) {
                // 151~400 千瓦时部分：0.4663 元/千瓦时
                fee = 150 * 0.4463 + (n - 150) * 0.4663;
            } else {
                // 401 千瓦时及以上部分：0.5663 元/千瓦时
                fee = 150 * 0.4463 + 250 * 0.4663 + (n - 400) * 0.5663;
            }
            System.out.printf("%.1f%n", fee); // 保留 1 位小数
        }
    }
}
