package oop.extend.a04extendsdemo4;

public class Maintainer extends AdminStaff{
    public Maintainer(String id, String name, String work) {
        super(id, name, work);
    }

    public Maintainer() {
    }

    @Override
    public void work() {
        System.out.println("维护设备");
    }
}
