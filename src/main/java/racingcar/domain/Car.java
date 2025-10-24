package racingcar.domain;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;

    private static final String ERROR_INVALID_NAME = "자동차 이름은 1자 이상 5자 이하여야 합니다";

    private final String name;
    private int position;

    public Car(String name) {
        validateName(name);
        this.name = name;
        this.position = 0;
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
        String normalizedName = name.trim();
        if (normalizedName.length() > MAX_NAME_LENGTH || normalizedName.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public void move(MoveStrategy moveStrategy) {
        if (moveStrategy.shouldMove()) {
            this.position++;
        }
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
