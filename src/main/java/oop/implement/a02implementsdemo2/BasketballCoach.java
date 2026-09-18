package oop.implement.a02implementsdemo2;

public class BasketballCoach extends Coach {

    public BasketballCoach() {
        super();
    }

    public BasketballCoach(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("篮球教练正在教打篮球");
    }
}