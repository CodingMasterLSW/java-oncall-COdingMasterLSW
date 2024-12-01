package oncall.controller;

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

    public OnCallController(InputView inputView, OutputView outputView, OnCallService onCallservice) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.onCallservice = onCallservice;
    }

    public void start() {
        inputView.printMonthAndDayMessage();
        String monthAndDay = inputView.monthAndDayInput();
        WorkingDay workingDay = onCallservice.createWorkingDay(monthAndDay);

        String weekWorkers = inputView.WeekendWorkWorkInput();
        String holidayWorkers = inputView.holidayWorkInput();

        Workers workers = onCallservice.createWorkers(weekWorkers);
        for (Worker worker : workers.getWeekWorkers()) {
            System.out.println(worker.getName());
        }

        onCallservice.addHolidayWorker(workers ,holidayWorkers);
    }


}
