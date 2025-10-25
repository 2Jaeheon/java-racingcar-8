package racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private final List<Car> cars;
    private final MoveCondition moveCondition;
    private final int totalRounds;
    private int currentRound;

    private static final String ERROR_MINIMUM_CAR = "경주할 자동차가 1대 이상 필요합니다";

    public Race(List<Car> cars, MoveCondition moveCondition, int totalRounds) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MINIMUM_CAR);
        }
        this.cars = new ArrayList<>(cars);
        this.moveCondition = moveCondition;
        this.totalRounds = totalRounds;
        this.currentRound = 0;
    }

    public List<String> findWinners() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = Math.max(maxPosition, car.getPosition());
        }

        final int finalMaxPosition = maxPosition;
        return cars.stream()
                .filter(car -> car.getPosition() == finalMaxPosition)
                .map(Car::getName)
                .toList();
    }

    public void playRound() {
        for (Car car : cars) {
            if (moveCondition.shouldMove()) {
                car.move();
            }
        }
        currentRound++;
    }

    public boolean isFinished() {
        return currentRound >= totalRounds;
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
