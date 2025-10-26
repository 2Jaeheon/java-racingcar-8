package racingcar.domain;

public interface MoveStrategy {
    /**
     * 주어진 자동차를 현재 전략에 따라 이동시킵니다.
     * (예: 랜덤 숫자를 생성하고, 4 이상일 때만 car.move()를 호출)
     *
     * @param car 이동을 시도할 자동차 객체
     */
    void attemptMove(Car car);
}
