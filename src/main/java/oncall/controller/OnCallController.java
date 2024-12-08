package oncall.controller;

import java.util.function.Supplier;
import oncall.domain.AssignmentResults;
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
        AssignmentResults assignmentResults = onCallservice.assignmentResult(workingDay, workers);
        outputView.printAssignResults(assignmentResults);
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
