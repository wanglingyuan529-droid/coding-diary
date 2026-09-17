package oop.extend.a04extendsdemo4;

public class Employee {
    private String id;
    private String name;
    private String work;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Employee(String id, String name, String work) {
        this.id = id;
        this.name = name;
        this.work = work;
    }

    public Employee() {
    }

    public void work() {
        System.out.println("员工工作");
    }
}
