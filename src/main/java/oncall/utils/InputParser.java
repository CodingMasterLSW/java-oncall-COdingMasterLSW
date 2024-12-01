package oncall.utils;

import static oncall.exception.ErrorMessage.INVALID_INPUT;
import static oncall.exception.ErrorMessage.INVALID_WORKER_SIZE;

import java.util.List;
import java.util.regex.Pattern;

public class InputParser {

    private static final String DELIMITER = ",";
    private static final int MIN_WORKER = 5;
    private static final int MAX_WORKER = 35;
    private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

    private InputParser() {
    }

    public static List<String> parse(String userInput) {
        List<String> splitResult = List.of(userInput.split(DELIMITER));
        validateUserInput(splitResult);
        return splitResult;
    }

    public static List<String> parseWorkingDay(String userInput) {
        List<String> splitResult = List.of(userInput.split(DELIMITER));
        validateWorkingDayInputOnMonth(splitResult);
        validateWorkingDayInputOnDay(splitResult);
        return splitResult;
    }

    private static void validateUserInput(List<String> splitResult) {
        if (splitResult.size() < MIN_WORKER || splitResult.size() > MAX_WORKER) {
            throw new IllegalArgumentException(INVALID_WORKER_SIZE.getMessage());
        }
    }

    private static void validateWorkingDayInputOnMonth(List<String> splitResult) {
        String month = splitResult.get(0);
        if (!NUMBER_PATTERN.matcher(month).matches()) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
        int parseMonth = Integer.parseInt(month);

        if (parseMonth < 1 || parseMonth > 12) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    private static void validateWorkingDayInputOnDay(List<String> splitResult) {
        String day = splitResult.get(1);
        if (day.equals("월") || day.equals("화") || day.equals("수") || day.equals("목") || day.equals(
                "금") || day.equals("토") || day.equals("일")) {
            return;
        }
        throw new IllegalArgumentException(INVALID_INPUT.getMessage());
    }


}
