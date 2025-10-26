package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    @DisplayName("기능 테스트 ")
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("자동차 이름은 1자 이상 5자 이하여야 합니다")
        );
    }

    // 아래는 위의 기본 테스트 메서드 이외에 추가로 작성한 테스트입니다
    @Test
    @DisplayName("pobi는 전진하고, woni는 멈춘다")
    void integrationTest_success() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");

                    assertThat(output()).contains(
                            "실행 결과",
                            "pobi : -",
                            "woni : ",
                            "최종 우승자 : pobi"
                    );
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("pobi, woni는 전진하고 jun은 멈춘다")
    void integrationTest_success_InMultiOutput() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "1");

                    assertThat(output()).contains(
                            "실행 결과",
                            "pobi : -",
                            "woni : -",
                            "jun : ",
                            "최종 우승자 : pobi, woni"
                    );
                },
                MOVING_FORWARD, MOVING_FORWARD, STOP
        );
    }

    // 아래는 예외에 대한 테스트입니다.

    @Test
    @DisplayName("예외 테스트: 자동차 이름이 5자를 초과하는 경우")
    void exceptionTest_carNameLong() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woniIsGood!", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("자동차 이름은 1자 이상 5자 이하여야 합니다")
        );
    }

    @Test
    @DisplayName("예외 테스트: 시도 횟수가 숫자가 아닌 경우")
    void exceptionTest_roundIsNotNumber() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni", "a")) // "a"는 숫자가 아님
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("시도 횟수는 숫자여야 합니다")
        );
    }

    @Test
    @DisplayName("예외 테스트: 자동차 이름 중 비어있는 이름이 있는 경우")
    void exceptionTest_carNameContainsBlank() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,,woni", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("자동차 이름은 1자 이상 5자 이하여야 합니다") // Car 생성자 예외
        );
    }

    @Test
    @DisplayName("예외 테스트: 자동차 이름 입력을 비우는 경우")
    void exceptionTest_carNameIsEmpty() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("", "1")) // 이름 입력을 비움
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("자동차 이름이 입력되지 않았습니다")
        );
    }

    @Test
    @DisplayName("예외 테스트: 자동차 이름 중 비어있는 이름이 있는 경우")
    void exceptionTest_carNameIsWhiteSpace() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("  ", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("자동차 이름이 입력되지 않았습니다") // Car 생성자 예외
        );
    }

    @Test
    @DisplayName("예외 테스트: 시도 횟수가 0 이하인 경우")
    void exceptionTest_roundIsZeroOrLess() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni", "-1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("시도 횟수는 1 이상이어야 합니다")
        );
    }

    @Test
    @DisplayName("예외 테스트: 시도 횟수가 null인 경우")
    void exceptionTest_roundIsNull() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni", null))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("시도 횟수는 숫자여야 합니다")
        );
    }

    @Test
    @DisplayName("예외 테스트: 자동차 이름이 중복되는 경우")
    void exceptionTest_DuplicateCarNames() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni,pobi", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("자동차 이름은 중복될 수 없습니다")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
