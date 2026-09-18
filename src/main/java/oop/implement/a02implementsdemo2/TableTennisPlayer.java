package oop.implement.a02implementsdemo2;

public class TableTennisPlayer extends Athlete implements SpeakEnglish {

    public TableTennisPlayer() {
        super();
    }

    public TableTennisPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void study() {
        System.out.println("乒乓球运动员正在学打乒乓球");
    }

    @Override
    public void speakEnglish() {
        System.out.println("乒乓球运动员会说英语");
    }
}