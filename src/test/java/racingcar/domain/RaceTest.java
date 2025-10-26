package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RaceTest {
    Race race;
    Car car1, car2, car3;

    @BeforeEach
    void SetUp() {
        car1 = new Car("pobi");
        car2 = new Car("lee");
        car3 = new Car("kim");

        car2.move();
        car3.move();
        car3.move();

        List<Car> cars = Arrays.asList(car1, car2, car3);
        // 테스트를 위해 항상 움직이는 strategy를 설정
        MoveStrategy alwaysMoveStrategy = new MoveStrategy() {
            @Override
            public void attemptMove(Car car) {
                car.move();
            }
        };
        int totalRound = 5;

        race = new Race(cars, alwaysMoveStrategy, totalRound);
    }

    @Test
    @DisplayName("findWinners()는 현재 위치가 가장 높은 우승자를 반환한다")
    void findWinners_returnWinner() {
        // when
        List<String> winners = race.findWinners();

        // then
        assertThat(winners).containsExactly("kim");
    }

    @Test
    @DisplayName("findWinners는 공동 우승자가 있으면 모두 반환한다")
    void findWinners_returnAllWinners_whenDraw() {
        // given
        car1.move();
        car1.move();

        // when
        List<String> winners = race.findWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("pobi", "kim");
    }

    @Test
    @DisplayName("playRound는 무조건 움직이는 전략일 때, 모든 차를 1칸씩 전진시킨다")
    void playRound() {
        // when
        // AlwaysMoveStrategy 에 따라서 이동하도록 설정했음
        race.playRound();

        // then
        assertThat(car1.getPosition()).isEqualTo(1);
        assertThat(car2.getPosition()).isEqualTo(2);
        assertThat(car3.getPosition()).isEqualTo(3);
    }

    @Test
    @DisplayName("isFinished()는 총 라운드보다 적게 playRound가 호출되면 false를 반환한다")
    void isFinished_returnFalse_whenBeforeTotalRounds() {
        // given
        race.playRound();
        race.playRound();
        race.playRound();
        race.playRound();

        // when
        boolean finished = race.isFinished();

        // then
        assertThat(finished).isFalse();
    }

    @Test
    @DisplayName("isFinished()는 총 라운드만큼 playRound()가 호출되면 true를 반환한다")
    void isFinished_returnsTrue_whenRoundsAreCompleted() {
        // given
        race.playRound();
        race.playRound();
        race.playRound();
        race.playRound();
        race.playRound();

        // when
        boolean finished = race.isFinished();

        // then
        assertThat(finished).isTrue();
    }

    @Test
    void getCars() {
        // when
        List<Car> cars = race.getCars();

        // then
        assertThat(cars).hasSize(3);
        assertThat(cars).containsExactly(car1, car2, car3);
    }
}