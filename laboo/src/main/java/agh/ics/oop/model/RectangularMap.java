package agh.ics.oop.model;


public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

    private final Boundary boundary;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.boundary = new Boundary(new Vector2d(0,0), new Vector2d(width-1, height-1));
    }
    public RectangularMap() {
        this(5,5);
    }

    @Override
    public Boundary getCurrentBounds() {
        return this.boundary;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.follows(boundary.lowerLeftCorner()) && position.precedes(boundary.upperRightCorner());
    }



    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }


}
