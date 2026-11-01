package collections;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        String text = "apple banana apple orange banana apple";
        for (String word : text.split(" ")) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        System.out.println("Map内容: " + map);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("one", 1);
        map2.put("two", 2);
        map2.put("three", 3);
        for (String key : map2.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map2.get(key));
        }
        // forEach + lambda表达式
        map2.forEach((key, value) -> {
            System.out.println(key + " = " + value);
        });
    }
}
