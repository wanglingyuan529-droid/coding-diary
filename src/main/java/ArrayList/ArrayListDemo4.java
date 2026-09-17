package ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDemo4 {
    static void main() {
        ArrayList<Phone> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            Phone p1 = new Phone();

            String b = sc.next();
            int p = sc.nextInt();

            p1.setBrand(b);
            p1.setPrice(p);

            list.add(p1);
        }

        ArrayList<Phone> result = index_phone(list);

        for (int i = 0; i < result.size(); i++) {
            Phone p = result.get(i);
            System.out.println(p.getBrand() + "," + p.getPrice());
        }
    }

    public static ArrayList<Phone> index_phone(ArrayList<Phone> list) {
        ArrayList<Phone> r = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Phone p = list.get(i);

            if (p.getPrice() < 3000) {
                r.add(p);
            }
        }

        return r;
    }
}