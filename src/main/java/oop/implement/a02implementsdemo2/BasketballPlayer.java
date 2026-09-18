package oop.implement.a02implementsdemo2;

public class BasketballPlayer extends Athlete {

    public BasketballPlayer() {
        super();
    }

    public BasketballPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void study() {
        System.out.println("篮球运动员正在学打篮球");
    }
}