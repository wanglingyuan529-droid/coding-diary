package oop.mystatic.a03staticdemo3;

import java.util.ArrayList;
import java.util.Scanner;

public class TestAge {
    static void main() {
        ArrayList<Student> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            Student s1 = new Student();
            System.out.println("请输入名字：");
            String name = sc.next();
            System.out.println("请输入年龄：");
            int age = sc.nextInt();
            System.out.println("请输入性别：");
            String gender = sc.next();
            s1.setName(name);
            s1.setAge(age);
            s1.setGender(gender);
            list.add(s1);
        }

        System.out.println("年龄最大的学生是:" + Max_ageUitl.maxAge(list) + "岁");
    }

}
