package agh.ics.oop.model;

public class Animal implements WorldElement {
    private MapDirection direction;
    private Vector2d position;


    public Animal(){
        this(new Vector2d(2, 2));
    }

    public Animal(Vector2d position) {
        this.direction = MapDirection.NORTH;
        this.position = position;
    }
    public void setPosition(Vector2d position) {
        this.position = position;
    }
    @Override
    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getDirection() {
        return direction;
    }

    @Override
    public String toString(){
        return String.valueOf(this.direction.toString().charAt(0));
    }

    @Override
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }



    public void move(MoveDirection direction, MoveValidator validator) throws PositionAlreadyOccupiedException{
        switch (direction){
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case FORWARD -> {
                Vector2d unitVector = this.direction.toUnitVector();
                moveInDirection(unitVector, validator);
            }
            case BACKWARD -> {
                Vector2d unitVector = this.direction.toUnitVector().opposite();
                moveInDirection(unitVector, validator);
            }
        }
    }

    private void moveInDirection(Vector2d unitVector, MoveValidator validator) throws PositionAlreadyOccupiedException{
        if (validator.canMoveTo(position.add(unitVector))){
            position =  position.add(unitVector);
        }
        else
            throw new PositionAlreadyOccupiedException(position.add(unitVector));
    }

}
