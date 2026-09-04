package oop.mystatic.a01staticdemo1;

public class StudentTest {
    static void main() {
        Student s1 = new Student();
        s1.setName("Mr.zhang");
        s1.setAge(23);
        s1.setGender("man");
        Student.teacher_name = "mr.awei";

        s1.study();
        s1.show();

        Student s2 = new Student();
        s2.setName("Mr.wang");
        s2.setAge(22);
        s2.setGender("man");

        s2.study();
        s2.show();
    }

}
