package oop.implement.a01implementsdemo1;

public class Test {
    public static void main(String[] args) {
        Frog frog = new Frog("小青蛙", 2);
        System.out.println(frog.getName() + "," + frog.getAge());

        frog.eat();
        frog.swim();
        
        Rabbit rabbit = new Rabbit("小兔子", 1);
        System.out.println(rabbit.getName() + "," + rabbit.getAge());
        rabbit.eat();
    }
}
