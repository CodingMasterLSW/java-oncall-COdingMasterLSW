package oncall.config;

import oncall.controller.OnCallController;
import oncall.service.OnCallService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class AppConfig {

    private AppConfig() {
    }

    public static OnCallController createController() {
        return new OnCallController(InputView.create(), OutputView.create(), createService());
    }

    public static OnCallService createService() {
        return new OnCallService();
    }

}
