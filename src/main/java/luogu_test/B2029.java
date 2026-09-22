package luogu_test;

import java.util.Scanner;

public class B2029 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int h = sc.nextInt();
            int r = sc.nextInt();

            // 桶体积 = π r² h（立方厘米，即毫升），π 取 3.14
            // 20 升 = 20000 立方厘米
            // 分子分母同乘 100 转成整数运算，避免浮点误差导致向上取整出错
            long volumeX100 = 314L * r * r * h;   // π r² h × 100
            long needX100 = 20_000L * 100;        // 20000 立方厘米 × 100

            long buckets = (needX100 + volumeX100 - 1) / volumeX100;

            System.out.println(buckets);
        }
    }
}
