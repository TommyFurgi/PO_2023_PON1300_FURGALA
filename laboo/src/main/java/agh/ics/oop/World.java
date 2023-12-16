package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static void main(String[] args) {
        try {
            List<Simulation> simulations = new ArrayList<>();
            for (int i = 0; i < 20000; i++) {
                List<MoveDirection> directions = OptionsParser.parse(new String[]{"b", "r", "f", "f"});
                List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(2, 3));
                WorldMap map = new RectangularMap();
                Simulation simulation = new Simulation(directions, positions, map);
                simulations.add(simulation);
            }

            SimulationEngine simulationEngine = new SimulationEngine(simulations);
            simulationEngine.runAsyncInThreadPool();

            System.out.println("END all Simulations");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
    }
}
