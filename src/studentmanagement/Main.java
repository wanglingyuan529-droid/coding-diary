package studentmanagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();

        loop: while (true) {
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作1登录 2注册 3忘记密码 4退出");
            String choose = sc.next();

            switch (choose) {
                case "1" -> {
                    if (login(users, sc)) {
                        manage(sc);
                    }
                }
                case "2" -> register(users, sc);
                case "3" -> forgetPassword(users, sc);
                case "4" -> {
                    System.out.println("再见");
                    break loop;
                }
                default -> System.out.println("输入有误，请重新选择");
            }
        }
    }

    public static void manage(Scanner sc) {
        ArrayList<Student> list = new ArrayList<>();
        loop: while (true) {
            System.out.println("---------Welcome to the HaiMa Student Management System---------");
            System.out.println("1: Add Student");
            System.out.println("2: Delete Student");
            System.out.println("3: Modify Student");
            System.out.println("4: Query Student");
            System.out.println("5: Exit");
            System.out.print("Please enter your choice:");
            String choose = sc.next();

            switch (choose) {
                case "1" -> AddStudent(list, sc);
                case "2" -> DeleteStudent(list, sc);
                case "3" -> ModifyStudent(list, sc);
                case "4" -> QueryStudent(list);
                case "5" -> {
                    System.out.println("Exit");
                    break loop;
                }
                default -> System.out.println("Error");
            }
        }
    }

    public static void register(ArrayList<User> users, Scanner sc) {
        String username;
        while (true) {
            System.out.println("请输入用户名：");
            username = sc.next();

            if (checkUsernameExists(users, username)) {
                System.out.println("用户名已存在，请重新输入");
                continue;
            }
            if (username.length() < 3 || username.length() > 15) {
                System.out.println("用户名长度必须在3~15位之间，请重新输入");
                continue;
            }
            if (!checkUsernameFormat(username)) {
                System.out.println("用户名只能是字母加数字的组合，且不能是纯数字，请重新输入");
                continue;
            }
            break;
        }

        String password;
        while (true) {
            System.out.println("请输入密码：");
            password = sc.next();
            System.out.println("请再次输入密码：");
            String again = sc.next();
            if (password.equals(again)) {
                break;
            }
            System.out.println("两次密码不一致，请重新输入");
        }

        String idCard;
        while (true) {
            System.out.println("请输入身份证号码：");
            idCard = sc.next();
            if (checkIdCard(idCard)) {
                break;
            }
            System.out.println("身份证号码不合法，请重新输入");
        }

        System.out.println("请输入手机号码：");
        String phone = sc.next();

        User u = new User(username, password, idCard, phone);
        users.add(u);
        System.out.println("注册成功！");
    }

    public static boolean login(ArrayList<User> users, Scanner sc) {
        System.out.println("请输入用户名：");
        String username = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                System.out.println("登录成功！欢迎进入学生管理系统。");
                return true;
            }
        }
        System.out.println("用户名或密码错误，登录失败。");
        return false;
    }

    public static void forgetPassword(ArrayList<User> users, Scanner sc) {
        System.out.println("请输入用户名：");
        String username = sc.next();

        User target = null;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                target = users.get(i);
                break;
            }
        }

        if (target == null) {
            System.out.println("用户名不存在，请先注册。");
            return;
        }

        System.out.println("请输入身份证号码验证身份：");
        String idCard = sc.next();
        if (!target.getIdCard().equals(idCard)) {
            System.out.println("身份证号码不匹配，无法修改密码。");
            return;
        }

        while (true) {
            System.out.println("请输入新密码：");
            String p1 = sc.next();
            System.out.println("请再次输入新密码：");
            String p2 = sc.next();
            if (p1.equals(p2)) {
                target.setPassword(p1);
                System.out.println("密码修改成功！");
                return;
            }
            System.out.println("两次密码不一致，请重新输入");
        }
    }

    public static boolean checkUsernameExists(ArrayList<User> users, String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkUsernameFormat(String username) {
        boolean allDigit = true;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (c < '0' || c > '9') {
                allDigit = false;
                break;
            }
        }
        if (allDigit) {
            return false;
        }

        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            boolean isDigit = c >= '0' && c <= '9';
            boolean isLetter = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
            if (!isDigit && !isLetter) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkIdCard(String idCard) {
        if (idCard.length() != 18) {
            return false;
        }
        if (idCard.charAt(0) == '0') {
            return false;
        }
        for (int i = 0; i < 17; i++) {
            char c = idCard.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static void AddStudent(ArrayList<Student> list, Scanner sc) {
        Student s = new Student();

        while (true) {
            System.out.println("Please enter student id(exit):");
            String id = sc.next();
            if (id.equals("exit")) {
                return;
            }

            boolean flag = contains(list, id);
            if (flag) {
                System.out.println("ID already exists. Please enter another ID.");
            } else {
                s.setId(id);
                break;
            }
        }

        System.out.println("Please enter student name:");
        String name = sc.next();
        s.setName(name);

        System.out.println("Please enter student age:");
        int age = sc.nextInt();
        s.setAge(age);

        System.out.println("Please enter student home:");
        String home = sc.next();
        s.setHome(home);

        list.add(s);
        System.out.println("Added successfully!");
    }

    public static void DeleteStudent(ArrayList<Student> list, Scanner sc) {
        System.out.println("Please enter the student ID to delete:");
        String id = sc.next();

        if (id.equals("exit")) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);

            if (stu.getId().equals(id)) {
                list.remove(i);
                System.out.println("Student deleted successfully!");
                return;
            }
        }

        System.out.println("Student ID does not exist.");
    }

    public static void ModifyStudent(ArrayList<Student> list, Scanner sc) {
        System.out.println("Please enter the student ID to modify:");
        String id = sc.next();

        if (id.equals("exit")) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);

            if (stu.getId().equals(id)) {
                System.out.println("Please enter new student name:");
                String name = sc.next();
                stu.setName(name);

                System.out.println("Please enter new student age:");
                int age = sc.nextInt();
                stu.setAge(age);

                System.out.println("Please enter new student home:");
                String home = sc.next();
                stu.setHome(home);

                System.out.println("Student modified successfully!");
                return;
            }
        }

        System.out.println("Student ID does not exist.");
    }

    public static void QueryStudent(ArrayList<Student> list) {
        System.out.println("Query Student");
        if (list.isEmpty()) {
            System.out.println("No student information available. Please add a student first.");
            return;
        }

        System.out.println("id\t\tname\t\tage\t\thome");
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu.getId() + "\t\t" + stu.getName() + "\t\t" + stu.getAge() + "\t\t" + stu.getHome());
        }
    }

    public static boolean contains(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            if (stu.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}