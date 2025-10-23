package racingcar.domain;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameConsole {

    private static final String CAR_NAMES_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ROUND_PROMPT = "시도할 횟수는 몇 회인가요?";
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String DELIMITER = ",";

    private final MoveStrategy moveStrategy;

    public GameConsole() {
        this.moveStrategy = new RandomStrategy();
    }

    private String read() {
        return Console.readLine();
    }

    private void print(String message) {
        System.out.println(message);
    }

    public void play() {
        print(CAR_NAMES_PROMPT);
        String carNames = read();
        List<Car> cars = parse(carNames);

        print(ROUND_PROMPT);
        int rounds = getRounds();

        print(RESULT_MESSAGE);
        startRace(cars, rounds);

        List<String> winners = findWinners(cars);
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
            printRoundResult(cars);
        }
    }

    private void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car.getStatus());
        }
        System.out.println();
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
            String roundsInput = read();
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
