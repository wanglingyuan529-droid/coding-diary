package oop.abstrac.a01stractdemo1;

public class Sheep extends Animal{

    @Override
    void eat() {
        System.out.println(getName() + "吃草");
    }
}
