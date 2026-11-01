package oop.polymorphism.demo1;

public class Student extends Person{
    @Override
    public void show() {
        System.out.println("Student:" + getName() + ", " + getAge());
    }
}
