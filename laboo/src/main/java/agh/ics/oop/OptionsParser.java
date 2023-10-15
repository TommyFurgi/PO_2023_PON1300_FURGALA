package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.Arrays;
public class OptionsParser {
    public static MoveDirection[] change(String[] args) {
        MoveDirection[] arg = new MoveDirection[args.length];

        int cnt = 0;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "f" -> arg[i-cnt] = MoveDirection.FORWARD;
                case "b" -> arg[i-cnt] = MoveDirection.BACKWARD;
                case "r" -> arg[i-cnt] = MoveDirection.RIGHT;
                case "l" -> arg[i-cnt] = MoveDirection.LEFT;
                default ->  cnt+=1;
                }
            }

        if (cnt==0) {
            MoveDirection[] res = {};
            return res;
        }
        MoveDirection[] result = Arrays.copyOfRange(arg, 0, args.length-cnt);

        return result;
    }
}
