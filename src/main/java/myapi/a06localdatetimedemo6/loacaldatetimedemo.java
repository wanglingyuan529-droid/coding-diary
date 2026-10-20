package myapi.a06localdatetimedemo6;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class loacaldatetimedemo {
    public static void main(String[] args) {
        LocalDate d = LocalDate.of(2026, 10, 20);
        LocalDate today = LocalDate.now();
        LocalDate parsed = LocalDate.parse("2026-10-20");

        LocalTime t = LocalTime.of(10, 20, 30);
        LocalDateTime dt = LocalDateTime.of(2026, 10, 20, 10, 20, 30);
        d.getYear();
        today.getYear();
        parsed.getYear();
        t.getHour();
        dt.getYear();
        System.out.println(d);
        System.out.println(today);
        System.out.println(parsed);
        System.out.println(t);
        System.out.println(dt);
        LocalDate d1 = d.plusDays(1);
        System.out.println(d1);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = fmt.format(LocalDateTime.of(2024, 10, 19, 10, 30, 45));
        System.out.println(s);
        LocalDateTime dt1 = LocalDateTime.parse("2024-10-19 10:30:45", fmt);
        System.out.println(dt1);
    }
}
