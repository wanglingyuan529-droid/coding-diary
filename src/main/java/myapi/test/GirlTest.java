package myapi.test;

import java.util.Arrays;

public class GirlTest {
    public static void main(String[] args) {
        GirlFinder girl1 = new GirlFinder("Alice", 20, 1.65);
        GirlFinder girl2 = new GirlFinder("Bob", 22, 1.75);
        GirlFinder girl3 = new GirlFinder("Charlie", 19, 1.60);

        GirlFinder[] girls = { girl1, girl2, girl3 };

        Arrays.sort(girls, (o1, o2) -> {
            double temp = o1.getAge() - o2.getAge();
            temp = temp == 0 ? o1.getHeight() - o2.getHeight() : temp;
            temp = temp == 0 ? o1.getName().compareTo(o2.getName()) : temp;
            if (temp > 0) {
                return 1;
            } else if (temp < 0) {
                return -1;
            } else {
                return 0;
            }
        });
        System.out.println(Arrays.toString(girls));
    }
}
