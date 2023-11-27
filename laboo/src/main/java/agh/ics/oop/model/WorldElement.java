package agh.ics.oop.model;

public interface WorldElement {

    Vector2d getPosition();
    String toString();

    boolean isAt(Vector2d position);

    void move(MoveDirection direction, MoveValidator validator);
}
