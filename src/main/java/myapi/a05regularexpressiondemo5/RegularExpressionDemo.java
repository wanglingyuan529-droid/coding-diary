package myapi.a05regularexpressiondemo5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式入门总览
 * <p>
 * 正则就做四件事：判断、查找、替换、切割。
 * Java 里有两个入口：
 * 1. String 自带的方法：matches / replaceAll / split（简单活）
 * 2. Pattern + Matcher（要提取内容、要反复用时用）
 */
public class RegularExpressionDemo {
    public static void main(String[] args) {
        System.out.println("===== ① 判断：这段文字符不符合规则 =====");
        System.out.println("13812345678 是手机号吗: " + "13812345678".matches("^1[3-9]\\d{9}$"));
        System.out.println("12812345678 是手机号吗: " + "12812345678".matches("^1[3-9]\\d{9}$"));
        System.out.println("abc123 是纯数字吗     : " + "abc123".matches("\\d+"));
        System.out.println("abc123 含有数字吗     : " + "abc123".matches(".*\\d+.*"));

        System.out.println("\n===== ② 查找：从一段话里找出内容 =====");
        // String 没有"找"的方法，要用 Pattern + Matcher
        Matcher m = Pattern.compile("\\d+").matcher("订单号 2024 金额 1280 元");
        while (m.find()) {
            System.out.println("找到数字: " + m.group() + "（位置 " + m.start() + "）");
        }

        System.out.println("\n===== ③ 替换：把符合规则的内容换掉 =====");
        System.out.println("日期重排: " + "2024-10-19".replaceAll("(\\d{4})-(\\d{2})-(\\d{2})", "$3/$2/$1"));
        System.out.println("手机打码: " + "13812345678".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        System.out.println("去空格  : " + "  张  三  ".replaceAll("\\s+", ""));

        System.out.println("\n===== ④ 切割：按规则把字符串切开 =====");
        String line = "Java,Python,Go,C++";
        for (String lang : line.split(",")) {
            System.out.println("语言: " + lang);
        }
        // 按连续空白切分
        for (String word : "Java   is    easy".split("\\s+")) {
            System.out.println("单词: " + word);
        }

        System.out.println("\n===== 小综合：校验 + 提取 =====");
        String text = "用户 13812345678 余额 1280.5 元";
        // 1. 先判断里面有没有手机号
        Matcher phone = Pattern.compile("1[3-9]\\d{9}").matcher(text);
        if (phone.find()) {
            System.out.println("提取到手机号: " + phone.group());
        }
        // 2. 再提取所有金额（带小数的数字）
        Matcher money = Pattern.compile("\\d+\\.?\\d*").matcher(text);
        while (money.find()) {
            System.out.println("提取到数字: " + money.group());
        }
    }
}
