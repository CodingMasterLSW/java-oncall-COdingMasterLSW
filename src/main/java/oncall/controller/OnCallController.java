package oncall.controller;

import oncall.domain.Worker;
import oncall.domain.Workers;
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
        inputView.monthAndDayInput();
        String weekWorkers = inputView.WeekendWorkWorkInput();
        String holidayWorkers = inputView.holidayWorkInput();

        Workers workers = onCallservice.create(weekWorkers);
        for (Worker worker : workers.getWeekWorkers()) {
            System.out.println(worker.getName());
        }
    }


}
