package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.OptionsParser.parse;
import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
//    "parse()" is function in OptionsParser class
    @Test
    public void testParseSingleCommands(){
        //GIVEN
        String[] args = {"f", "f", "r", "l"};
        List<MoveDirection> expected_result = new ArrayList<>(Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        ));
        //WHEN
        List<MoveDirection> function_result = parse(args);
        //THEN
        assertEquals(expected_result,function_result);
    }

    @Test
    public void testParseMultipleCommands(){
        String[] args = {"ff", "f", "b", "rl", "l", "rr", "r", "fb"};
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args));
    }

    @Test
    public void testParseNoCommands(){
        String[] args = {};
        List<MoveDirection> expected_result = new ArrayList<>();
        List<MoveDirection> function_result =  parse(args);

        assertEquals(expected_result,function_result);
    }

    @Test
    public void testParseAllWrongCommands(){
        String[] args = {"ff", "fight", "back", "rl", "lamp", "rr", "right", "fb"};
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args));
    }
}
