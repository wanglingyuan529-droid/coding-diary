package stringdemo;

import java.util.Scanner;

public class StringDemo2 {
    static void main() {
        String s1 = new String("abc");
        String s2 = "abc";
        System.out.println(s1.equals(s2));

        boolean result1 = s1.equals(s2);
        System.out.println(result1);

        boolean result2 = s1.equalsIgnoreCase(s2);
        System.out.println(result2);

        Scanner sc = new Scanner(System.in);
        System.out.println("input String:");
        String str1 = sc.next();
        String str2 = "abc";
        System.out.println(str1.equals(str2));
    }
}