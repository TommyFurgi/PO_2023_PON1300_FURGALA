package agh.ics.oop.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    private RectangularMap map;
    private Animal animal;

    @BeforeEach
    public void setUp() {
        this.map = new RectangularMap(5, 5);
        this.animal = new Animal(new Vector2d(2, 3));

        try {
            map.place(animal);
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testCanMoveToValidPosition() {
        assertTrue(map.canMoveTo(new Vector2d(2, 4))); // Test a valid position
    }

    @Test
    public void testCanMoveToInvalidPosition() {
        assertFalse(map.canMoveTo(new Vector2d(-1, 3))); // Test an invalid position
    }

    @Test
    public void testPlaceAnimal() {
        assertEquals(animal,map.objectAt(new Vector2d(2, 3)));
    }


    @Test
    public void testMoveAnimal() {
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 4), animal.getPosition());
    }

    @Test
    public void testMoveBeyondMap() {
        map.move(animal, MoveDirection.FORWARD);
        assertFalse(map.canMoveTo(new Vector2d(2, 5)));
        assertEquals(new Vector2d(2, 4), animal.getPosition());
    }

    @Test
    public void testIsOccupied() {
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
        assertFalse(map.isOccupied(new Vector2d(3, 2)));
    }

    @Test
    public void testIsOccupiedAfterMove() {
        map.move(animal, MoveDirection.FORWARD);
        assertTrue(map.isOccupied(new Vector2d(2, 4)));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    public void testClearMapPositionAfterMove() {
        map.move(animal, MoveDirection.FORWARD);
        assertNull(map.objectAt(new Vector2d(2, 3)));
        assertEquals(animal, map.objectAt(new Vector2d(2, 4)));
    }


    @Test
    public void testObjectAt() {
        assertEquals(animal, map.objectAt(new Vector2d(2, 3)));
        assertNull(map.objectAt(new Vector2d(3, 2)));
    }
}