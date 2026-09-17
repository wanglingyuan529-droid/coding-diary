package oop.mystatic.a01staticdemo1;

public class Student {
    private String name;
    private int age;
    private String gender;
    public static String teacher_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Student() {
    }

    public void study() {
        System.out.println(name + "Learning");
    }

    public void show() {
        System.out.println(name + "," + age + "," + gender + "," + teacher_name);
    }
}
