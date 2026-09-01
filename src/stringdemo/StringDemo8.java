package stringdemo;

public class StringDemo8 {
    static void main() {
        StringBuilder sb = new StringBuilder("abc");
        sb.append(1).append(2.3).append(true);
        sb.reverse();
        System.out.println(sb);
    }
}
