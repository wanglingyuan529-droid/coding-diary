package oop.implement.a02implementsdemo2;

public abstract class Coach extends People {

    public Coach() {
        super();
    }

    public Coach(String name, int age) {
        super(name, age);
    }

    public abstract void teach();
}