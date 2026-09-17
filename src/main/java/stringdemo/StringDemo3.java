package stringdemo;

import java.util.Scanner;

public class StringDemo3 {
    static void main() {
        String user_name = "yuan";
        String user_pass = "123456";
        for (int i = 2; i >= 0; i--) {
            System.out.println("input_username:");
            Scanner sc = new Scanner(System.in);
            String name = sc.next();
            System.out.println("input_userpass:");
            String pass = sc.next();
            if (user_name.equals(name) && user_pass.equals(pass)) {
                System.out.println("right");
                break;
            }
            else {
                System.out.println("no" + " " + i);
            }
        }
    }
}
