package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final WorldMap Map;

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions, WorldMap map) {
        this.animals = new ArrayList<>();
        this.directions = directions;
        this.Map = map;
        for (Vector2d startPosition: positions) {
            if (map.canMoveTo(startPosition)) {
                Animal newAnimal =new Animal(startPosition);
                this.animals.add(newAnimal);
                Map.place(newAnimal);
            }
        }

    }

    public void run() {
        int animalIndex = 0;
        int animals_size = animals.size();
        for (MoveDirection move : directions) {
            if (animalIndex < animals_size){
                Animal current_animal = animals.get(animalIndex);
                Map.move(current_animal, move);

                System.out.println(Map);
                animalIndex += 1;
            }
            animalIndex %= animals_size;
        }
    }
}
