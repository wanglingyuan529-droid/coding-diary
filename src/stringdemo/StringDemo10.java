package stringdemo;
import java.util.Scanner;

public class StringDemo10 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] roman = {
                "", "I", "II", "III", "IV",
                "V", "VI", "VII", "VIII", "IX"
        };
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int num = c - '0';
            result.append(roman[num]);
        }
        System.out.println(result);
    }
}