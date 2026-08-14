public class OOP {
    // 成员变量（私有封装）
    private String name;
    private int age;
    private String studentId;
    private double score;

    // 无参构造
    public OOP() {
        System.out.println("执行无参构造方法，创建学生对象");
    }

    // 有参构造
    public OOP(String name, int age, String studentId) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
    }

    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age > 0 && age < 120) {
            this.age = age;
        } else {
            System.out.println("年龄输入不合法！");
        }
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        if (score >= 0 && score <= 100) {
            this.score = score;
        } else {
            System.out.println("分数范围必须0~100！");
        }
    }

    // 对象行为方法
    public void study() {
        System.out.println(name + " 正在认真学习Java面向对象！");
    }

    public void showInfo() {
        System.out.println("========学生信息========");
        System.out.println("姓名：" + name);
        System.out.println("年龄：" + age);
        System.out.println("学号：" + studentId);
        System.out.println("分数：" + score);
    }

    // Java25 main方法，消除多余public警告；调用getter消除未使用警告
    static void main(String[] args) {
        OOP s1 = new OOP("Iris", 19, "2026001");
        s1.setScore(92.5);
        s1.study();
        s1.showInfo();

        System.out.println("【测试Getter】姓名：" + s1.getName());
        System.out.println("【测试Getter】分数：" + s1.getScore());

        System.out.println("\n");

        OOP s2 = new OOP();
        s2.setName("张三");
        s2.setAge(18);
        s2.setStudentId("2026002");
        s2.setScore(88);
        s2.showInfo();

        // 非法数据测试
        s2.setAge(200);
        s2.setScore(120);

        // 消除 args未使用警告
        if (args.length > 0) {
            System.out.println("命令行参数：" + args[0]);
        }
    }
}
