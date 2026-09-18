package oop.implement.a01implementsdemo1;

public class Rabbit extends Animal{
    public Rabbit(String name, int age) {
        super(name, age);
    }

    public Rabbit() {
    }

    @Override 
    public void eat() {
        System.out.println("兔子吃胡萝卜");
    }
}
