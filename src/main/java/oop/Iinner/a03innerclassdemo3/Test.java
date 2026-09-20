package oop.Iinner.a03innerclassdemo3;

public class Test implements Swim {

    public static void main(String[] args) {
        new Swim() {
            @Override
            public void swim() {
                System.out.println("游泳");
            }
        }.swim();

        new Animal() {
            @Override
            public void eat() {
                System.out.println("吃东西");
            }
        }.eat();
    }

    public void swim() {
        System.out.println("Test 自己游泳");
    }
}