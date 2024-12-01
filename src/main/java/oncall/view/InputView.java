package oncall.view;

import static oncall.exception.ErrorMessage.NOT_BLANK_INPUT;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String BLANK = "";
    private static final String MONTH_AND_DAY_INPUT_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String WEEKDAY_EMERGENCY_WORK_MESSAGE = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String HOLIDAY_EMERGENCY_WORK_MESSAGE = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

    private InputView() {
    }

    public static InputView create() {
        return new InputView();
    }

    public void printMonthAndDayMessage() {
        printMessage(MONTH_AND_DAY_INPUT_MESSAGE);
    }

    public String monthAndDayInput() {
        return userInput();
    }

    public String WeekendWorkWorkInput() {
        printMessage(BLANK);
        printMessage(WEEKDAY_EMERGENCY_WORK_MESSAGE);
        return userInput();
    }

    public String holidayWorkInput() {
        printMessage(BLANK);
        printMessage(HOLIDAY_EMERGENCY_WORK_MESSAGE);
        return userInput();
    }

    private void printMessage(String message) {
        System.out.print(message);
    }

    private String userInput() {
        String userInput = Console.readLine();
        validateInput(userInput);
        return userInput;
    }

    private void validateInput(String userInput) {
        if (userInput.isBlank() || userInput == null) {
            throw new IllegalArgumentException(NOT_BLANK_INPUT.getMessage());
        }
    }
}
