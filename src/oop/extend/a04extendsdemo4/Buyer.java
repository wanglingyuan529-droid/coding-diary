package oop.extend.a04extendsdemo4;

public class Buyer extends AdminStaff{
    public Buyer(String id, String name, String work) {
        super(id, name, work);
    }

    public Buyer() {
    }

    @Override
    public void work() {
        System.out.println("采购物资");
    }
}
