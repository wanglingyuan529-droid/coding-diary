package stringdemo;

import java.util.Scanner;

public class StringDemo04 {
    static void main() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("input_String:");
            String str = sc.next();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                System.out.println(c);
            }
        }
    }
}
