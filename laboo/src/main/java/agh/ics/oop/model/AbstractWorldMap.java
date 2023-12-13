package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap{
    protected Map<Vector2d, Animal> animals = new HashMap<>();

    protected List<MapChangeListener> changeListeners = new ArrayList<>();

    @Override
    public abstract Boundary getCurrentBounds();

    private final UUID id;

    public AbstractWorldMap() {
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public void place(Animal animal) {
        Vector2d position = animal.getPosition();
        animals.put(position, animal);
        mapChanged("Placed animal on "+position);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        try {
            animal.move(direction, this);
            animals.remove(oldPosition);
            animals.put(animal.getPosition(), animal);
            if (oldPosition != animal.getPosition()) {
                mapChanged("Moved animal from " + oldPosition + " to " + animal.getPosition());
            }

        } catch (PositionAlreadyOccupiedException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> elements = new ArrayList<>();
        for (Animal animal : animals.values()) {
            elements.add(animal);
        }

        return elements;
    }

    @Override
    public String toString(){
        Boundary bounds = getCurrentBounds();

        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(bounds.lowerLeftCorner(), bounds.upperRightCorner());
    }

    @Override
    public abstract boolean isOccupied(Vector2d position);


    @Override
    public void addMapChangeListener(MapChangeListener listener) {
        changeListeners.add(listener);
    }

    @Override
    public void removeMapChangeListener(MapChangeListener listener) {
        changeListeners.remove(listener);
    }

    @Override
    public void mapChanged(String message){
        for (MapChangeListener listener: changeListeners){
            listener.mapChanged(this, message);
        }
    }
}
