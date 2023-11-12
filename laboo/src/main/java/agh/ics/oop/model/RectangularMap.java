package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

    private final Vector2d lowerLeftCorner;

    private final Vector2d upperRightCorner;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.lowerLeftCorner = new Vector2d(0, 0);
        this.upperRightCorner = new Vector2d(width-1, height-1);
    }
    public RectangularMap() {
        this(5,5);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.follows(lowerLeftCorner) && position.precedes(upperRightCorner);
    }



    @Override
    public boolean isOccupied(Vector2d position) {return animals.containsKey(position);}


    @Override
    public String toString() {
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(width - 1, height - 1);

        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
