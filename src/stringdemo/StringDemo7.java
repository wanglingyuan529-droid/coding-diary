package stringdemo;

import java.util.Scanner;

public class StringDemo7 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String id = sc.next();
        String year = id.substring(6, 10);
        String month = id.substring(10, 12);
        String day = id.substring(12, 14);
        char gender = id.charAt(17);
        System.out.println(year + "year" + month + "month" + day + "day");
        if ((gender - '0') % 2 == 0) {
            System.out.println("gender: man");
        } else {
            System.out.println("gender: woman");
        }
    }
}
