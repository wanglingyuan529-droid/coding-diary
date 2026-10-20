package oop.abstraction.a01abstractdemo1;

public class Sheep extends Animal{

    @Override
    void eat() {
        System.out.println(getName() + "吃草");
    }
}
