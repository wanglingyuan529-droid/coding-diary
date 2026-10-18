package myapi.a01mathdemo1;

import java.util.Scanner;

public class MathDemo2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = n % 10;
        int b = n / 10 % 10;
        int c = n / 100;
        if ((a ^ 3 + b ^ 3 + c ^ 3) == n) {
            System.out.println("是水仙花数");
        } else {
            System.out.println("不是水仙花数");
        }
        sc.close();
        

    }
}
