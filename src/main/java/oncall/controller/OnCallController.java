package oncall.controller;

import java.util.List;
import oncall.service.OnCallService;
import oncall.utils.InputParser;
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
        List<String> parse1 = InputParser.parse(weekWorkers);
        List<String> parse2 = InputParser.parse(holidayWorkers);
    }


}
