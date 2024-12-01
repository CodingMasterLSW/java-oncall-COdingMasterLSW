package oncall.domain;

public class WorkingDay {

    private final int month;
    private final String startingDay;

    private WorkingDay(int month, String startingDay) {
        this.month = month;
        this.startingDay = startingDay;
    }

    public static WorkingDay of(int month, String startingDay) {
        return new WorkingDay(month, startingDay);
    }

    public int getMonth() {
        return month;
    }

    public String getStartingDay() {
        return startingDay;
    }
}
