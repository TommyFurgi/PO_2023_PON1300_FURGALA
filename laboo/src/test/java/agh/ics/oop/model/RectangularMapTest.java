package agh.ics.oop.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    public void testCanMoveToValidPosition() {
        RectangularMap map = new RectangularMap();
        assertTrue(map.canMoveTo(new Vector2d(2, 3))); // Test a valid position
    }

    @Test
    public void testCanMoveToInvalidPosition() {
        RectangularMap map = new RectangularMap(5, 5);
        assertFalse(map.canMoveTo(new Vector2d(-1, 3))); // Test an invalid position
    }

    @Test
    public void testPlaceAnimal() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 3));
        assertTrue(map.place(animal));
    }


    @Test
    public void testMoveAnimal() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 3));
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 4), animal.getPosition());
    }

    @Test
    public void testMoveBeyondMap() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 4));
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertFalse(map.canMoveTo(new Vector2d(2, 5)));
        assertEquals(new Vector2d(2, 4), animal.getPosition());
    }

    @Test
    public void testIsOccupied() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 3));
        map.place(animal);
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
        assertFalse(map.isOccupied(new Vector2d(3, 2)));
    }

    @Test
    public void testIsOccupiedAfterMove() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 3));
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertTrue(map.isOccupied(new Vector2d(2, 4)));
        assertFalse(map.isOccupied(new Vector2d(2, 3)));
    }

    @Test
    public void testClearMapPositionAfterMove() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 3));
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertNull(map.objectAt(new Vector2d(2, 3)));
        assertEquals(animal, map.objectAt(new Vector2d(2, 4)));
    }


    @Test
    public void testObjectAt() {
        RectangularMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(new Vector2d(2, 3));
        map.place(animal);
        assertEquals(animal, map.objectAt(new Vector2d(2, 3)));
        assertNull(map.objectAt(new Vector2d(3, 2)));
    }
}