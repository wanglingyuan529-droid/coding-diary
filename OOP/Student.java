package oop;

public class Student extends Person {

    // Student 特有的成员变量（Person 里没有的）
    private final String studentId;
    private double score;

    public Student(String name, int age, String studentId) {
        super(name, age);            // 调用父类构造器（必须第一行！）
        this.studentId = studentId;
        this.score = 0;
    }

    public void setScore(double score) {
        if (score >= 0 && score <= 100) {
            this.score = score;
        } else {
            System.out.println("分数范围必须0~100！");
        }
    }

    // ---------- Student 特有方法 ----------
    public void study() {
        // 注意！这里用的是 getName() 而不是 name：
        //   name 是父类的 private，子类也碰不到！只能走 getter 这个窗户。
        //   这就是"private 连亲儿子都不给碰"
        System.out.println(getName() + " 正在认真学习Java面向对象！");
    }

    @Override
    public void showInfo() {
        super.showInfo();            // 调父类方法（打印姓名年龄）
        System.out.println("学号：" + studentId);
        System.out.println("分数：" + score);
    }
}