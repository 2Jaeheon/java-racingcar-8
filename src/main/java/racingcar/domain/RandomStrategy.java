package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomStrategy implements MoveStrategy {
    private static final int MOVE_THRESHOLD = 4;
    private static final int MAX_RANDOM_NUMBER = 9;

    @Override
    public void attemptMove(Car car) {
        int randomNumber = Randoms.pickNumberInRange(0, MAX_RANDOM_NUMBER);
        if (randomNumber >= MOVE_THRESHOLD) {
            car.move();
        }
    }
}
