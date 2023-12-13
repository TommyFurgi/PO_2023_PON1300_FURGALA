package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) throws IllegalArgumentException {
        return Arrays.stream(args)
                .map(OptionsParser::mapToMoveDirection)
                .collect(Collectors.toList());
    }

    private static MoveDirection mapToMoveDirection(String arg) throws IllegalArgumentException {
        MoveDirection direction;
        switch (arg) {
            case "f" -> direction = MoveDirection.FORWARD;
            case "b" -> direction = MoveDirection.BACKWARD;
            case "r" -> direction = MoveDirection.RIGHT;
            case "l" -> direction = MoveDirection.LEFT;
            default -> throw new IllegalArgumentException(arg + " is not a legal move specification");
        }
        return direction;
    }
}
