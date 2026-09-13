package oop.polymorphism.a01polymorphismdemo1;

public class Student extends Person{
    @Override
    public void show() {
        System.out.println("Student:" + getName() + ", " + getAge());
    }
}
