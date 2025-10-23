package racingcar.domain;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private final int position;

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
}
