package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

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
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)){
            animals.put(position, animal);
            return;
        }
        throw new PositionAlreadyOccupiedException(position);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        if ((direction == MoveDirection.FORWARD || direction == MoveDirection.BACKWARD )) {
            try {
                this.place(animal);
                animals.remove(oldPosition);
                mapChanged("Moved animal from " + oldPosition + " to " + animal.getPosition());

            } catch (PositionAlreadyOccupiedException e) {
                System.out.println(e.getMessage());
                animal.setPosition(oldPosition);
            }
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
