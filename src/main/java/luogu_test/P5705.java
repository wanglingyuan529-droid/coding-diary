package luogu_test;

import java.util.Scanner;

public class P5705 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int s = sc.nextInt();
            int v = sc.nextInt();

            int time = (s + v - 1) / v;

            time += 10;

            int total = 8 * 60 - time;

            if (total < 0) {
                total += 24 * 60;
            }

            int hour = total / 60;
            int minute = total % 60;

            System.out.printf("%02d:%02d", hour, minute);
        }
    }
}

