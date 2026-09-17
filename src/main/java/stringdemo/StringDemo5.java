package stringdemo;
import java.util.Scanner;

public class StringDemo5 {
    static void main() {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        int big = 0;
        int small = 0;
        int num = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                big++;
            } else if (c >= 'a' && c <= 'z') {
                small++;
            } else if (c >= '0' && c <= '9') {
                num++;
            }
        }

        System.out.println("大写字母：" + big);
        System.out.println("小写字母：" + small);
        System.out.println("数字：" + num);
    }
}