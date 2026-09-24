package luogu_test;

import java.util.Scanner;

public class P1421 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int money = a * 10 + b;
            System.out.println(money / 19);
        }
    }
}
