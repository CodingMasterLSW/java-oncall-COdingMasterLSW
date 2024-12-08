package oncall.controller;

import java.util.List;
import java.util.function.Supplier;
import oncall.domain.Calender;
import oncall.domain.Holiday;
import oncall.domain.Week;
import oncall.domain.Worker;
import oncall.domain.Workers;
import oncall.domain.WorkingDay;
import oncall.service.OnCallService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OnCallController {

    private final InputView inputView;
    private final OutputView outputView;
    private final OnCallService onCallservice;

    public OnCallController(InputView inputView, OutputView outputView,
            OnCallService onCallservice) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.onCallservice = onCallservice;
    }

    public void start() {
        WorkingDay workingDay = handleMonthInput();
        Workers workers = handleWorkers();

        int startingDayValue = workingDay.getStartingDayValue();
        Calender month = workingDay.getMonth();
        int weekIdx = 0;
        int holidayIdx = 0;

        for (int i = 1; i <= month.getEndDate(); i++) {
            int decisionResult = startingDayValue % 7;
            Week weekByValue = Week.findWeekByValue(decisionResult);
            boolean holiday = weekByValue.isHoliday();
            if (holiday) {
                List<Worker> holidayWorkers = workers.getHolidayWorkers();
                int currentIdx = holidayIdx % holidayWorkers.size();
                Worker worker = holidayWorkers.get(currentIdx);
                outputView.printAssignResult(month, i, weekByValue, worker);
                holidayIdx++;
            } else {
                if (Holiday.isHoliday(month.getMonth(), i)) {
                    List<Worker> holidayWorkers = workers.getHolidayWorkers();
                    int currentIdx = holidayIdx % holidayWorkers.size();
                    Worker worker = holidayWorkers.get(currentIdx);
                    outputView.printAssignResultHoliday(month, i, weekByValue, worker);
                    holidayIdx++;
                }
                List<Worker> weekWorkers = workers.getWeekWorkers();
                int currentIdx = weekIdx % weekWorkers.size();
                Worker worker = weekWorkers.get(currentIdx);
                outputView.printAssignResult(month, i, weekByValue, worker);
                weekIdx++;
            }
            startingDayValue++;
        }
    }

    private WorkingDay handleMonthInput() {
        inputView.printMonthAndDayMessage();
        return retryOnInvalidInput(() -> {
            String monthAndDay = inputView.monthAndDayInput();
            return onCallservice.createWorkingDay(monthAndDay);
        });
    }

    private Workers handleWorkers() {
        return retryOnInvalidInput(() -> {
            String weekWorkers = inputView.WeekendWorkWorkInput();
            String holidayWorkers = inputView.holidayWorkInput();
            Workers workers = onCallservice.createWorkers(weekWorkers);
            onCallservice.addHolidayWorker(workers, holidayWorkers);
            return workers;
        });
    }

    private <T> T retryOnInvalidInput(Supplier<T> input) {
        while (true) {
            try {
                return input.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

}
