package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * HashMap 练习：自定义对象作 key、按 entrySet 求最大值、按 value 排序。
 * <p>
 * 三个要点：
 * 1. 用对象作 key，必须同时重写 equals 和 hashCode；只重写一个会导致 get() 取不到值。
 * 2. HashMap 无序，想找最大值只能遍历 entrySet 比较。
 * 3. 想按 value 排序，得先把 entrySet 转成 List 再 sort（Map 本身没有顺序）。
 * <p>
 * 基础的 put/get/forEach 用法见同包的 {@link MapDemo}。
 */
public class HashMapDemo {

    public static void main(String[] args) {
        demoCustomKey();
        demoFindMax();
        demoSortedByValue();
    }

    /** ① 自定义对象作 key：重写 equals/hashCode 后，内容相同的不同实例也能命中同一个 key。 */
    private static void demoCustomKey() {
        Map<Student, String> map = new HashMap<>();
        map.put(new Student("Alice", 20), "A");
        // 这里 new 的是另一个对象，能取到 "A" 全靠 equals/hashCode
        System.out.println("① 按内容取 key 的值: " + map.get(new Student("Alice", 20)));
    }

    /** ② 遍历 entrySet 找分数最高的学生。 */
    private static void demoFindMax() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("张三", 50);
        scores.put("李四", 90);
        scores.put("王五", 70);

        Map.Entry<String, Integer> top = null;
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (top == null || entry.getValue() > top.getValue()) {
                top = entry;
            }
        }
        System.out.println("② 最高分学生: " + top.getKey() + "，分数: " + top.getValue());
    }

    /** ③ 按分数降序排队，分数相同时按姓名升序。 */
    private static void demoSortedByValue() {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("张三", 50);
        scores.put("李四", 90);
        scores.put("王五", 70);

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(scores.entrySet());
        entries.sort((a, b) -> {
            int byValue = b.getValue().compareTo(a.getValue());     // 降序
            return byValue != 0 ? byValue : a.getKey().compareTo(b.getKey());
        });
        System.out.println("③ 按分数降序: " + entries);
    }

    /** 作为 Map key 的实体类：equals 与 hashCode 必须成对重写。 */
    private static final class Student {

        private final String name;
        private final int age;

        Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Student other)) {
                return false;
            }
            return age == other.age && Objects.equals(name, other.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', age=" + age + "}";
        }
    }
}
