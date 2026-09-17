package oop.extend.a04extendsdemo4;

public class Tutor extends Teacher{
    public Tutor(String id, String name, String work) {
        super(id, name, work);
    }

    public Tutor() {
    }

    @Override
    public void work() {
        System.out.println("辅导学生");
    }
}
