package racingcar.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;

class InputConvertTest {
    InputParser inputParser;

    @BeforeEach
    void SetUp() {
        inputParser = new InputParser();
    }

    @Test
    @DisplayName("convertToCars()는 차 이름이 없을 때 예외를 발생한다")
    void convertToCars_throw_nonCarName() {
        // given
        String carNames = "";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> inputParser.parseCarNames(carNames));
        assertThatThrownBy(() -> inputParser.parseCarNames(carNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름이 입력되지 않았습니다");
    }

    @Test
    @DisplayName("convertToCars()는 차 이름이 null일 때 예외를 발생한다")
    void convertToCars_throw_nullCarName() {
        // given
        String carNames = "";

        // when & then
        assertThrows(IllegalArgumentException.class, () -> inputParser.parseCarNames(carNames));
        assertThatThrownBy(() -> inputParser.parseCarNames(carNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름이 입력되지 않았습니다");
    }

    @Test
    @DisplayName("convertToCars()는 차 이름을 차 리스트로 반환한다")
    void convertToCars_return_carsList() {
        // given
        String carNames = "pobi,woni";

        // when
        List<Car> cars = inputParser.parseCarNames(carNames);

        // then
        assertThat(cars).hasSize(2);
        assertThat(cars)
                .extracting(Car::getName)
                .containsExactly("pobi", "woni");
    }

    @Test
    @DisplayName("convertToRounds()는 시도 횟수가 문자라면 예외를 발생한다")
    void convertToRounds_throw_notNumber() {
        // given
        String rounds = "^";

        // when & then
        assertThatThrownBy(() -> inputParser.parseRounds(rounds))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 숫자여야 합니다");
    }

    @Test
    @DisplayName("convertToRounds()는 시도 횟수가 0 이하라면 예외를 발생한다")
    void convertToRounds_throw_zero() {
        // given
        String rounds = "0";

        // when & then
        assertThatThrownBy(() -> inputParser.parseRounds(rounds))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 1 이상이어야 합니다");
    }

    @Test
    @DisplayName("convertToRounds()는 문자열 시도 횟수를 정수로 반환한다")
    void convertToRounds_return_round() {
        // given
        String rounds = "5";

        // when
        int round = inputParser.parseRounds(rounds);

        // then
        assertThat(round).isEqualTo(5);
    }
}