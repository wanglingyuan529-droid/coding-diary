package myapi.a05regularexpressiondemo5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式：匹配与提取（Pattern + Matcher）
 * <p>
 * matches()  整串匹配
 * lookingAt() 从头开始匹配即可
 * find()      任意位置找，可以反复找
 * group(n)    取第 n 组括号里的内容，group(0) 是整段
 */
public class RegexDemo2 {
    public static void main(String[] args) {
        System.out.println("===== 1. 三个方法的区别 =====");
        Matcher m1 = Pattern.compile("\\d+").matcher("123abc456");
        System.out.println("matches()   : " + m1.matches());        // false，整串不是纯数字

        Matcher m2 = Pattern.compile("\\d+").matcher("123abc456");
        System.out.println("lookingAt() : " + m2.lookingAt() + " group=" + m2.group()); // true，group=123

        Matcher m3 = Pattern.compile("\\d+").matcher("123abc456");
        System.out.println("find() 第一次: " + m3.find() + " group=" + m3.group());  // 123
        System.out.println("find() 第二次: " + m3.find() + " group=" + m3.group());  // 456
        System.out.println("find() 第三次: " + m3.find());                          // false，没有了

        System.out.println("\n===== 2. 循环 find 找出所有数字 =====");
        Matcher m = Pattern.compile("\\d+").matcher("订单号 2024 金额 1280 元 数量 3 件");
        while (m.find()) {
            System.out.println("找到: " + m.group() + "，位置 " + m.start() + "~" + m.end());
        }

        System.out.println("\n===== 3. 分组：把括号里的内容分别取出来 =====");
        Matcher date = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})").matcher("日期 2024-10-19 结束");
        if (date.find()) {
            System.out.println("整段 group(0) : " + date.group(0));   // 2024-10-19
            System.out.println("年   group(1) : " + date.group(1));   // 2024
            System.out.println("月   group(2) : " + date.group(2));   // 10
            System.out.println("日   group(3) : " + date.group(3));   // 19
            System.out.println("分组数量      : " + date.groupCount()); // 3
        }

        System.out.println("\n===== 4. 命名分组：给括号起名字，好记 =====");
        Matcher named = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{2})").matcher("2024-10");
        if (named.find()) {
            System.out.println("year  = " + named.group("year"));    // 2024
            System.out.println("month = " + named.group("month"));   // 10
        }

        System.out.println("\n===== 5. 反向引用：找重复内容 =====");
        // (.) 捕获任意一个字符，\1 表示"跟第1组一模一样"
        System.out.println("aabb 是否有连续重复: " + Pattern.compile("(.)\\1").matcher("aabb").find());
        System.out.println("abcd 是否有连续重复: " + Pattern.compile("(.)\\1").matcher("abcd").find());

        System.out.println("\n===== 6. Matcher 复用：reset() 换字符串 =====");
        Matcher reuse = Pattern.compile("\\d+").matcher("");
        for (String text : new String[]{"a11", "bb222", "ccc3333"}) {
            reuse.reset(text);
            if (reuse.find()) {
                System.out.println(text + " -> " + reuse.group());
            }
        }
    }
}
