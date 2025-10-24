package racingcar.domain;

import java.util.List;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class GameConsole {
    private final MoveStrategy moveStrategy;
    private final InputView inputView;
    private final OutputView outputView;
    private final racingcar.domain.inputConverter inputConverter;

    private static final String COLON = " : ";
    private static final String PROGRESS = "-";

    public GameConsole() {
        this.moveStrategy = new RandomStrategy();
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.inputConverter = new inputConverter();
    }

    public void play() {
        try {
            // 준비
            Race race = setUpRace();
            int rounds = setUpRounds();

            // 실행
            outputView.printRaceStart();
            startRace(race, rounds);

            // 결과
            List<String> winners = race.findWinners();
            outputView.printWinner(winners);
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
        for (int i = 0; i < rounds; i++) {
            race.moveAll(moveStrategy);
            List<String> raceStatus = race.getCars().stream()
                    .map(car -> car.getName() + COLON + PROGRESS.repeat(car.getPosition()))
                    .toList();
            outputView.printRound(raceStatus);
        }
    }
}
