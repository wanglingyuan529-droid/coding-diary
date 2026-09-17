package oop.polymorphism.a01polymorphismdemo1;

public class Test {
    static void main() {
        Student s = new Student();
        s.setName("zhangsan");
        s.setAge(18);

        Teacher t = new Teacher();
        t.setName("lisi");
        t.setAge(30);

        Administrator admin = new Administrator();
        admin.setName("Administrator");
        admin.setAge(35);

        register(s);
        register(t);
        register(admin);
    }

    public static void register(Person p) {
        p.show();
    }
}
