package racingcar.view;

import java.util.List;
import racingcar.dto.CarStatus;

public class OutputView {
    private static final String RESULT_MESSAGE = "\n실행 결과";
    private static final String WINNER_MESSAGE_PREFIX = "최종 우승자 : ";
    private static final String COMMA = ",";
    private static final String COLON = " : ";
    private static final String PROGRESS = "-";

    public void printRaceStart() {
        System.out.println(RESULT_MESSAGE);
    }

    public void printRaceRound(List<CarStatus> carStatuses) {
        for (CarStatus car : carStatuses) {
            String name = car.getName();
            int position = car.getPosition();
            System.out.println(name + COLON + PROGRESS.repeat(position));
        }
        System.out.println();
    }

    public void printWinner(List<String> winnersName) {
        String winners = String.join(COMMA, winnersName);
        System.out.println(WINNER_MESSAGE_PREFIX + winners);
    }
}
