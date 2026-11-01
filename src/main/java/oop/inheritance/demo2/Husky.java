package oop.inheritance.demo2;

public class Husky extends Animal{

    @Override
    public void eat() {
        System.out.println("Eating dog food.");
    }

    public void wreckingHouse() {
        System.out.println("Wrecking the house.");
    }
}
