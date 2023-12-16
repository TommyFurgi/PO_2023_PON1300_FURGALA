package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {

    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
            WorldMap worldMap = new GrassField(10);
            Simulation simulation = new Simulation(directions, positions, worldMap);
            simulation.run();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
    }

    static void run(List<MoveDirection> args) {
        for (int i = 0; i < args.size(); i++) {
            switch (args.get(i)) {
                case FORWARD:
                    System.out.print("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.print("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.print("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    System.out.print("Zwierzak skręca w lewo");
                    break;
            }

            if (i != args.size()-1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
