package oop.implement.a01implementsdemo1;

public class Frog extends Animal implements Swim{
    public Frog(String name, int age) {
        super(name, age);
    }

    public Frog() {
    }

    @Override 
    public void eat() {
        System.out.println("青蛙吃虫子");
    }


    @Override
    public void swim() {
        System.out.println("青蛙会游泳");
    }

}
