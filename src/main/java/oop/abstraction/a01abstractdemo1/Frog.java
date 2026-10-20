package oop.abstraction.a01abstractdemo1;

public class Frog extends Animal{

    @Override
    void eat() {
        System.out.println(getName() + "吃虫子");
    }
}
