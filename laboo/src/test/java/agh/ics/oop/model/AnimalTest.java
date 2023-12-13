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
        assertEquals("N", pet.toString());
    }

    @Test
    public void testIsAt() {
        Animal pet = new Animal();
        Vector2d position = new Vector2d(2, 2);

        assertTrue(pet.isAt(position));
    }

    @Test
    public void testMoveForward() {
        MoveValidator validator = new RectangularMap();
        Animal pet = new Animal();
        try {
            pet.move(MoveDirection.FORWARD, validator);
            assertEquals(new Vector2d(2, 3), pet.getPosition());
            pet.move(MoveDirection.FORWARD, validator);
            assertEquals(new Vector2d(2, 4), pet.getPosition());
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMoveRightLeft() {
        MoveValidator validator = new RectangularMap();
        Animal pet = new Animal();
        try{
            pet.move(MoveDirection.RIGHT, validator);
            assertEquals(MapDirection.EAST, pet.getDirection());
            pet.move(MoveDirection.LEFT, validator);
            assertEquals(MapDirection.NORTH, pet.getDirection());

        } catch (PositionAlreadyOccupiedException e) {
        System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMoveBackward() {
        MoveValidator validator = new RectangularMap();
        Animal pet = new Animal();
        try {
            pet.move(MoveDirection.BACKWARD, validator);
            pet.move(MoveDirection.BACKWARD, validator);
            pet.move(MoveDirection.BACKWARD, validator);
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(new Vector2d(2, 0), pet.getPosition());
    }

    @Test
    public void testMoveForwardWithinBounds() {
        MoveValidator validator = new RectangularMap();
        Animal pet = new Animal(new Vector2d(4, 4));

        try {
            pet.move(MoveDirection.FORWARD, validator);
            assertEquals(new Vector2d(4, 4), pet.getPosition());
            pet.move(MoveDirection.RIGHT, validator);
            pet.move(MoveDirection.FORWARD, validator);
            assertEquals(new Vector2d(4, 4), pet.getPosition());
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testMoveBackwardWithinBounds() {
        MoveValidator validator = new RectangularMap();
        Animal pet = new Animal(new Vector2d(0, 0));

        try {
            pet.move(MoveDirection.BACKWARD, validator);
            assertEquals(new Vector2d(0, 0), pet.getPosition());
            pet.move(MoveDirection.RIGHT, validator);
            pet.move(MoveDirection.BACKWARD, validator);
            assertEquals(new Vector2d(0, 0), pet.getPosition());
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
    }
}