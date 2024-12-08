package oncall.service;

import static oncall.exception.ErrorMessage.INVALID_INPUT;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.AssignmentResult;
import oncall.domain.AssignmentResults;
import oncall.domain.Calender;
import oncall.domain.Holiday;
import oncall.domain.Week;
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

    public AssignmentResults assignmentResult(WorkingDay workingDay, Workers workers) {

        int startingDayValue = workingDay.getStartingDayValue();
        Calender month = workingDay.getMonth();

        int weekIdx = 0;
        int holidayIdx = 0;

        AssignmentResults assignmentResults = AssignmentResults.create();

        for (int i = 1; i <= month.getEndDate(); i++) {

            int decisionResult = startingDayValue % 7;
            Week weekByValue = Week.findWeekByValue(decisionResult);
            boolean holiday = weekByValue.isHoliday();
            List<Worker> holidayWorkers = workers.getHolidayWorkers();
            List<Worker> weekWorkers = workers.getWeekWorkers();

            if (holiday) {
                int currentIdx = holidayIdx % holidayWorkers.size();
                Worker worker = holidayWorkers.get(currentIdx);
                AssignmentResult assignmentResult = AssignmentResult.of(month.getMonth(), i,
                        weekByValue.getName(), worker);
                assignmentResults.addResult(assignmentResult);
                holidayIdx++;
            } else {
                if (Holiday.isHoliday(month.getMonth(), i)) {
                    int currentIdx = holidayIdx % holidayWorkers.size();
                    Worker worker = holidayWorkers.get(currentIdx);
                    AssignmentResult assignmentResult = AssignmentResult.of(month.getMonth(), i,
                            weekByValue.getName(), worker);
                    assignmentResult.changeHoliday();
                    assignmentResults.addResult(assignmentResult);
                    holidayIdx++;
                } else {
                    int currentIdx = weekIdx % weekWorkers.size();
                    Worker worker = weekWorkers.get(currentIdx);
                    AssignmentResult assignmentResult = AssignmentResult.of(month.getMonth(), i,
                            weekByValue.getName(), worker);
                    assignmentResults.addResult(assignmentResult);
                    weekIdx++;
                }
            }
            startingDayValue++;

        }
        return assignmentResults;
    }


    private void validateWorker(Worker worker) {
        if (worker == null) {
            throw new IllegalArgumentException(INVALID_INPUT.getMessage());
        }
    }

}
