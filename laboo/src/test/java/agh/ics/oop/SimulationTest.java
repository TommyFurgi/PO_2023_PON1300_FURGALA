package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulationTest {
    private Simulation simulation;

    @Test
    public void testRunSimulation() {
//         GIVEN
        String[] startArgs = {"f", "ff", "r", "l", "f", "fb", "b", "l", "b", "f"};
        List<MoveDirection> directions = OptionsParser.parse(startArgs);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 3));
        WorldMap worldMap = new RectangularMap();
//        WHEN
        simulation = new Simulation(new ArrayList<>(directions), new ArrayList<>(positions), worldMap);
        simulation.run();
//        THEN
        List<Animal> animals = simulation.getAnimals();
        assertEquals(new Vector2d(3, 3), animals.get(0).getPosition());
        assertEquals(new Vector2d(4, 4), animals.get(1).getPosition());
    }

    @Test
    public void testRunSimulationWithNoAnimals() {
        String[] startArgs = {"f", "f", "r", "l", "f"};
        List<MoveDirection> directions = OptionsParser.parse(startArgs);
        List<Vector2d> positions = new ArrayList<>();
        WorldMap worldMap = new RectangularMap();
        simulation = new Simulation(new ArrayList<>(directions), new ArrayList<>(positions), worldMap);

        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.isEmpty());
    }

    @Test
    public void testRunSimulationWithNoMoves() {
        String[] startArgs = {"fog", "fight", "row", "left", "frog", "lollipop", "bag", "lamp", "brown", "fries"};
        List<MoveDirection> directions = OptionsParser.parse(startArgs);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 3));
        WorldMap worldMap = new RectangularMap();
        simulation = new Simulation(new ArrayList<>(directions), new ArrayList<>(positions), worldMap);
        simulation.run();


        List<Animal> animals = simulation.getAnimals();
        assertTrue(directions.isEmpty());
        assertEquals(new Vector2d(2, 2), animals.get(0).getPosition());
        assertEquals(new Vector2d(3, 3), animals.get(1).getPosition());
    }
    @Test

    public void testRunSimulationWithNoStrings() {
        String[] startArgs = {};
        List<MoveDirection> directions = OptionsParser.parse(startArgs);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 3));
        WorldMap worldMap = new RectangularMap();
        simulation = new Simulation(new ArrayList<>(directions), new ArrayList<>(positions), worldMap);
        simulation.run();


        List<Animal> animals = simulation.getAnimals();
        assertTrue(directions.isEmpty());
        assertEquals(new Vector2d(2, 2), animals.get(0).getPosition());
        assertEquals(new Vector2d(3, 3), animals.get(1).getPosition());
    }


    @Test
    public void testRunSimulationNextToBorder() {
        String[] startArgs = {"f", "r", "f", "r", "f", "f", "f", "f", "f", "r", "f", "f", "f", "f", "f"};
        List<MoveDirection> directions = OptionsParser.parse(startArgs);
        List<Vector2d> positions = new ArrayList<>(List.of(new Vector2d(4, 4)));
        WorldMap worldMap = new RectangularMap();
        simulation = new Simulation(new ArrayList<>(directions), new ArrayList<>(positions), worldMap);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertEquals(new Vector2d(0, 0), animals.get(0).getPosition());
    }

    @Test
    public void testRunSimulationCheckDirections() {
        String[] startArgs = {"r", "r", "l", "r", "l", "r", "l"};
        List<MoveDirection> directions = OptionsParser.parse(startArgs);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 3));
        WorldMap worldMap = new RectangularMap();
        simulation = new Simulation(new ArrayList<>(directions), new ArrayList<>(positions), worldMap);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertEquals(MapDirection.SOUTH, animals.get(0).getDirection());
        assertEquals(MapDirection.WEST, animals.get(1).getDirection());
    }

    @Test
    public void testRunSimulationWithWrongAnimalsPosition() {
        String[] startArgs = {"f", "f", "r", "l", "f"};
        List<MoveDirection> directions = OptionsParser.parse(startArgs);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2, 2), new Vector2d(3, 5), new Vector2d(5, 0));
        WorldMap worldMap = new RectangularMap();
        simulation = new Simulation(new ArrayList<>(directions), new ArrayList<>(positions), worldMap);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertEquals(1, simulation.getAnimals().size());
        assertEquals(MapDirection.NORTH, animals.get(0).getDirection());
        assertEquals(new Vector2d(2, 4), animals.get(0).getPosition());
    }
}