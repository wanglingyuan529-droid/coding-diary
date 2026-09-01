package oop;

public class Teacher extends Person {

    private final String subject;   // 教的科目
    private final double salary;    // 工资

    public Teacher(String name, int age, String subject, double salary) {
        super(name, age);              // 铁律：第一行调父类构造器
        this.subject = subject;
        this.salary = salary;
    }

    // Teacher 特有方法
    public void teach() {
        System.out.println(getName() + " 正在教" + subject + "课！");
    }

    // 同样重写 showInfo，改成老师的版本
    @Override
    public void showInfo() {
        super.showInfo();              // 先打印公共部分（姓名年龄）
        System.out.println("科目：" + subject);
        System.out.println("工资：" + salary);
    }
}