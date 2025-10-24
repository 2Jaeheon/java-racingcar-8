package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Race {
    private final List<Car> cars;

    private static final String ERROR_MINIMUM_CAR = "경주할 자동차가 1대 이상 필요합니다";

    public Race(List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MINIMUM_CAR);
        }
        this.cars = cars;
    }

    public List<String> findWinners() {
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

    public void moveAll(MoveStrategy strategy) {
        for (Car car : cars) {
            car.move(strategy);
        }
    }

    public List<String> getStatuses() {
        return cars.stream()
                .map(Car::getStatus)
                .collect(Collectors.toList());
    }
}
