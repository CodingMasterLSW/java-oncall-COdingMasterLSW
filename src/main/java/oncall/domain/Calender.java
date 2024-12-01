package oncall.domain;

import java.util.Arrays;

public enum Calender {

    JAN(1, 1, 31),
    FEB(2, 1, 28),
    MAR(3, 1, 31),
    APR(4, 1, 30),
    MAY(5, 1, 31),
    JUNE(6, 1, 30),
    JUL(7, 1, 31),
    AUG(8, 1, 31),
    SEP(9, 1, 30),
    OCT(10, 1, 31),
    NOV(11, 1, 30),
    DEC(12, 1, 31);


    private final int month;
    private final int startDate;
    private final int endDate;

    Calender(int month, int startDate, int endDate) {
        this.month = month;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Calender decideCalender(int month) {
        return Arrays.stream(values())
                .filter(calender -> calender.month == month)
                .findFirst()
                .orElse(null);
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }
}
