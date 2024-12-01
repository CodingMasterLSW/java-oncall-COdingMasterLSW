package oncall.service;

import static oncall.exception.ErrorMessage.INVALID_INPUT;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Worker;
import oncall.domain.Workers;
import oncall.domain.WorkingDay;
import oncall.utils.InputParser;

public class OnCallService {

    public Workers createWorkers(String userInput) {
        List<Worker> workers = new ArrayList<>();

        List<String> parsers = InputParser.parse(userInput);
        for (String parser : parsers) {
            Worker worker = Worker.from(parser);
            workers.add(worker);
        }
        return Workers.from(workers);
    }

    public WorkingDay createWorkingDay(String userInput) {
        List<String> parsers = InputParser.parseWorkingDay(userInput);
        String month = parsers.get(0);
        String day = parsers.get(1);
        return WorkingDay.of(Integer.parseInt(month), day);
    }

    public void addHolidayWorker(Workers workers, String inputHolidayWorkers) {
        List<String> parsers = InputParser.parse(inputHolidayWorkers);
        List<Worker> tmpWorkers = new ArrayList<>();
        for (String parser : parsers) {
            Worker worker = workers.findWorker(parser);
            validateWorker(worker);
            tmpWorkers.add(worker);
        }
        workers.addHolidayWorker(tmpWorkers);
    }

    private void validateWorker(Worker worker) {
        if (worker == null) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

}
