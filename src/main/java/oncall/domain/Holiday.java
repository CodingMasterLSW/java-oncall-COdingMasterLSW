package oncall.domain;

import java.util.Arrays;

public enum Holiday {

    신정("신정", 1, 1),
    삼일절("삼일절", 3, 1),
    어린이날("어린이날", 5, 5),
    현충일("현충일", 6, 6),
    광복절("광복절", 8, 15),
    개천절("개천절", 10, 3),
    한글날("한글날", 10, 9),
    성탄절("성탄절", 12, 25);

    private final String name;
    private final int month;
    private final int day;

    Holiday(String name, int month, int day) {
        this.name = name;
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(int month, int day) {
        Holiday isHoliday = Arrays.stream(Holiday.values())
                .filter(holiday -> holiday.month == month && holiday.day == day)
                .findFirst()
                .orElse(null);
        if (isHoliday == null) {
            return false;
        }
        return true;
    }
}
