package agh.ics.oop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    private GrassField grasses;
    private Grass grass;

    @BeforeEach
    public void setUp() {
        this.grasses = new GrassField(0);
        this.grass = new Grass(new Vector2d(2, 3));
        try {
            grasses.placeGrass(grass);
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void placeAnimalOnGrass() {
        Animal animal = new Animal(new Vector2d(2, 3));
        grasses.place(animal);


        assertEquals(animal, grasses.objectAt(new Vector2d(2, 3)));
    }

    @Test
    void canMoveToGrassField() {
        Animal animal = new Animal(new Vector2d(2, 3));

        grasses.place(animal);
        grasses.move(animal, MoveDirection.FORWARD);

        assertEquals(animal, grasses.objectAt(new Vector2d(2, 4)));
    }

    @Test
    void endlessMap() {
        Animal animal = new Animal(new Vector2d(2, 3));


        grasses.place(animal);

        grasses.move(animal, MoveDirection.FORWARD);
        grasses.move(animal, MoveDirection.FORWARD);
        grasses.move(animal, MoveDirection.FORWARD);
        grasses.move(animal, MoveDirection.FORWARD);
        grasses.move(animal, MoveDirection.FORWARD);


        assertEquals(animal, grasses.objectAt(new Vector2d(2, 8)));
    }

    @Test
    void showUpGrassAfterAnimalMove() {
        Animal animal = new Animal(new Vector2d(2, 3));

        grasses.place(animal);
        grasses.move(animal, MoveDirection.FORWARD);

        assertEquals(grass, grasses.objectAt(new Vector2d(2, 3)));
        assertEquals(animal, grasses.objectAt(new Vector2d(2, 4)));
    }

    @Test
    void isOccupied() {
        Animal animal = new Animal(new Vector2d(2, 3));

        grasses.place(animal);
        grasses.move(animal, MoveDirection.FORWARD);

        assertTrue(grasses.isOccupied(new Vector2d(2, 3)));
        assertTrue(grasses.isOccupied(new Vector2d(2, 4)));
        assertFalse(grasses.isOccupied(new Vector2d(2, 1)));
    }

    @Test
    void objectAt() {
        Grass grass1 = new Grass(new Vector2d(2, 5));
        Grass grass2 = new Grass(new Vector2d(1, 0));
        try {
            grasses.placeGrass(grass1);
            grasses.placeGrass(grass2);
        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
        Animal animal = new Animal(new Vector2d(2, 3));

        grasses.place(animal);

        assertEquals(grass1, grasses.objectAt(new Vector2d(2, 5)));
        assertEquals(grass2, grasses.objectAt(new Vector2d(1, 0)));
        assertEquals(animal, grasses.objectAt(new Vector2d(2, 3)));
    }

    @Test
    void cannotMoveToOccupiedField() {
        Animal animal1 = new Animal(new Vector2d(2, 3));
        Animal animal2 = new Animal(new Vector2d(2, 4));

        grasses.place(animal1);
        grasses.place(animal2);


        boolean canMove = grasses.canMoveTo(animal1.getPosition().add(MapDirection.NORTH.toUnitVector()));

        // Assert
        assertFalse(canMove);
    }

    @Test
    void animalDoesNotLeaveTrail() {
        // Arrange
        Animal animal = new Animal(new Vector2d(2, 3));

        // Act
        grasses.place(animal);
        grasses.move(animal, MoveDirection.FORWARD);

        // Assert
        assertTrue(grasses.isOccupied(new Vector2d(2, 3)));
        assertEquals(grass, grasses.objectAt(new Vector2d(2, 3)));
    }

    @Test
    public void testGettingOrderedAnimalsWithNoMoves() {
        Animal animal1 = new Animal(new Vector2d(2, 3));
        Animal animal2 = new Animal(new Vector2d(2, 4));

        grasses.place(animal1);
        grasses.place(animal2);


        List<Animal> animalListLambda = grasses.getOrderedAnimalsWithLambda();
        List<Animal> animalListStreams = grasses.getOrderedAnimalsWithStreams();
        assertEquals(animalListLambda.get(0), animal1);
        assertEquals(animalListLambda.get(1), animal2);
        assertEquals(animalListStreams.get(0), animal1);
        assertEquals(animalListStreams.get(1), animal2);
    }

    @Test
    public void testGettingOrderedAnimalsWithMoves() {
        Animal animal1 = new Animal(new Vector2d(2, 3));
        Animal animal2 = new Animal(new Vector2d(3, 4));

        grasses.place(animal1);
        grasses.place(animal2);


        grasses.move(animal1, MoveDirection.RIGHT);
        grasses.move(animal1, MoveDirection.FORWARD);
        grasses.move(animal1, MoveDirection.FORWARD);
        grasses.move(animal2, MoveDirection.BACKWARD);

        List<Animal> animalListLambda = grasses.getOrderedAnimalsWithLambda();
        List<Animal> animalListStreams = grasses.getOrderedAnimalsWithStreams();
        assertEquals(animalListLambda.get(0), animal2);
        assertEquals(animalListLambda.get(1), animal1);
        assertEquals(animalListStreams.get(0), animal2);
        assertEquals(animalListStreams.get(1), animal1);
    }

    @Test
    public void testGetElemnts() {
        Animal animal1 = new Animal(new Vector2d(2, 3));
        Animal animal2 = new Animal(new Vector2d(3, 4));

        grasses.place(animal1);
        grasses.place(animal2);

        List<WorldElement> elements = (List<WorldElement>) grasses.getElements();

        Assertions.assertNotNull(elements);
        assertEquals(3, elements.size());
    }
}