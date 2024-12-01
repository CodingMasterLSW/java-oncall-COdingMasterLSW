package oncall.view;

import static oncall.exception.ErrorMessage.NOT_BLANK_INPUT;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String MONTH_AND_DAY_INPUT_MESSAGE = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String BLANK = "";

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
