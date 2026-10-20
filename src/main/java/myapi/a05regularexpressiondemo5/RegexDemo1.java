package myapi.a05regularexpressiondemo5;

/**
 * 正则表达式：基础语法速成
 * <p>
 * 记住三个坑：
 * 1. 正则写在 Java 字符串里，反斜杠要双写：\\d 而不是 \d
 * 2. matches() 是整串匹配，不是"包含"
 * 3. replaceAll / split 的参数都是正则，不是纯文本
 */
public class RegexDemo1 {
    public static void main(String[] args) {
        System.out.println("===== 1. 字符类：单个字符怎么表示 =====");
        System.out.println("[abc]+ 匹配 abcabc : " + "abcabc".matches("[abc]+"));
        System.out.println("[^abc]+ 匹配 xyz  : " + "xyz".matches("[^abc]+"));
        System.out.println("[a-z]+ 匹配 abc   : " + "abc".matches("[a-z]+"));
        System.out.println("[0-9]+ 匹配 123   : " + "123".matches("[0-9]+"));
        System.out.println("\\d+ 匹配 123      : " + "123".matches("\\d+"));
        System.out.println("\\w+ 匹配 abc_123  : " + "abc_123".matches("\\w+"));
        System.out.println("\\s+ 匹配 空格     : " + " ".matches("\\s+"));
        // 大写的都是取反
        System.out.println("\\D+ 匹配 abc      : " + "abc".matches("\\D+"));
        System.out.println("\\W+ 匹配 !@#      : " + "!@#".matches("\\W+"));

        System.out.println("\n===== 2. 量词：出现几次 =====");
        System.out.println("ab* 匹配 a        : " + "a".matches("ab*"));       // * 0次或多次
        System.out.println("ab* 匹配 abbb     : " + "abbb".matches("ab*"));
        System.out.println("ab+ 匹配 a        : " + "a".matches("ab+"));       // + 至少1次
        System.out.println("ab? 匹配 a        : " + "a".matches("ab?"));       // ? 0或1次
        System.out.println("\\d{3} 匹配 123   : " + "123".matches("\\d{3}"));  // 正好3次
        System.out.println("\\d{3} 匹配 1234  : " + "1234".matches("\\d{3}")); // false，整串匹配
        System.out.println("\\d{2,4} 匹配 123 : " + "123".matches("\\d{2,4}"));
        System.out.println("\\d{3,} 匹配 1234 : " + "1234".matches("\\d{3,}"));

        System.out.println("\n===== 3. 边界：^ 开头、$ 结尾 =====");
        System.out.println("\\d+ 匹配 123abc   : " + "123abc".matches("\\d+"));      // false
        System.out.println("\\d+ 匹配 a123     : " + "a123".matches("\\d+"));
        System.out.println("^\\d+ 匹配 123abc  : " + "123abc".matches("^\\d+"));
        System.out.println("\\d+$ 匹配 a123    : " + "a123".matches("\\d+$"));
        System.out.println("^\\d+$ 匹配 123    : " + "123".matches("^\\d+$"));

        System.out.println("\n===== 4. 正则里的特殊字符要转义 =====");
        // 想匹配真正的点：正则里写 \. ，Java 字符串里要写成 \\. （两个反斜杠）
        System.out.println("3.14 匹配 3\\.14  : " + "3.14".matches("3\\.14"));
        System.out.println("3X14 匹配 3\\.14  : " + "3X14".matches("3\\.14"));  // false
        // 不转义：3.14 里的 . 是"任意字符"，所以 3X14 也能匹配
        System.out.println("3X14 匹配 3.14    : " + "3X14".matches("3.14"));    // true！坑

        System.out.println("\n===== 5. 大小写和换行的小开关 =====");
        System.out.println("Abc 匹配 abc          : " + "Abc".matches("abc"));
        System.out.println("Abc 匹配 (?i)abc      : " + "Abc".matches("(?i)abc"));  // 忽略大小写
        System.out.println("a换行b 匹配 a.b        : " + "a\nb".matches("a.b"));
        System.out.println("a换行b 匹配 (?s)a.b    : " + "a\nb".matches("(?s)a.b")); // 让 . 匹配换行
    }
}
