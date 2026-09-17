package oop.abstrac.a01stractdemo1;

public class Test {
    static void main() {
        Frog f = new Frog();
        f.setName("青蛙");
        f.setAge(2);
        f.eat();
        f.drink();
        Dog d = new Dog();
        d.setAge(2);
        d.setName("大黄");
        d.eat();
        d.drink();
        Sheep s = new Sheep();
        s.setAge(3);
        s.setName("喜羊羊");
        s.eat();
        s.drink();
    }
}
