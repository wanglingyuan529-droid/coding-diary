package myapi.a05regularexpressiondemo5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式：常用场景实战（直接抄，改改就能用）
 * <p>
 * 提醒：正则只管"格式"，不管"业务合法性"。
 * 比如 2024-02-30 用日期正则可以过，因为正则不知道 2 月没有 30 号。
 */
public class RegexDemo4 {
    public static void main(String[] args) {
        System.out.println("===== 1. 手机号：^1[3-9]\\d{9}$ =====");
        String phone = "^1[3-9]\\d{9}$";
        String[] phones = {"13812345678", "12812345678", "1381234567", "138123456789", "1381234567a"};
        for (String p : phones) {
            System.out.println("  " + p + " -> " + p.matches(phone));
        }

        System.out.println("\n===== 2. 邮箱（简化版） =====");
        String mail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String[] mails = {"a@b.com", "a.b+c@mail.co.uk", "a@b", "a@@b.com", "@b.com"};
        for (String s : mails) {
            System.out.println("  " + s + " -> " + s.matches(mail));
        }

        System.out.println("\n===== 3. 日期格式（注意 2 月 30 号能过） =====");
        String date = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        String[] dates = {"2024-10-19", "2024-13-01", "2024-00-10", "2024-02-30", "2024-1-1"};
        for (String d : dates) {
            System.out.println("  " + d + " -> " + d.matches(date));
        }
        System.out.println("  ↑ 2024-02-30 也 true：正则不校验月份天数，真校验要用 LocalDate.parse()");

        System.out.println("\n===== 4. 提取一段文本里的所有数字 =====");
        Matcher num = Pattern.compile("\\d+").matcher("订单号 2024，金额 1280 元，数量 3 件，优惠 0.5");
        while (num.find()) {
            System.out.println("  数字: " + num.group());
        }

        System.out.println("\n===== 5. 提取带小数的数字 =====");
        Matcher dec = Pattern.compile("\\d+\\.?\\d*").matcher("价格 19.99 元，重量 3.5 kg，数量 10");
        while (dec.find()) {
            System.out.println("  数字: " + dec.group());
        }

        System.out.println("\n===== 6. 从乱文本里提取所有邮箱 =====");
        Matcher emails = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
                .matcher("联系 a@b.com 或 c.d@mail.co.uk，也可以找 xyz@qq.com");
        while (emails.find()) {
            System.out.println("  邮箱: " + emails.group());
        }

        System.out.println("\n===== 7. 用户名规则：字母开头，6~16 位字母数字下划线 =====");
        String username = "^[A-Za-z]\\w{5,15}$";
        String[] users = {"zhangsan", "zhangsan123", "1zhangsan", "abc", "a_b_c_d_e_f_g"};
        for (String u : users) {
            System.out.println("  " + u + " -> " + u.matches(username));
        }

        System.out.println("\n===== 8. 分割：把一句话拆成单词 =====");
        String sentence = "Java  is   easy   to   learn";
        for (String word : sentence.split("\\s+")) {
            System.out.println("  [" + word + "]");
        }

        System.out.println("\n===== 9. 提取中文 =====");
        String cn = "abc中文123汉字456";
        Matcher zh = Pattern.compile("[\\u4e00-\\u9fa5]+").matcher(cn);
        while (zh.find()) {
            System.out.println("  中文: " + zh.group());
        }

        // ===== 10. 安全提醒（只讲不跑）=====
        // 不要写嵌套量词：(a+)+b、(.*){10} 这种会让匹配时间指数级爆炸（灾难性回溯）
        // 用户传长字符串能把程序卡死。换成精确写法：a+b、.{10}
        System.out.println("\n===== 10. 性能提醒 =====");
        System.out.println("  循环里反复用同一个正则，提前 Pattern.compile() 一次，别每次写字符串");
        System.out.println("  短正则单次调用差异很小，别过度优化");
    }
}
