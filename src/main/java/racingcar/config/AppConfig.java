package racingcar.config;

import racingcar.controller.RaceController;
import racingcar.util.InputParser;
import racingcar.domain.MoveCondition;
import racingcar.domain.RandomCondition;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {
    public MoveCondition moveCondition() {
        return new RandomCondition();
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public InputParser inputParser() {
        return new InputParser();
    }

    public RaceController game() {
        return new RaceController(
                moveCondition(),
                inputView(),
                outputView(),
                inputParser()
        );
    }
}
