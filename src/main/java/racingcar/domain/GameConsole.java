package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class GameConsole {
    private static final String DELIMITER = ",";

    private final MoveStrategy moveStrategy;
    private final InputView inputView;
    private final OutputView outputView;

    public GameConsole() {
        this.moveStrategy = new RandomStrategy();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void play() {
        String carNames = inputView.readCarNames();
        List<Car> carList = parse(carNames);
        Race race = new Race(carList);

        int rounds = getRounds();

        outputView.printRaceStart();
        startRace(race, rounds);
        List<String> winners = race.findWinners();
        outputView.printWinner(winners);
    }

    private void startRace(Race race, int rounds) {
        for (int i = 0; i < rounds; i++) {
            race.moveAll(moveStrategy);
            List<String> carsStatus = race.getStatuses();
            outputView.printRound(carsStatus);
        }
    }

    private List<Car> parse(String carNames) {
        String[] parsedCarNames = carNames.split(DELIMITER);

        if (parsedCarNames.length == 0) {
            throw new IllegalArgumentException();
        }

        try {
            return Arrays.stream(parsedCarNames)
                    .map(Car::new)
                    .toList();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private int getRounds() {
        try {
            String roundsInput = inputView.readRound();
            int rounds = Integer.parseInt(roundsInput);

            if (rounds < 0) {
                throw new IllegalArgumentException();
            }

            return rounds;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
