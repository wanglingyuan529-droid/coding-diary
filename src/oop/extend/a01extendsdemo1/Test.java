package oop.extend.a01extendsdemo1;

public class Test {
    static void main() {
        Ragdoll rd = new Ragdoll();
        rd.eat();
        rd.drink();
        rd.catchMouse();

        Husky h = new Husky();
        h.eat();
        h.drink();
        h.breakHome();
        h.lookHome();

        LiHua lh = new LiHua();
        lh.eat();
        lh.drink();
        lh.catchMouse();

        Teddy td = new Teddy();
        td.eat();
        td.drink();
        td.lookHome();
        td.touch();
    }
}
