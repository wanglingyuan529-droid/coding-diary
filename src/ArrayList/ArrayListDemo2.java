package ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDemo2 {
    static void main() {
        ArrayList<Student> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            Student s = new Student();
            String name = sc.next();
            int age = sc.nextInt();
            s.setName(name);
            s.setAge(age);
            list.add(s);
        }
        for (int i = 0; i < list.size(); i++) {
            Student s3 = list.get(i);
            System.out.println(s3.getName() + "," + s3.getAge());
        }

    }
}
