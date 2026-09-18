package oop.implement.a02implementsdemo2;

public class TableTennisCoach extends Coach implements SpeakEnglish {

    public TableTennisCoach() {
        super();
    }

    public TableTennisCoach(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("乒乓球教练正在教打乒乓球");
    }

    @Override
    public void speakEnglish() {
        System.out.println("乒乓球教练会说英语");
    }
}