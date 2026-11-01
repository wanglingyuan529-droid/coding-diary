package jdkapi.a08lambda;

import java.util.Arrays;

public class LambdaStringSortDemo {
    public static void main(String[] args) {
        String[] arr = { "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa" };
        Arrays.sort(arr, (o1, o2) -> o1.length() - o2.length());
        System.out.println(Arrays.toString(arr));
    }
}
