package luogu_test;

import java.util.Scanner;

public class B2029 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int h = sc.nextInt();
            int r = sc.nextInt();

            long volumeX100 = 314L * r * r * h;  
            long needX100 = 20_000L * 100;  

            long buckets = (needX100 + volumeX100 - 1) / volumeX100;

            System.out.println(buckets);
        }
    }
}
