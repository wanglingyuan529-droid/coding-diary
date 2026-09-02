package ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListDemo3 {
    static void main() {
        ArrayList<User> user = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            User u1 = new User();
            int id = sc.nextInt();
            String name = sc.next();
            String pass = sc.next();
            u1.setId(id);
            u1.setUsername(name);
            u1.setPassword(pass);
            user.add(u1);
        }
        System.out.println(index(user, 2));
        System.out.println(index(user, 3));
    }

    public static boolean index(ArrayList<User> user, int id) {
        for (int i = 0; i < user.size(); i++) {
            User u = user.get(i);
            if (u.getId() == id) {
                return true;
            }
        }
        return false;
    }

}
