package cardgame;

import java.util.*;

public class PokerGame {
    static ArrayList<String> poker = new ArrayList<>();
    static Map<String, Integer> cardIndex = new HashMap<>();

    static {
        String[] color = { "♠", "♥", "♣", "♦" };
        String[] number = { "3", "4", "5", "6", "7", "8", "9", "10",
                "J", "Q", "K", "A", "2" };

        // 准备牌
        for (String c : color) {
            for (String n : number) {
                poker.add(c + n);
            }
        }
        poker.add("小王");
        poker.add("大王");

        // 建编号表（先点数后花色）
        int index = 0;
        for (String n : number) {
            for (String c : color) {
                cardIndex.put(c + n, index++);
            }
        }
        cardIndex.put("小王", 52);
        cardIndex.put("大王", 53);
    }

    public PokerGame() {
        // 洗牌
        Collections.shuffle(poker);

        // 发牌
        ArrayList<String> p1 = new ArrayList<>();
        ArrayList<String> p2 = new ArrayList<>();
        ArrayList<String> p3 = new ArrayList<>();
        ArrayList<String> bottom = new ArrayList<>();

        for (int i = 0; i < poker.size(); i++) {
            if (i < 51) {
                if (i % 3 == 0)
                    p1.add(poker.get(i));
                else if (i % 3 == 1)
                    p2.add(poker.get(i));
                else
                    p3.add(poker.get(i));
            } else {
                bottom.add(poker.get(i));
            }
        }

        // 排序（按编号）
        Comparator<String> cmp = Comparator.comparingInt(cardIndex::get);
        p1.sort(cmp);
        p2.sort(cmp);
        p3.sort(cmp);
        bottom.sort(cmp);

        // 看牌
        System.out.println("玩家1: " + p1);
        System.out.println("玩家2: " + p2);
        System.out.println("玩家3: " + p3);
        System.out.println("底牌 : " + bottom);
    }

    public static void main(String[] args) {
        new PokerGame();
    }
}