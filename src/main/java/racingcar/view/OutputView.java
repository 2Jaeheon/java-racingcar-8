package racingcar.view;

import java.util.List;

public class OutputView {
    private static final String RESULT_MESSAGE = "\n실행 결과";
    private static final String WINNER_MESSAGE_PREFIX = "최종 우승자 : ";
    private static final String COMMA = ",";

    public void printRaceStart() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printRound(List<String> carStatuses) {
        carStatuses.forEach(System.out::println);
        System.out.println();
    }

    public void printWinner(List<String> winnersName) {
        String winners = String.join(COMMA, winnersName);
        System.out.println(WINNER_MESSAGE_PREFIX + winners);
    }
}
