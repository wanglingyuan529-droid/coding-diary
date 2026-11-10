package streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        // 从一堆数字里跳出来3位数，取前2个
        List<Integer> numbers = List.of(1, 3, 222, 45455, 22, 1, 99, 111, 998);
        List<Integer> result = numbers.stream()
                .filter(num -> num >= 100 && num <= 999)
                .limit(2)
                .toList();
        System.out.println(result);
        // 从名字列表里筛出“姓张且两个字”的
        List<String> names = List.of("张三", "李四", "王五", "张小明", "赵六", "张大伟");
        List<String> filteredNames = names.stream()
                .filter(w -> w.startsWith("张"))
                .filter(w -> w.length() == 2)
                .toList();
        System.out.println(filteredNames);
        // 统计每个首字母有多少个词
        List<String> words = List.of("apple", "banana", "avocado", "blueberry", "cherry", "apricot");
        Map<Character, Long> letterCount = words.stream()
                .collect(Collectors.groupingBy(word -> word.charAt(0), Collectors.counting()));
        System.out.println(letterCount);
    }
}
