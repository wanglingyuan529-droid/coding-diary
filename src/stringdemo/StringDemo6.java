package stringdemo;

import java.util.Scanner;

public class StringDemo6 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String phone = sc.next();
        String start = phone.substring(0, 3);
        String end = phone.substring(7);
        String result = start + "****" + end;
        System.out.println(result);

    }
}
