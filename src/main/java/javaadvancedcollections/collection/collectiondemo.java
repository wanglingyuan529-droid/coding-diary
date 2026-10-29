package javaadvancedcollections.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class collectiondemo {
    public static void main(String[] args) {
        // 用Collection接住ArrayList
        Collection<String> c = new ArrayList<>();
        c.add("a");
        c.add("b");
        System.out.println(c + " size=" + c.size());

        // 用迭代器删偶数
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Iterator<Integer> it = nums.iterator();
        while (it.hasNext())
            if (it.next() % 2 == 0)
                it.remove();
        System.out.println(nums);

        // 复现ConcurrentModificationException
        List<Integer> boom = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        try {
            for (Integer n : boom)
                if (n % 2 == 0)
                    boom.remove(n);
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException caught!");
        }

        // removeIf代替
        List<Integer> n2 = new ArrayList<>(List.of(1, 2, 3, 4));
        n2.removeIf(x -> x % 2 == 0);
        System.out.println(n2);

        // 五种遍历方式任选其一
        List<String> v = List.of("w", "x", "y");
        v.forEach(s -> System.out.println(s + " "));

        // 写个泛型类
        class Box<T> {
            T v;

            Box(T t) {
                v = t;
            }

            T get() {
                return v;
            }
        }
        System.out.println(new Box<String>("hello").get());
    }
}
