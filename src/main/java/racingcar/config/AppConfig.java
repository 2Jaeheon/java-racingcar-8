package racingcar.config;

import racingcar.domain.GameConsole;
import racingcar.util.InputConverter;
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

    public InputConverter inputConverter() {
        return new InputConverter();
    }

    public GameConsole gameConsole() {
        return new GameConsole(
                moveStrategy(),
                inputView(),
                outputView(),
                inputConverter()
        );
    }
}
