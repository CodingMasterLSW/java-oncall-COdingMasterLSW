package oncall.domain;

import static oncall.exception.ErrorMessage.INVALID_WORKER_NAME_LENGTH;

public class Worker {

    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    private Worker(String name) {
        validateNameLength(name);
        this.name = name;
    }

    public static Worker from(String name) {
        return new Worker(name);
    }

    public String getName() {
        return name;
    }

    private void validateNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_WORKER_NAME_LENGTH.getMessage());
        }
    }

}
