package oncall.controller;

import java.util.List;
import java.util.function.Supplier;
import oncall.domain.Calender;
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
        // 여기서 이미 객체 만들어졌음. 이제 요일 따라서 출력만 하면 되는데
        Workers workers = handleWorkers();

        // 여기서 int 값을 반환하고, 7로 나눠야함.
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
                System.out.println(month.getMonth() + "월 " + i + "일 " + weekByValue.getName() + " "
                        + worker.getName());
                holidayIdx++;
            } else {
                List<Worker> weekWorkers = workers.getWeekWorkers();
                int currentIdx = weekIdx % weekWorkers.size();
                Worker worker = weekWorkers.get(currentIdx);
                System.out.println(month.getMonth() + "월 " + i + "일 " + weekByValue.getName() + " "
                        + worker.getName());
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
