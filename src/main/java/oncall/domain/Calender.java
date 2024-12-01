package oncall.domain;

import java.util.Arrays;

public enum Calender {

    JAN(1, 31),
    FEB(2, 28),
    MAR(3, 31),
    APR(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JUL(7, 31),
    AUG(8, 31),
    SEP(9, 30),
    OCT(10, 31),
    NOV(11, 30),
    DEC(12, 31);


    private final int month;
    private final int endDate;

    Calender(int month, int endDate) {
        this.month = month;
        this.endDate = endDate;
    }

    public static Calender decideCalender(int month) {
        return Arrays.stream(values())
                .filter(calender -> calender.month == month)
                .findFirst()
                .orElse(null);
    }

    public int getEndDate() {
        return endDate;
    }

    public int getMonth() {
        return month;
    }
}
