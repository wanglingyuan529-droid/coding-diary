package oop.extend.a02extendsdemo2;

public class Test {
    static void main() {
        Husky hs = new Husky();
        hs.eat();
        hs.drink();
        hs.home();
        hs.wreckingHouse();
        System.out.println("-------------");
        SharPei sp = new SharPei();
        sp.eat();
        sp.drink();
        sp.home();
        System.out.println("-------------");
        ChineseRuralDog crl = new ChineseRuralDog();
        crl.eat();
        crl.drink();
        crl.home();
    }
}
