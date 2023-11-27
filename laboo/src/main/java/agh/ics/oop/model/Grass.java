package agh.ics.oop.model;

public class Grass implements WorldElement{
    private final Vector2d position;

    public Grass(Vector2d grass) {
        this.position = grass;
    }

    @Override
    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String toString(){
        return "*";
    }

    @Override
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    @Override
    public void move(MoveDirection direction, MoveValidator validator) {
    }
}
