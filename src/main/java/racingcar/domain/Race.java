package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Race {
    private final List<Car> cars;

    public Race(List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException();
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
