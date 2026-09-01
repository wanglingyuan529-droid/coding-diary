package oop;

public class Person {

    // ---------- 成员变量（private = 封装：锁在家里，外面碰不到）----------
    private final String name;
    private int age;

    // ---------- 构造方法：创建对象时执行，负责初始化 ----------
    public Person(String name, int age) {
        this.name = name;   // this = "我自己的"成员变量，避免和参数 name 混淆
        this.age = age;
    }

    // ---------- Getter & Setter（类对外的"窗户"）----------
    public String getName() {
        return name;        // get = 把锁在 private 里的值"递出去"
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // 门卫：和你自己的 OOP.java 里的 setAge 一模一样
        if (age > 0 && age < 120) {
            this.age = age;
        } else {
            System.out.println("年龄输入不合法！");
        }
    }

    // ---------- 公共行为方法 ----------
    // 子类会【重写】这个方法（加上 @Override），改成自己的版本
    public void showInfo() {
        System.out.println("姓名：" + name);
        System.out.println("年龄：" + age);
    }
}