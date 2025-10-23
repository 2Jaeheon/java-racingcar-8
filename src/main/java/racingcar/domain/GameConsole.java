package racingcar.domain;

import camp.nextstep.edu.missionutils.Console;

public class GameConsole {

    private static final String CAR_NAMES_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ROUND_PROMPT = "시도할 횟수는 몇 회인가요?";

    private String read() {
        return Console.readLine();
    }

    private void print(String message) {
        System.out.println(message);
    }

    public void play() {
        print(CAR_NAMES_PROMPT);
        String carNames = read();

        print(ROUND_PROMPT);
        int rounds = getRounds();
    }

    private int getRounds() {
        try {
            String roundsInput = read();
            int rounds = Integer.parseInt(roundsInput);

            if (rounds < 0) {
                throw new IllegalArgumentException();
            }

            return rounds;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
