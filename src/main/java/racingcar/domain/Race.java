package racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private final List<Car> cars;

    private static final String ERROR_MINIMUM_CAR = "경주할 자동차가 1대 이상 필요합니다";

    public Race(List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MINIMUM_CAR);
        }
        this.cars = new ArrayList<>(cars);
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

    public void moveCars(MoveCondition moveCondition) {
        for (Car car : cars) {
            car.move(moveCondition);
        }
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
