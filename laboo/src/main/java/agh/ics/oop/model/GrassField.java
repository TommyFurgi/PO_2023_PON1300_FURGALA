package agh.ics.oop.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap{
    private final int size;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        super();
        this.size = (int) Math.sqrt(grassCount * 10);
        placeGrasses(grassCount);
    }

    private void placeGrasses(int grassCount) {
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(size+1, size+1, grassCount);

        for (Vector2d grassPosition : randomPositionGenerator) {
            try {
                placeGrass(new Grass(grassPosition));
            } catch (PositionAlreadyOccupiedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void placeGrass(Grass grass) throws PositionAlreadyOccupiedException {
        Vector2d position = grass.getPosition();
        if (!grasses.containsKey(position)) {
            grasses.put(position, grass);
            return;
        }
        throw new PositionAlreadyOccupiedException(position);
    }

    @Override
    public Boundary getCurrentBounds() {
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

        return new Boundary(new Vector2d(minX, minY), new Vector2d(maxX, maxY));
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
    public Collection<WorldElement> getElements() {
        Collection<WorldElement> elements = super.getElements();

        return Stream.concat(elements.stream(), grasses.values().stream()).collect(Collectors.toList());
    }
}

