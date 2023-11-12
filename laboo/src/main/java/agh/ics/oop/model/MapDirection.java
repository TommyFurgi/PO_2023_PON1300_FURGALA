package agh.ics.oop.model;

public enum MapDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public static void main(String[] args){
        MapDirection direction1 = MapDirection.NORTH;
        System.out.println(direction1.next());
        System.out.println(direction1.previous());
        System.out.println(direction1.toString());
        System.out.println(direction1.toUnitVector().toString());

    }

    @Override
    public String toString(){
        return switch(this) {
            case NORTH -> "NORTH";
            case SOUTH -> "SOUTH";
            case EAST -> "EAST";
            case WEST -> "WEST";
        };
    }

    public MapDirection next(){
        return switch(this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
            case WEST -> NORTH;
        };
    }
    public MapDirection previous() {
        return switch (this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
        };
    }

    public Vector2d toUnitVector(){
        return switch (this) {
            case NORTH -> new Vector2d(0,1);
            case SOUTH -> new Vector2d(0,-1);
            case EAST -> new Vector2d(1,0);
            case WEST -> new Vector2d(-1,0);
        };
    }
}