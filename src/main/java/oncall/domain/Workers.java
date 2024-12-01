package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class Workers {

    private final List<Worker> weekWorkers;
    private final List<Worker> holidayWorkers;

    private Workers(List<Worker> weekWorkers) {
        this.weekWorkers = weekWorkers;
        this.holidayWorkers = new ArrayList<>();
    }

    public static Workers from(List<Worker> weekWorkers) {
        return new Workers(weekWorkers);
    }

    public List<Worker> getWeekWorkers() {
        return weekWorkers;
    }

    public List<Worker> getHolidayWorkers() {
        return holidayWorkers;
    }

    private void validateHolidayWorkers(List<Worker> holidayWorkers) {

    }

}
