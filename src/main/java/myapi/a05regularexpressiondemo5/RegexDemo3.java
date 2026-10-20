package myapi.a05regularexpressiondemo5;

import java.util.Arrays;

/**
 * 正则表达式：替换与切割
 * <p>
 * 三个最容易搞混的地方：
 * 1. replace 是纯文本替换，replaceAll 才是正则
 * 2. split 的参数是正则，按点切要写 "\\."
 * 3. replaceAll 的替换内容里，$ 和 \ 是特殊字符
 */
public class RegexDemo3 {
    public static void main(String[] args) {
        System.out.println("===== 1. replace 和 replaceAll 不是一回事 =====");
        System.out.println("replace(\".\", \"-\")    -> " + "a.b".replace(".", "-"));     // a-b（纯文本）
        System.out.println("replaceAll(\".\", \"-\") -> " + "a.b".replaceAll(".", "-"));  // ---（. 是任意字符）
        System.out.println("replaceFirst(\".\", \"-\") -> " + "a.b.c".replaceFirst("\\.", "-")); // a-b.c

        System.out.println("\n===== 2. 用 $1 $2 引用分组，做格式重排 =====");
        System.out.println("日期 2024-10-19 -> " + "2024-10-19".replaceAll("(\\d{4})-(\\d{2})-(\\d{2})", "$3/$2/$1"));
        System.out.println("手机号打码      -> " + "13812345678".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        System.out.println("姓名去空格      -> " + "  张  三  ".replaceAll("\\s+", ""));

        System.out.println("\n===== 3. 替换内容里的 $ 是特殊字符 =====");
        // 想把 - 换成 $ 本身，直接写 "$" 会抛异常
        try {
            System.out.println("a-b".replaceAll("-", "$"));
        } catch (IllegalArgumentException e) {
            System.out.println("直接写 $ 会炸: " + e.getMessage());
        }
        System.out.println("正确写法 \\\\$ -> " + "a-b".replaceAll("-", "\\$"));

        System.out.println("\n===== 4. split 切割，参数也是正则 =====");
        System.out.println("a,b,,c 按逗号切      -> " + Arrays.toString("a,b,,c".split(",")));
        System.out.println("a,b,, 按逗号切（尾部空串丢弃）-> " + Arrays.toString("a,b,,".split(",")));
        System.out.println("a,b,, 用 limit=-1 保留空串   -> " + Arrays.toString("a,b,,".split(",", -1)));
        System.out.println("a,b,,c,d 用 limit=2          -> " + Arrays.toString("a,b,,c,d".split(",", 2)));
        System.out.println("a1b22c 按数字切              -> " + Arrays.toString("a1b22c".split("\\d+")));
        System.out.println("a.b.c 按点切（错了！）       -> " + Arrays.toString("a.b.c".split(".")));
        System.out.println("a.b.c 按点切（转义才对）     -> " + Arrays.toString("a.b.c".split("\\.")));

        System.out.println("\n===== 5. 贪婪 vs 懒惰 =====");
        String s = "\"x\" \"y\"";
        System.out.println("原文           : " + s);
        System.out.println("贪婪 \".*\"     -> " + s.replaceAll("\".*\"", "Q"));   // Q（一口气吃到底）
        System.out.println("懒惰 \".*?\"    -> " + s.replaceAll("\".*?\"", "Q"));  // Q Q（吃到最近的引号）

        System.out.println("\n===== 6. 断言：只判断位置，不消耗字符 =====");
        System.out.println("数字之间插逗号: " + "100 200 300".replaceAll("(?<=\\d)(?=\\d)", ","));
        System.out.println("元前面是数字就换: " + "100元 300元".replaceAll("(?<=\\d)元", "块"));
        System.out.println("元后面加空格   : [" + "100元200元".replaceAll("(?<=元)", " ") + "]");
        System.out.println("后面不是元的数字: " + "100元 200个 300个".replaceAll("\\d+(?!元)", "#"));

        System.out.println("\n===== 7. 用代码算替换结果（Java 9+） =====");
        String result = java.util.regex.Pattern.compile("(\\d+)")
                .matcher("苹果3个 梨5个")
                .replaceAll(mr -> String.valueOf(Integer.parseInt(mr.group()) * 10));
        System.out.println("数量乘 10: " + result);
    }
}
