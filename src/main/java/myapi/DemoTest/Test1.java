package myapi.DemoTest;

import java.util.ArrayList;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ArrayList<Integer> numbers = new ArrayList<>();
            int sum = 0;
            System.out.println("Enter a number:");
            while (sum < 200) {
                int num = sc.nextInt();
                if (num >= 1 && num <= 100) {
                    numbers.add(num);
                    sum += num;
                } else {
                    System.out.println("Number out of range. Please enter a number between 1 and 100.");
                }
                
            }
            
            System.out.println("录入结束");
            System.out.println("集合内容：" + numbers);
            System.out.println("总和：" + sum);
        }

    }
}
