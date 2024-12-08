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

    public String getStartingDay() {
        return startingDay;
    }

    public int getStartingDayValue() {
        Week week = Week.findWeekByName(startingDay);
        return week.getValue();
    }

    public Calender getMonth() {
        return Calender.decideCalender(month);
    }
}
