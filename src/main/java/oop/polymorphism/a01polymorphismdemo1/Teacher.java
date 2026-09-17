package oop.polymorphism.a01polymorphismdemo1;

public class Teacher extends Person{
    public void show() {
        System.out.println("Teacher:" + getName() + ", " + getAge());
    }
}
