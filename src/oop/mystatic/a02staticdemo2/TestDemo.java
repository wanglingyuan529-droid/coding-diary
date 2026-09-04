package oop.mystatic.a02staticdemo2;

public class TestDemo {
    static void main() {
        int[] arr1 = {1, 2, 3, 4, 5};
        String str = ArrayUtil.printArr(arr1);
        System.out.println(str);

        double[] arr2 = {2.2, 3.3, 4.4, 5.5};
        Double db = ArrayUtil.getAerage(arr2);
        System.out.println(db);

    }
}
