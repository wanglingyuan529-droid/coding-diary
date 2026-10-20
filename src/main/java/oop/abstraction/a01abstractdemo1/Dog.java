package oop.abstraction.a01abstractdemo1;

public class Dog extends Animal{

    @Override
    void eat() {
        System.out.println(getName() + "吃骨头");
    }
}
