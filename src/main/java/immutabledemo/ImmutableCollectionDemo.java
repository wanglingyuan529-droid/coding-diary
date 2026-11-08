package immutabledemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImmutableCollectionDemo {
    public static void main(String[] args) {

        // ① 感受一下写操作全被禁
        List<String> list = List.of("a", "b");
        try {
            list.add("c");
        } catch (UnsupportedOperationException e) {
            System.out.println("add 被禁");
        }
        try {
            list.set(0, "z");
        } catch (UnsupportedOperationException e) {
            System.out.println("set 被禁");
        }

        // ② null 和重复的异常
        try {
            List.of("a", null);
        } catch (NullPointerException e) {
            System.out.println("null 被禁");
        }
        try {
            Set.of("a", "a");
        } catch (IllegalArgumentException e) {
            System.out.println("重复被禁");
        }

        // ③ 视图 vs 拷贝
        List<String> src = new ArrayList<>(List.of("a", "b"));
        List<String> view = Collections.unmodifiableList(src);
        List<String> copy = List.copyOf(src);
        src.add("c");
        System.out.println("view: " + view + "  ← 跟着变了");
        System.out.println("copy: " + copy + "  ← 没变");

        // ④ 转成可变副本
        List<String> mutable = new ArrayList<>(List.of("a", "b"));
        mutable.add("c");
        System.out.println("可变副本: " + mutable);

        // ⑤ 超过 10 对的 Map
        Map<String, Integer> big = Map.ofEntries(
                Map.entry("k1", 1), Map.entry("k2", 2), Map.entry("k3", 3),
                Map.entry("k4", 4), Map.entry("k5", 5), Map.entry("k6", 6),
                Map.entry("k7", 7), Map.entry("k8", 8), Map.entry("k9", 9),
                Map.entry("k10", 10), Map.entry("k11", 11));
        System.out.println("11 对: " + big.size());
    }
}
