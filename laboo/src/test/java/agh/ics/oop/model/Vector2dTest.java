package agh.ics.oop.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void testEquals(){
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(1, 3);
        Vector2d vector3 = new Vector2d(4, 3);

        assertTrue(vector1.equals(vector2));
        assertFalse(vector1.equals(vector3));
    }

    @Test
    public void testToString(){
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(5, 3);
        Vector2d vector3 = new Vector2d(4, 5);

        assertEquals(vector1.toString(), "(1,3)");
        assertEquals(vector2.toString(), "(5,3)");
        assertEquals(vector3.toString(), "(4,5)");
    }

    @Test
    public void testPrecedes(){
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(5, 3);
        Vector2d vector3 = new Vector2d(4, 5);

        assertTrue(vector1.precedes(vector1));
        assertTrue(vector1.precedes(vector2));
        assertTrue(vector1.precedes(vector3));
        assertFalse(vector3.precedes(vector2));
    }

    @Test
    public void testFollows(){
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(5, 3);
        Vector2d vector3 = new Vector2d(4, 5);

        assertTrue(vector1.follows(vector1));
        assertTrue(vector2.follows(vector1));
        assertTrue(vector3.follows(vector1));
        assertFalse(vector3.follows(vector2));
    }

    @Test
    public void testUpperRight(){
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(5, 3);
        Vector2d vector3 = new Vector2d(4, 5);

        assertEquals(new Vector2d(5,3),vector1.upperRight(vector2));
        assertEquals(new Vector2d(5,5),vector3.upperRight(vector2));
        assertEquals(new Vector2d(4,5),vector1.upperRight(vector3));
    }
    @Test
    public void testLowerLeft(){
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(5, 3);
        Vector2d vector3 = new Vector2d(4, 5);

        assertEquals(new Vector2d(1,3),vector1.lowerLeft(vector2));
        assertEquals(new Vector2d(4,3),vector3.lowerLeft(vector2));
        assertEquals(new Vector2d(1,3),vector3.lowerLeft(vector1));
    }

    @Test
    public void testAdd(){
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(5, 3);
        Vector2d vector3 = new Vector2d(4, 5);

        assertEquals(new Vector2d(6,6),vector1.add(vector2));
        assertEquals(new Vector2d(9,8),vector3.add(vector2));
        assertEquals(new Vector2d(5,8),vector3.add(vector1));
    }

    @Test
    public void testSubtract(){
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(5, -3);
        Vector2d vector3 = new Vector2d(-4, 0);

        assertEquals(new Vector2d(-4,6),vector1.subtract(vector2));
        assertEquals(new Vector2d(-9,3),vector3.subtract(vector2));
        assertEquals(new Vector2d(-5,-3),vector3.subtract(vector1));
    }

    @Test
    public void testOpposite(){
        Vector2d vector1 = new Vector2d(1, 3);
        Vector2d vector2 = new Vector2d(5, -3);
        Vector2d vector3 = new Vector2d(-4, 0);

        assertEquals(new Vector2d(-1,-3),vector1.opposite());
        assertEquals(new Vector2d(-5,3),vector2.opposite());
        assertEquals(new Vector2d(4,0),vector3.opposite());
    }
}
