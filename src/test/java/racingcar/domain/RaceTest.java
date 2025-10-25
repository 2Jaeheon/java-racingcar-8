package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarStatus;

class RaceTest {

    MoveCondition alwaysMoveCondition;
    MoveCondition neverMoveCondition;

    private Car pobi;
    private Car woni;
    private Car jun;

    @BeforeEach
    void setUp() {
        pobi = new Car("pobi");
        woni = new Car("woni");
        jun = new Car("jun");

        alwaysMoveCondition = new MoveCondition() {
            @Override
            public boolean shouldMove() {
                return true;
            }
        };

        neverMoveCondition = new MoveCondition() {
            @Override
            public boolean shouldMove() {
                return false;
            }
        };
    }

    @Test
    @DisplayName("Race 생성 시 자동차가  없으면 예외가 발생한다")
    void createRace_returnException_NotAnyCar() {
        // given
        List<Car> emptyList = Collections.emptyList(); // 빈 리스트

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Race(emptyList));
    }

    @Test
    @DisplayName("moveAll() 실행 시 모든 자동차가 항상 움직이는 전략에 따라 움직인다")
    void moveAll_alwaysMovesAllCar() {
        // given
        Race race = new Race(List.of(pobi, woni));

        // when
        race.moveAll(alwaysMoveCondition);

        // then
        assertThat(pobi.getPosition()).isEqualTo(1);
        assertThat(woni.getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("moveAll() 실행 시 모든 자동차가 움직이지 않는 전략에 따라 움직이지 않는다")
    void moveAll_neverMovesAllCar() {
        // given
        Race race = new Race(List.of(pobi, woni, jun));

        // when
        race.moveAll(neverMoveCondition);

        // then
        assertThat(pobi.getPosition()).isEqualTo(0);
        assertThat(woni.getPosition()).isEqualTo(0);
        assertThat(jun.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("findWinners()는 가장 많이 움직인 우승자를 반환한다")
    void extractWinners_longestMovesCar() {
        // given
        woni.move(alwaysMoveCondition);
        Race race = new Race(List.of(pobi, woni, jun));

        // when
        List<String> winners = race.findWinners();

        // then
        assertThat(winners).containsExactly("woni");
    }

    @Test
    @DisplayName("findWinners()는 가장 많이 전진한 공동 우승자들을 반환한다")
    void extractWinners_multiWinners() {
        // given
        pobi.move(alwaysMoveCondition);
        woni.move(alwaysMoveCondition);
        Race race = new Race(List.of(pobi, woni, jun));

        // when
        List<String> winners = race.findWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("woni", "pobi");
    }

    @Test
    @DisplayName("getStatuses()는 모든 자동차의 현재 상태를 정확히 반환한다")
    void getStatuses_allCarStatus() {
        // given
        pobi.move(alwaysMoveCondition);
        pobi.move(alwaysMoveCondition);
        woni.move(alwaysMoveCondition);

        Race race = new Race(List.of(pobi, woni, jun));

        // when
        List<CarStatus> cars = race.getRaceStatus();

        // then
        assertThat(cars.get(0).getName()).isEqualTo("pobi");
        assertThat(cars.get(0).getPosition()).isEqualTo(2);

        assertThat(cars.get(1).getName()).isEqualTo("woni");
        assertThat(cars.get(1).getPosition()).isEqualTo(1);

        assertThat(cars.get(2).getName()).isEqualTo("jun");
        assertThat(cars.get(2).getPosition()).isEqualTo(0);
    }

}