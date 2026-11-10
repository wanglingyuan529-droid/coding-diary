package functiondemo;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceDemo {
    public static void main(String[] args) {
        // 静态方法引用
        List<String> nums = List.of("1", "2", "3", "4", "5");
        System.out.println(nums.stream().map(Integer::parseInt).toList());

        // 对象方法引用
        List.of("a", "b").forEach(System.out::println);

        // 类名::实例方法
        System.out.println(List.of("x", "y").stream().map(String::toUpperCase).toList());

        // 构造方法引用
        System.out.println(List.of("a", "b").stream().map(StringBuilder::new).toList());

        // 数组构造
        String[] arr = List.of("a", "b").stream().toArray(String[]::new);
        System.out.println(Arrays.toString(arr));

    }
}
