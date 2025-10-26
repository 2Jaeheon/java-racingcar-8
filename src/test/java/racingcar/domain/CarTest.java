package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

    @Test
    @DisplayName("move() 메서드를 호출하면 position이 1 증가한다")
    void move() {
        // given
        Car car = new Car("pobi");
        int expected = 1;

        // when
        car.move();
        int actual = car.getPosition();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("isAtPosition()은 현재 위치와 입력된 위치가 같으면 true를 반환한다")
    void isAtPosition_returnTrue_whenPositionMatch() {
        // given
        Car car = new Car("pobi");
        car.move();
        car.move();

        // when
        boolean actual = car.isAtPosition(2);

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isAtPosition()은 현재 위치와 입력된 위치가 다르면 false 반환한다")
    void isAtPosition_returnFalse_whenPositionNotMatch() {
        // given
        Car car = new Car("pobi");
        car.move();
        car.move();

        // when
        boolean actual = car.isAtPosition(1);

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("getMaxPosition()은 차의 현재 위치가 입력값보다 크면 차의 위치를 반환한다")
    void getMaxPosition_returnCarPosition_whenCarIsBig() {
        // given
        Car car = new Car("pobi");
        car.move();
        car.move();
        car.move();
        int currentMax = 2;
        int expected = 3;

        // when
        int actual = car.getMaxPosition(currentMax);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("getMaxPosition()은 차의 현재 위치가 입력값보다 작으면 입력값을 반환한다")
    void getMaxPosition_returnInputValue_whenCarIsSmall() {
        // given
        Car car = new Car("pobi");
        car.move();
        car.move();
        int currentMax = 5;

        // when
        int actual = car.getMaxPosition(currentMax);

        // then
        assertThat(actual).isEqualTo(currentMax);
    }

    @Test
    @DisplayName("getPosition은 현재 자동차의 위치를 반환한다.")
    void getPosition_returnCurrentCarPosition() {
        // given
        Car car = new Car("pobi");
        car.move();
        int expected = 1;

        // when
        int actual = car.getPosition();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("getName()은 현재 자동차의 이름을 반환한다")
    void getName_returnCarName() {
        // given
        Car car = new Car("pobi");
        String expected = "pobi";

        // when
        String actual = car.getName();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    // 아래는 예외에 관한 테스트코드입니다.

    @Test
    @DisplayName("자동차 이름이 비어있으면 예외가 발생한다")
    void createCar_throwsException_whenNameIsBlank() {
        // given
        String carName = "  ";

        // when & then
        assertThatThrownBy(() -> new Car(carName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1자 이상 5자 이하여야 합니다");
    }

    @Test
    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    void createCar_throwsException_whenNameIsTooLong() {
        // given
        String carName = "helloMyNameIsPobi";

        // when & then
        assertThatThrownBy(() -> new Car(carName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름은 1자 이상 5자 이하여야 합니다");
    }
}