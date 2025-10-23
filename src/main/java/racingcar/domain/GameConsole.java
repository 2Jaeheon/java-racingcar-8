package racingcar.domain;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        List<Car> cars = parse(carNames);

        int rounds = getRounds();

        outputView.printRaceStart();
        startRace(cars, rounds);

        List<String> winners = findWinners(cars);
        outputView.printWinner(winners);
    }

    private List<String> findWinners(List<Car> cars) {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }

        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }
        return winners;
    }

    private void startRace(List<Car> cars, int rounds) {
        for (int i = 0; i < rounds; i++) {
            for (Car car : cars) {
                car.move(moveStrategy);
            }

            List<String> carsStatus = cars.stream()
                    .map(Car::getStatus)
                    .toList();
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
