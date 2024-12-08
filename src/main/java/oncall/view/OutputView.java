package oncall.view;

import oncall.domain.AssignmentResult;
import oncall.domain.AssignmentResults;

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

    public void printAssignResults(AssignmentResults assignmentResults) {
        for (AssignmentResult assignmentResult : assignmentResults.getAssignmentResults()) {
            printAssignmentResult(assignmentResult);
        }
    }

    private void printAssignmentResult(AssignmentResult assignmentResult) {
        if (assignmentResult.isHoliday()) {
            System.out.printf(ASSIGNMENT_RESULT2, assignmentResult.getMonth(),
                    assignmentResult.getDate(), assignmentResult.getDay(),
                    assignmentResult.getWorker().getName());
            printMessage(BLANK);
            return;
        }
        System.out.printf(ASSIGNMENT_RESULT, assignmentResult.getMonth(),
                assignmentResult.getDate(), assignmentResult.getDay(),
                assignmentResult.getWorker().getName());
        printMessage(BLANK);
    }


    private void printMessage(String message) {
        System.out.println(message);
    }

}
