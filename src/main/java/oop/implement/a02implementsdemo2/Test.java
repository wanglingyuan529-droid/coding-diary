package oop.implement.a02implementsdemo2;

public class Test {
    public static void main(String[] args) {
        TableTennisPlayer tableTennisPlayer = new TableTennisPlayer("马龙", 35);
        System.out.println(tableTennisPlayer.getName() + "," + tableTennisPlayer.getAge());
        tableTennisPlayer.study();
        tableTennisPlayer.speakEnglish();

        BasketballPlayer basketballPlayer = new BasketballPlayer("姚明", 42);
        System.out.println(basketballPlayer.getName() + "," + basketballPlayer.getAge());
        basketballPlayer.study();

        TableTennisCoach tableTennisCoach = new TableTennisCoach("刘国梁", 47);
        System.out.println(tableTennisCoach.getName() + "," + tableTennisCoach.getAge());
        tableTennisCoach.teach();
        tableTennisCoach.speakEnglish();

        BasketballCoach basketballCoach = new BasketballCoach("杜锋", 43);
        System.out.println(basketballCoach.getName() + "," + basketballCoach.getAge());
        basketballCoach.teach();
    }
}