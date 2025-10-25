package racingcar;

import racingcar.config.AppConfig;
import racingcar.domain.GameConsole;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        GameConsole game = appConfig.gameConsole();
        game.play();
    }
}
