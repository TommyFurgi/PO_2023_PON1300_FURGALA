package agh.ics.oop.model;


public class Animal {
    private MapDirection direction;
    private Vector2d position;


    public Animal(){
        this(new Vector2d(2, 2));
    }

    public Animal(Vector2d position) {
        this.direction = MapDirection.NORTH;
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }
    public MapDirection getDirection() {return direction;}

    @Override
    public String toString(){return this.direction.toString() +" "+ this.position.toString();}

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    private boolean isOnMap(Vector2d vector) {
        return vector.getX() >= 0 & vector.getX() < 5 & vector.getY() >= 0 & vector.getY() < 5;
    }

    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> {
                Vector2d unitVector = this.direction.toUnitVector();
                moveInDirection(unitVector);
            }
            case BACKWARD -> {
                Vector2d unitVector = this.direction.next().next().toUnitVector();
                moveInDirection(unitVector);
            }
        }
    }
    private void moveInDirection(Vector2d unitVector) {
        Vector2d potentialVector = this.position.add(unitVector);
        if (isOnMap(potentialVector)) {
            this.position = potentialVector;
        }
    }

}