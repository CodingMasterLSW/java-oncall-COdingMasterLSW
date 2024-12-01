package oncall.utils;

import static oncall.exception.ErrorMessage.INVALID_WORKER_SIZE;

import java.util.List;

public class InputParser {

    private static final String DELIMITER = ",";
    private static final int MIN_WORKER = 5;
    private static final int MAX_WORKER = 35;

    private InputParser() {
    }

    public static List<String> parse(String userInput) {
        List<String> splitResult = List.of(userInput.split(DELIMITER));
        validateUserInput(splitResult);
        return splitResult;
    }

    private static void validateUserInput(List<String> splitResult) {
        if (splitResult.size() < MIN_WORKER || splitResult.size() > MAX_WORKER) {
            throw new IllegalArgumentException(INVALID_WORKER_SIZE.getMessage());
        }
    }


}
