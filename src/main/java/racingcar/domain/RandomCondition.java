package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomCondition implements MoveCondition {
    private static final int MOVE_THRESHOLD = 4;
    private static final int MAX_RANDOM_NUMBER = 10;


    @Override
    public boolean shouldMove() {
        int randomNumber = Randoms.pickNumberInRange(0, MAX_RANDOM_NUMBER - 1);
        return randomNumber >= MOVE_THRESHOLD;
    }
}
