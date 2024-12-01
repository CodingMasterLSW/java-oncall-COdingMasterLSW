package oncall.domain;

import java.util.Arrays;

public enum Week {

    MON("월", 0, false),
    TUE("화", 1, false),
    WEN("수", 2, false),
    THU("목", 3, false),
    FRI("금", 4, false),
    SAT("토", 5, true),
    SUN("일", 6, true);

    private final String name;
    private final int value;
    private boolean isHoliday;

    Week(String name, int value, boolean isHoliday) {
        this.name = name;
        this.value = value;
        this.isHoliday = isHoliday;
    }

    public static Week findWeekByName(String startingDay) {
        return Arrays.stream(values())
                .filter(week -> startingDay.equals(week.name))
                .findFirst()
                .orElse(null);
    }

    public static Week findWeekByValue(int value) {
        return Arrays.stream(values())
                .filter(week -> value == week.value)
                .findFirst()
                .orElse(null);
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public boolean isHoliday() {
        return isHoliday;
    }
}
