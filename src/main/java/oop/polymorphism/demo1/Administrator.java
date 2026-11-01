package oop.polymorphism.demo1;

public class Administrator extends Person{
    @Override
    public void show() {
        System.out.println("Administrator:" + getName() + ", " + getAge());
    }
}
