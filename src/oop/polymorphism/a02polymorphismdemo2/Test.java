package oop.polymorphism.a02polymorphismdemo2;

public class Test {
    static void main() {
        Person laoWang = new Person("老王", 30);
        Dog dog = new Dog(2, "黑");
        laoWang.keepPet(dog, "骨头");

        Person laoLi = new Person("老李", 25);
        Cat cat = new Cat(3, "灰");
        laoLi.keepPet(cat, "小鱼干");
    }
}
