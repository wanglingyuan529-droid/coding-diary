package oop.polymorphism.a02polymorphismdemo2;

public class Dog extends Animal {
    public Dog() {
    }

    public Dog(int age, String color) {
        super(age, color);
    }

    @Override
    public void eat(String something) {
        System.out.println(getAge() + "岁的" + getColor() + "颜色的狗正在吃" + something);
    }

    public void lookHome() {
        System.out.println("狗正在看家");
    }
}
