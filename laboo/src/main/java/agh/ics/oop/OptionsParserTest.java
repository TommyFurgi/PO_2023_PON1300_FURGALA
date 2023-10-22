package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import static agh.ics.oop.OptionsParser.change;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class OptionsParserTest {
//    "Change()" is funcion in OprtionsParser class
    @Test
    public void testChangeSingleCommands(){
        //GIVEN
        String[] args = {"f", "f", "r", "l"};
        MoveDirection[] expected_result = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        //WHEN
        MoveDirection[] funcion_result = change(args);
        //THEN
        assertEquals(expected_result,funcion_result);
    }

    @Test
    public void testChangeMultipleCommandss(){
        String[] args = {"ff", "f", "b", "rl", "l", "rr", "r", "fb"};
        MoveDirection[] expected_result = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        MoveDirection[] funcion_result = change(args);

        assertEquals(expected_result,funcion_result);
    }

    @Test
    public void testChangeNoCommandss(){
        String[] args = {"ff", "p", "k", "rl", "o", "rr", "e", "fb"};
        MoveDirection[] expected_result = {};
        MoveDirection[] funcion_result = change(args);

        assertEquals(expected_result,funcion_result);
    }
}
