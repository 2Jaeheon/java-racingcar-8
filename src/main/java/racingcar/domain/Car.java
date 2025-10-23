package racingcar.domain;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final String POSITION = "-";
    private static final String COLON = ":";

    private final String name;
    private int position;

    public Car(String name) {
        validateName(name);
        this.name = name;
        this.position = 0;
    }

    private void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        name = name.trim();
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    public void move(MoveStrategy moveStrategy) {
        if (moveStrategy.shouldMove()) {
            this.position++;
        }
    }

    public String getStatus() {
        return name + COLON + POSITION.repeat(position);
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
