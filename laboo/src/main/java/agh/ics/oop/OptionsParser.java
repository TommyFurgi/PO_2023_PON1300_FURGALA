package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.Arrays;
public class OptionsParser {
    public static MoveDirection[] change(String[] args) {
        MoveDirection[] directions = new MoveDirection[args.length];

        int mismatch_word_counter = 0;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "f" -> directions[i-mismatch_word_counter] = MoveDirection.FORWARD;
                case "b" -> directions[i-mismatch_word_counter] = MoveDirection.BACKWARD;
                case "r" -> directions[i-mismatch_word_counter] = MoveDirection.RIGHT;
                case "l" -> directions[i-mismatch_word_counter] = MoveDirection.LEFT;
                default ->  mismatch_word_counter+=1;
                }
            }

        if (mismatch_word_counter==args.length) {
            MoveDirection[] res = {};
            return res;
        }
        MoveDirection[] result = Arrays.copyOfRange(directions, 0, args.length-mismatch_word_counter);

        return result;
    }
}
