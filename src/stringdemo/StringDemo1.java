package stringdemo;

public class StringDemo1 {
    static void main() {
        String s1 = "abc";
        System.out.println(s1);


        String s2 = new String();
        System.out.println("@" + s2 + "!");

        char[] chs = {'a', 'b', 'c', 'd'};
        String s4 = new String(chs);
        System.out.println(s4);

        byte[] bytes = {97, 98, 99, 100};
        String s5 = new String(bytes);
        System.out.println(s5);
    }
}