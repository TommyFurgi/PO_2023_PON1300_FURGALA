package agh.ics.oop;

import agh.ics.oop.model.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Simulation implements Runnable{
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap map;

    public WorldMap getWorldMap() {
        return map;
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions, WorldMap map) {
        this.animals = new ArrayList<>();
        this.directions = directions;
        this.map = map;
        map.addMapChangeListener((WorldMap worldMap, String message) ->
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) +
                        " " + message));
        map.addMapChangeListener(new ConsoleMapDisplay());
        for (Vector2d startPosition: positions) {
            Animal newAnimal =new Animal(startPosition);
            this.animals.add(newAnimal);
            try {
                map.place(newAnimal);
            } catch (PositionAlreadyOccupiedException e) {
                System.out.println(e.getMessage());

            }
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int animalIndex = 0;
        int animals_size = animals.size();

        if (animals_size == 0)
            return;

        for (MoveDirection move : directions) {
            if (animalIndex < animals_size){
                Animal current_animal = animals.get(animalIndex);
                map.move(current_animal, move);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                animalIndex += 1;
            }
            animalIndex %= animals_size;
        }
    }
}
