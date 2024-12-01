package oncall.controller;

import java.util.function.Supplier;
import oncall.domain.Calender;
import oncall.domain.Week;
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
        handleWorkers();

        Calender calender = Calender.decideCalender(workingDay.getMonth());
        int endDate = calender.getEndDate();


        int idx = 1;
        String startingDay = workingDay.getStartingDay();
        int month = workingDay.getMonth();
        Week week = Week.findWeekByName(startingDay);
        int tmpValue = week.getValue();

        /***
         * 요일 계산 로직
         */
        while (true) {
            int value = tmpValue % 7;
            Week weekByValue = Week.findWeekByValue(value);
            System.out.println(month +"월 " + idx+"일 " + weekByValue.getName());
            idx++;
            tmpValue++;

            if (idx > endDate) {
                break;
            }
        }

    }

    private WorkingDay handleMonthInput() {
        inputView.printMonthAndDayMessage();
        return retryOnInvalidInput(() -> {
            String monthAndDay = inputView.monthAndDayInput();
            return onCallservice.createWorkingDay(monthAndDay);
        });
    }

    /*
    private WorkingDay handleCreateWorkingDay(String monthAndDay) {
        WorkingDay workingDay = retryOnInvalidInput(
                () -> onCallservice.createWorkingDay(monthAndDay));
        return workingDay;
    }
     */
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
