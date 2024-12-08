package oncall.view;

import oncall.domain.Calender;
import oncall.domain.Week;
import oncall.domain.Worker;

public class OutputView {

    private static final String ASSIGNMENT_RESULT = "%s월 %s일 %s %s";
    private static final String ASSIGNMENT_RESULT2 = "%s월 %s일 %s(휴일) %s";

    private static final String BLANK = "";

    private OutputView() {
    }

    public static OutputView create() {
        return new OutputView();
    }

    public void printErrorMessage(String message) {
        printMessage(message);
    }

    public void printAssignResult(Calender calender, int currentDay, Week week, Worker worker) {
        System.out.printf(ASSIGNMENT_RESULT, calender.getMonth(), currentDay, week.getName(),
                worker.getName());
        printMessage(BLANK);
    }

    public void printAssignResultHoliday(Calender calender, int currentDay, Week week, Worker worker) {
        System.out.printf(ASSIGNMENT_RESULT2, calender.getMonth(), currentDay, week.getName(),
                worker.getName());
        printMessage(BLANK);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

}
