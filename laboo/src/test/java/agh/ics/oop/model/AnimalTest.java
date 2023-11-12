package agh.ics.oop.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    public void testConstructorWithDefaultPosition() {
        Animal pet = new Animal();

        assertEquals(MapDirection.NORTH, pet.getDirection());
        assertEquals(new Vector2d(2, 2), pet.getPosition());
    }
    @Test
    public void testConstructorWithCustomPosition() {
        Vector2d customPosition = new Vector2d(3, 3);
        Animal pet = new Animal(customPosition);

        assertEquals(MapDirection.NORTH, pet.getDirection());
        assertEquals(customPosition, pet.getPosition());
    }

    @Test
    public void testToString() {
        Animal pet = new Animal();
        assertEquals("NORTH (2,2)", pet.toString());
    }

    @Test
    public void testIsAt() {
        Animal pet = new Animal();
        Vector2d position = new Vector2d(2, 2);

        assertTrue(pet.isAt(position));
    }

    @Test
    public void testMoveForward() {
        Animal pet = new Animal();
        pet.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(2, 3), pet.getPosition());
        pet.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 4), pet.getPosition());
    }

    @Test
    public void testMoveRightLeft() {
        Animal pet = new Animal();
        pet.move(MoveDirection.RIGHT);

        assertEquals(MapDirection.EAST, pet.getDirection());
        pet.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, pet.getDirection());
    }

    @Test
    public void testMoveBackward() {
        Animal pet = new Animal();
        pet.move(MoveDirection.BACKWARD);
        pet.move(MoveDirection.BACKWARD);
        pet.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(2, 0), pet.getPosition());
    }

    @Test
    public void testMoveForwardWithinBounds() {
        Animal pet = new Animal(new Vector2d(4, 4));

        pet.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4, 4), pet.getPosition());
        pet.move(MoveDirection.RIGHT);
        pet.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4, 4), pet.getPosition());
    }

    @Test
    public void testMoveBackwardWithinBounds() {
        Animal pet = new Animal(new Vector2d(0, 0));

        pet.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0, 0), pet.getPosition());
        pet.move(MoveDirection.RIGHT);
        pet.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0, 0), pet.getPosition());
    }
}