package racingcar.domain;

import java.util.List;
import racingcar.dto.CarStatus;
import racingcar.util.InputConverter;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class GameConsole {
    private final MoveStrategy moveStrategy;
    private final InputView inputView;
    private final OutputView outputView;
    private final InputConverter inputConverter;

    public GameConsole(MoveStrategy moveStrategy, InputView inputView, OutputView outputView,
                       InputConverter inputConverter) {
        this.moveStrategy = moveStrategy;
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputConverter = inputConverter;
    }

    public void play() {
        try {
            // 준비
            Race race = setUpRace();
            int rounds = setUpRounds();

            // 실행
            startRace(race, rounds);

            // 결과
            outputView.printWinner(race.findWinners());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private Race setUpRace() {
        String carNames = inputView.readCarNames();
        List<Car> carList = inputConverter.convertToCars(carNames);
        return new Race(carList);
    }

    private int setUpRounds() {
        String roundInput = inputView.readRound();
        return inputConverter.convertToRounds(roundInput);
    }

    private void startRace(Race race, int rounds) {
        outputView.printRaceStart();
        for (int i = 0; i < rounds; i++) {
            race.moveAll(moveStrategy);
            List<CarStatus> raceStatus = race.getRaceStatus();
            outputView.printEachRound(raceStatus);
        }
    }
}
