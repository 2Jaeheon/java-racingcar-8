package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomStrategy implements MoveStrategy {
    private static final int MOVE_THRESHOLD = 4;
    private static final int MAX_RANDOM_NUMBER = 10;


    @Override
    public boolean shouldMove() {
        int random = Randoms.pickNumberInRange(0, MAX_RANDOM_NUMBER - 1);
        return random >= MOVE_THRESHOLD;
    }
}
