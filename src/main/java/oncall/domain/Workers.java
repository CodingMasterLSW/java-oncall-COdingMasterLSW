package oncall.domain;

import static oncall.exception.ErrorMessage.DUPLICATE_WORKER;
import static oncall.exception.ErrorMessage.INVALID_INPUT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Workers {

    private final List<Worker> weekWorkers;
    private final List<Worker> holidayWorkers;

    private Workers(List<Worker> weekWorkers) {
        validateDuplicateWorker(weekWorkers);
        this.weekWorkers = weekWorkers;
        this.holidayWorkers = new ArrayList<>();
    }

    public static Workers from(List<Worker> weekWorkers) {
        return new Workers(weekWorkers);
    }

    public void addHolidayWorker(List<Worker> inputHolidayWorkers) {
        validateDuplicateWorker(inputHolidayWorkers);
        validateHolidayWorkers(inputHolidayWorkers);
        for (Worker worker : inputHolidayWorkers) {
            holidayWorkers.add(worker);
        }
    }

    public List<Worker> getWeekWorkers() {
        return weekWorkers;
    }

    public List<Worker> getHolidayWorkers() {
        return holidayWorkers;
    }

    public Worker findWorker(String name) {
        return weekWorkers.stream()
                .filter(worker -> worker.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private void validateDuplicateWorker(List<Worker> workers) {
        Set<Worker> tmpWorker = new HashSet<>(workers);
        if (tmpWorker.size() != workers.size()) {
            throw new IllegalArgumentException(DUPLICATE_WORKER.getMessage());
        }
    }

    private void validateHolidayWorkers(List<Worker> holidayWorkers) {
        if (!holidayWorkers.equals(weekWorkers)) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Workers workers = (Workers) object;
        return Objects.equals(weekWorkers, workers.weekWorkers) && Objects.equals(
                holidayWorkers, workers.holidayWorkers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekWorkers, holidayWorkers);
    }
}
