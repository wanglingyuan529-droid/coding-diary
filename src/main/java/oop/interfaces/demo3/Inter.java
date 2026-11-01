package oop.interfaces.demo3;

public interface Inter {
    default void method() {
        System.out.println("Inter interface default method");
    }
}
