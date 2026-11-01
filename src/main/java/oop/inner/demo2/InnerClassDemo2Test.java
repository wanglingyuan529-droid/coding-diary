package oop.inner.demo2;

public class InnerClassDemo2Test {
    public static void main(String[] args) {
        Outer.Inner oi = new Outer().new Inner();
        oi.show();
    }
}
