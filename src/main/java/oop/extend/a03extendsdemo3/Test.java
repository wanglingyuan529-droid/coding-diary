package oop.extend.a03extendsdemo3;

public class Test {
    static void main() {
        Manager m = new Manager("001", "manager", 10000, 800);
        System.out.println(m.getId() + ", " + m.getName() + ", " + m.getSalary() + ", " + m.getBonus());
        m.work();
        m.eat();

        Chef c = new Chef();
        c.setId("002");
        c.setName("chef");
        c.setSalary(7000);
        System.out.println(c.getId() + ", " + c.getName() + ", " + c.getSalary());
        c.work();
        c.eat();
    }
}
