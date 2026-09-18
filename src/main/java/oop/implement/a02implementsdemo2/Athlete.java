package oop.implement.a02implementsdemo2;

public abstract class Athlete extends People {

    public Athlete() {
        super();
    }

    public Athlete(String name, int age) {
        super(name, age);
    }

    public abstract void study();
}