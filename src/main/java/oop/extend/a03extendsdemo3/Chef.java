package oop.extend.a03extendsdemo3;

public class Chef extends People{
    public Chef(String id, String name, int salary) {
        super(id, name, salary);
    }

    public Chef() {
    }

    @Override
    public void work() {
        System.out.println("Stir-fry");
    }
}
