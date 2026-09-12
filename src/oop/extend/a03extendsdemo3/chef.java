package oop.extend.a03extendsdemo3;

public class chef extends people{
    public chef(String id, String name, int salary) {
        super(id, name, salary);
    }

    public chef() {
    }

    @Override
    public void work() {
        System.out.println("Stir-fry");
    }
}
