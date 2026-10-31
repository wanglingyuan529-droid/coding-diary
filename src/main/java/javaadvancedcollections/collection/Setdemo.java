package javaadvancedcollections.collection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Setdemo {
    public static void main(String[] args) {
        Set<String> h = new HashSet<>();
        Set<String> l = new LinkedHashSet<>();
        Set<String> t = new TreeSet<>();
        for (String s : new String[] { "c", "a", "b" }) {
            h.add(s);
            l.add(s);
            t.add(s);
        }
        System.out.println("HashSet       : " + h);
        System.out.println("LinkedHashSet : " + l);
        System.out.println("TreeSet       : " + t);

        // ② 复现"只重写 equals 不去重"
        class A {
            String n;

            A(String n) {
                this.n = n;
            }

            public boolean equals(Object o) {
                return o instanceof A a && a.n.equals(n);
            }
        }
        Set<A> sa = new HashSet<>();
        sa.add(new A("x"));
        sa.add(new A("x"));
        System.out.println("只重写 equals 的 size = " + sa.size());

        // ③ 复现 TreeSet 丢数据
        class D implements Comparable<D> {
            String n;
            int a;

            D(String n, int a) {
                this.n = n;
                this.a = a;
            }

            public int compareTo(D o) {
                return this.a - o.a;
            }

            public String toString() {
                return n + a;
            }
        }
        TreeSet<D> td = new TreeSet<>();
        td.add(new D("甲", 3));
        td.add(new D("乙", 3));
        System.out.println("TreeSet 丢数据了: " + td);

        // ④ 用比较器排序
        TreeSet<String> byLen = new TreeSet<>(
                Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        byLen.addAll(List.of("banana", "apple", "kiwi", "fig"));
        System.out.println("按长度排: " + byLen);

    }
}
