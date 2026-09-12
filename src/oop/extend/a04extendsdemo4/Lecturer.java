package oop.extend.a04extendsdemo4;

public class Lecturer extends Teacher{
    public Lecturer(String id, String name, String work) {
        super(id, name, work);
    }

    public Lecturer() {
    }

    @Override
    public void work() {
        System.out.println("讲课");
    }
}
