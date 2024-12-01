package oncall.service;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.Worker;
import oncall.domain.Workers;
import oncall.utils.InputParser;

public class OnCallService {

    public Workers create(String userInput) {
        List<Worker> workers = new ArrayList<>();

        List<String> parsers = InputParser.parse(userInput);
        for (String parser : parsers) {
            Worker worker = Worker.from(parser);
            workers.add(worker);
        }
        return Workers.from(workers);
    }

}
