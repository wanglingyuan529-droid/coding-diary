package myapi.a07arraysdemo7;

import java.util.Arrays;

public class ArraysDemo {
    public static void main(String[] args) {

        int[] a = { 5, 2, 9, 1 };

        Arrays.toString(a); // "[5, 2, 9, 1]" 打印数组（不写就是 hashcode）
        Arrays.sort(a); // 原地升序 → [1, 2, 5, 9]
        Arrays.binarySearch(a, 5); // 2 必须先排序！
        Arrays.binarySearch(a, 7); // -4 负数 = 没找到
        Arrays.equals(new int[] { 1, 2 }, new int[] { 1, 2 }); // true
        Arrays.copyOf(a, 2); // "[1, 2]" 截取前 2 个
        Arrays.copyOf(a, 6); // "[1, 2, 5, 9, 0, 0]" 补 0
        Arrays.copyOfRange(a, 1, 3);

        int[][] mat = { { 1, 2 }, { 3, 4 } };
        Arrays.toString(mat); // "[[I@1b6d3586, [I@4554617c]" ← 一堆地址
        Arrays.deepToString(mat); // "[[1, 2], [3, 4]]" ✅
    }
}
