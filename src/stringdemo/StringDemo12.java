package stringdemo;

import java.util.Random;
import java.util.Scanner;

public class StringDemo12 {
    static void main() {
        char t;
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        char[] arr = str.toCharArray();

        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int rd = r.nextInt(0, arr.length);
            t = arr[i];
            arr[i] = arr[rd];
            arr[rd] = t;
        }
        String result = new String(arr);
        System.out.println(result);
    }

}
