package oop.mystatic.a03staticdemo3;

import java.util.ArrayList;

public class Max_ageUitl {
    private Max_ageUitl() {

    }

    public static int maxAge(ArrayList<Student> list) {
        int max = list.get(0).getAge();
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i).getAge()) {
                max = list.get(i).getAge();
            }
        }
        return max;
    }
}
