package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> directions;

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public Simulation(List<MoveDirection> directions, List<Vector2d> positions) {
        this.animals = new ArrayList<>();
        for (Vector2d start_pos: positions) {
            this.animals.add(new Animal(start_pos));
        }
        this.directions = directions;
    }

    public void run() {
        int animal_index = 0;
        int animals_size = animals.size();
        for (MoveDirection move : directions) {
            if (animal_index < animals_size){
                Animal current_animal = animals.get(animal_index);
                current_animal.move(move);
                System.out.println("Zwierze "+(animal_index+1)+" : "+current_animal);

                animal_index += 1;
            }
            if (animal_index == animals_size)
                animal_index = 0;
        }
    }
}
