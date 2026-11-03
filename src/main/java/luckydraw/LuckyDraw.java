package luckydraw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 随机点名练习：先录入全班学生，再逐个抽取幸运儿。
 * <p>
 * 规则：
 * 1. 录入阶段：每次输入「姓名 性别」，性别 1 表示男生、其它值表示女生；输入 exit 结束录入。
 * 2. 抽取阶段：每输入任意内容抽一位；两个池都有人时，男生被抽中的概率为 70%。
 * 3. 某一池抽空后会自动从另一池抽；两池都抽完提示开启新一轮，并用原始名单重新填充。
 * <p>
 * 用到的知识点：ArrayList 的增删查、Random.nextInt(bound)、Scanner 读取、try-with-resources 关闭输入流。
 */
public class LuckyDraw {

    /** 男生被抽中的概率（百分比），仅在男女生都还有剩余时生效。 */
    private static final int BOY_PERCENT = 70;

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            List<String> boys = new ArrayList<>();
            List<String> girls = new ArrayList<>();

            inputStudents(sc, boys, girls);
            if (boys.isEmpty() && girls.isEmpty()) {
                System.out.println("没有录入任何学生，程序结束。");
                return;
            }

            draw(sc, boys, girls);
        }
    }

    /** 录入阶段：反复读取「姓名 性别」，直到输入 exit。 */
    private static void inputStudents(Scanner sc, List<String> boys, List<String> girls) {
        System.out.println("录入学生：输入「姓名 性别」（1=男生，其它=女生），输入 exit 结束录入");
        while (sc.hasNext()) {
            String name = sc.next();
            if ("exit".equals(name)) {
                break;
            }
            if (!sc.hasNextInt()) {
                sc.next();   // 丢弃写错的性别 token，否则它会被当成下一个名字
                System.out.println("  性别应为数字，跳过: " + name);
                continue;
            }
            int gender = sc.nextInt();
            (gender == 1 ? boys : girls).add(name);
        }
        System.out.println("录入完成：男生 " + boys.size() + " 人，女生 " + girls.size() + " 人");
    }

    /** 抽取阶段：每输入一次抽一位，一轮抽完自动用原始名单重新填充。 */
    private static void draw(Scanner sc, List<String> boysSource, List<String> girlsSource) {
        List<String> boys = new ArrayList<>(boysSource);
        List<String> girls = new ArrayList<>(girlsSource);
        System.out.println("输入任意内容抽一位，输入 exit 结束");

        while (sc.hasNext()) {
            if ("exit".equals(sc.next())) {
                break;
            }

            // 两边都有人就按概率抽，只剩一边就直接抽那一边
            boolean pickBoy = (!boys.isEmpty() && !girls.isEmpty())
                    ? RANDOM.nextInt(100) < BOY_PERCENT
                    : !boys.isEmpty();

            List<String> pool = pickBoy ? boys : girls;
            System.out.println("幸运儿: " + pool.remove(RANDOM.nextInt(pool.size())));

            if (boys.isEmpty() && girls.isEmpty()) {
                System.out.println("=== 全班抽完，开启新一轮 ===");
                boys.addAll(boysSource);
                girls.addAll(girlsSource);
            }
        }
    }
}
