package racingcar.config;

import racingcar.controller.RaceController;
import racingcar.util.InputParser;
import racingcar.domain.MoveStrategy;
import racingcar.domain.RandomStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {
    public MoveStrategy moveStrategy() {
        return new RandomStrategy();
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

    public RaceController raceController() {
        return new RaceController(
                moveStrategy(),
                inputView(),
                outputView(),
                inputParser()
        );
    }
}
