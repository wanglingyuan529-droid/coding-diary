package stringdemo;

import java.util.Scanner;

public class StringDemo9 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String result = new StringBuilder().append(str).reverse().toString();
        if (str.equals(result)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
