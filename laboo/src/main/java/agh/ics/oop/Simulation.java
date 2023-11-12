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
    private WorldMap Map;

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions, WorldMap map) {
        this.animals = new ArrayList<>();
        this.directions = directions;
        this.Map = map;
        for (Vector2d start_pos: positions) {
            if (map.canMoveTo(start_pos)) {
                Animal newAnimal =new Animal(start_pos);
                this.animals.add(newAnimal);
                Map.place(newAnimal);
            }
        }

    }

    public void run() {
        int animal_index = 0;
        int animals_size = animals.size();
        for (MoveDirection move : directions) {
            if (animal_index < animals_size){
                Animal current_animal = animals.get(animal_index);
                Map.move(current_animal, move);
                System.out.println("Zwierze "+(animal_index+1)+" : "+current_animal);

                animal_index += 1;
            }
            if (animal_index == animals_size)
                animal_index = 0;
        }
    }
}
