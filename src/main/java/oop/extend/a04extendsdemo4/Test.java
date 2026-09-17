package oop.extend.a04extendsdemo4;

public class Test {
    static void main() {
        Employee[] employees = {
            new Lecturer("E001", "张三", "讲课"),
            new Tutor("E002", "李四", "辅导学生"),
            new Maintainer("E003", "王五", "维护设备"),
            new Buyer("E004", "赵六", "采购物资")
        };

        for (Employee e : employees) {
            System.out.print(e.getName() + "：");
            e.work();
        }
    }
}
