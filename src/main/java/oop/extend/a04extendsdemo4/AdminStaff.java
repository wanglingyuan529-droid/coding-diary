package oop.extend.a04extendsdemo4;

public class AdminStaff extends Employee{
    public AdminStaff(String id, String name, String work) {
        super(id, name, work);
    }

    public AdminStaff() {
    }

    @Override
    public void work() {
        System.out.println("行政工作");
    }
}
