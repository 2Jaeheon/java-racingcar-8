package racingcar.controller;

import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.MoveStrategy;
import racingcar.domain.Race;
import racingcar.util.InputParser;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RaceController {
    private final MoveStrategy moveStrategy;
    private final InputView inputView;
    private final OutputView outputView;
    private final InputParser inputParser;

    public RaceController(MoveStrategy moveStrategy, InputView inputView, OutputView outputView,
                          InputParser inputParser) {
        this.moveStrategy = moveStrategy;
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputParser = inputParser;
    }

    public void play() {
        try {
            // 준비
            Race race = prepareRace();

            // 실행
            start(race);

            // 결과
            outputView.printWinner(race.findWinners());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private Race prepareRace() {
        String carNames = inputView.readCarNames();
        String roundInput = inputView.readRound();
        int rounds = inputParser.parseRounds(roundInput);

        List<Car> carList = inputParser.parseCarNames(carNames);
        return new Race(carList, this.moveStrategy, rounds);
    }

    private void start(Race race) {
        outputView.printRaceStart();
        while (!race.isFinished()) {
            race.playRound();
            outputView.printRaceRound(race.getCars());
        }
    }
}
