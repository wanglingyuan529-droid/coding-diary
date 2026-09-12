package oop.extend.a04extendsdemo4;

public class Teacher extends Employee{
    public Teacher(String id, String name, String work) {
        super(id, name, work);
    }

    public Teacher() {
    }

    @Override
    public void work() {
        System.out.println("教学");
    }
}
