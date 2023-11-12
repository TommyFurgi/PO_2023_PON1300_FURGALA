package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap{
    private final int size;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        this.size = (int) Math.sqrt(grassCount * 10);
        placeGrasses(grassCount);
    }

    private void placeGrasses(int grassCount) {
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(size+1, size+1, grassCount);

        for (Vector2d grassPosition : randomPositionGenerator) {
            grasses.put(grassPosition, new Grass(grassPosition));
        }
    }

    public boolean placeGrass(Grass grass){
        if (!grasses.containsKey(grass.getPosition())){
            grasses.put(grass.getPosition(), grass);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position) || grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return super.objectAt(position);
        }
        return grasses.get(position);
    }

    @Override
    public String toString() {
        int minX = 0, maxX = 1, minY = 0, maxY = 1;

        for (Vector2d position : animals.keySet()) {
            minX = Math.min(minX, position.getX());
            maxX = Math.max(maxX, position.getX());
            minY = Math.min(minY, position.getY());
            maxY = Math.max(maxY, position.getY());
        }

        for (Vector2d position : grasses.keySet()) {
            minX = Math.min(minX, position.getX());
            maxX = Math.max(maxX, position.getX());
            minY = Math.min(minY, position.getY());
            maxY = Math.max(maxY, position.getY());
        }

        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(new Vector2d(minX, minY), new Vector2d(maxX, maxY));
    }

    @Override
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> elements = super.getElements();
        for (Grass grass : grasses.values()) {
            elements.add(grass);
        }

        return elements;
    }
}

