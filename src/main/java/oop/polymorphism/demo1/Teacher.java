package oop.polymorphism.demo1;

public class Teacher extends Person{
    public void show() {
        System.out.println("Teacher:" + getName() + ", " + getAge());
    }
}
