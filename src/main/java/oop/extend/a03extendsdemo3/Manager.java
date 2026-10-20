package oop.extend.a03extendsdemo3;

public class Manager extends People {
    private double bonus;

    public Manager(double bonus) {
        this.bonus = bonus;
    }

    public Manager(String id, String name, int salary, double bonus) {
        super(id, name, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public void work() {
        System.out.println("management people;");
    }
}
