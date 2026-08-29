package oop;

/**
 * ============================================
 *  【测试入口】：Main —— 施工队
 *
 *  main 写法说明（JDK 25 两种全支持）：
 *    传统写法 public static void main(String[] args) —— 教材/考试/网上资料通用，推荐
 *    简化写法 static void main()                     —— JDK 21 起新增，可选
 * ============================================
 */
public class Main {
    public static void main(String[] args) {

        // ---------- 1. 最普通的用法：直接创建子类对象 ----------
        System.out.println("========== 1. 直接创建 Student ==========");
        Student s1 = new Student("Iris", 19, "2026001");
        s1.setScore(92.5);
        s1.study();
        s1.showInfo();   // 调用的是 Student 重写后的 showInfo

        // ---------- 2. 封装 + 继承联动 ----------
        System.out.println("\n========== 2. 封装演示（门卫拦人）==========");
        s1.setAge(200);   // 继承来的父类 setAge：打印"年龄输入不合法！"
        System.out.println("s1 的年龄仍然是：" + s1.getAge() + "（200 没进去）");

        // ---------- 3. 向上转型：父类引用 指向 子类对象 ----------
        System.out.println("\n========== 3. 向上转型 ==========");
        Person p = new Teacher("王老师", 35, "数学", 8000);
        p.showInfo();   // 多态！调的是 Teacher 重写后的版本

        // p.teach();
        // ↑ 把上面这行的注释去掉，编译就会红：
        //   "错误: 找不到符号 方法 teach()"
        // 原因：p 是 Person 类型，Person 的眼睛里只有 Person 的方法，
        // teach() 是 Teacher 特有的，先向下转型才能调：
        ((Teacher) p).teach();   // 强制向下转型（了解即可）

        // ---------- 4. 多态：一个数组装两种子类，统一调用 ----------
        System.out.println("\n========== 4. 多态演示 ==========");
        Person[] people = {
                new Student("张三", 18, "2026002"),
                new Teacher("李老师", 40, "语文", 9000)
        };
        for (Person person : people) {
            person.showInfo();   // 同一行代码，两种输出 — 多态
            System.out.println("----");
        }
    }
}