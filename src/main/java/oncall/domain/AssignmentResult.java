package oncall.domain;

public class AssignmentResult {

    private final int month;
    private final int date;
    private boolean isHoliday;
    private final String day;
    private final Worker worker;

    private AssignmentResult(int month, int date, String day, Worker worker) {
        this.month = month;
        this.date = date;
        this.isHoliday = false;
        this.day = day;
        this.worker = worker;
    }

    public static AssignmentResult of(int month, int date, String day, Worker worker) {
        return new AssignmentResult(month, date, day, worker);
    }

    public void changeHoliday() {
        this.isHoliday = true;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public Worker getWorker() {
        return worker;
    }

    public boolean isHoliday() {
        return isHoliday;
    }
}
