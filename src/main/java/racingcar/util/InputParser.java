package racingcar.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import racingcar.domain.Car;

public class InputParser {
    private static final String DELIMITER = ",";

    private static final String ERROR_NO_CAR_NAMES = "자동차 이름이 입력되지 않았습니다";
    private static final String ERROR_NOT_NUMBER = "시도 횟수는 숫자여야 합니다";
    private static final String ERROR_INVALID_ROUND = "시도 횟수는 1 이상이어야 합니다";
    private static final String ERROR_DUPLICATE_NAMES = "자동차 이름은 중복될 수 없습니다";


    public List<Car> parseCarNames(String carNames) {
        if (carNames == null || carNames.isBlank()) {
            throw new IllegalArgumentException(ERROR_NO_CAR_NAMES);
        }

        String[] parsedCarNames = carNames.split(DELIMITER, -1);
        validateUniqueNames(parsedCarNames);

        return Arrays.stream(parsedCarNames)
                .map(Car::new)
                .toList();
    }

    private void validateUniqueNames(String[] parsedCarNames) {
        List<String> carNameList = Arrays.asList(parsedCarNames);
        Set<String> nameSet = new HashSet<>(carNameList);

        if (carNameList.size() != nameSet.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_NAMES);
        }
    }

    public int parseRounds(String roundInput) {
        int rounds;
        try {
            rounds = Integer.parseInt(roundInput);
        } catch (Exception e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }

        // 시도 횟수가 1 이상임을 검증
        if (rounds <= 0) {
            throw new IllegalArgumentException(ERROR_INVALID_ROUND);
        }
        return rounds;
    }
}
