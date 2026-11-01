package oop.abstraction.demo1;

public class Dog extends Animal{

    @Override
    void eat() {
        System.out.println(getName() + "吃骨头");
    }
}
