package ArrayList;

import java.util.ArrayList;

public class ArrayListDemo1 {
    static void main() {
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("BBB");
        list.add("ccc");
        list.add("abcd");
        System.out.println(list);
        list.remove(2);
        System.out.println(list);
        String result = list.set(1, "aaa");
        System.out.println(result);
        String n = list.get(2);
        System.out.println(n);
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            System.out.println(str);
        }
    }
}
