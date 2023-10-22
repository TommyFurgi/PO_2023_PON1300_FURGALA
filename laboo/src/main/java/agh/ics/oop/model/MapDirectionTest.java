package agh.ics.oop.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapDirectionTest {
    @Test
    public void testNextEastToSouth() {
        MapDirection east = MapDirection.EAST;
        MapDirection nextDirection = east.next();
        assertEquals(MapDirection.SOUTH, nextDirection);
    }

    @Test
    public void testNextSouthToWest() {
        MapDirection south = MapDirection.SOUTH;
        MapDirection nextDirection = south.next();
        assertEquals(MapDirection.WEST, nextDirection);
    }

    @Test
    public void testNextWestToNorth() {
        MapDirection west = MapDirection.WEST;
        MapDirection nextDirection = west.next();
        assertEquals(MapDirection.NORTH, nextDirection);
    }

    @Test
    public void testNextNorthToEast() {
        MapDirection north = MapDirection.NORTH;
        MapDirection nextDirection = north.next();
        assertEquals(MapDirection.EAST, nextDirection);
    }


//    Previous
    @Test
    public void testPreviousEastToNorth() {
        MapDirection east = MapDirection.EAST;
        MapDirection nextDirection = east.previous();
        assertEquals(MapDirection.NORTH, nextDirection);
    }
    @Test
    public void testPreviousNorthToWest() {
        MapDirection north = MapDirection.NORTH;
        MapDirection nextDirection = north.previous();
        assertEquals(MapDirection.WEST, nextDirection);
    }
    @Test
    public void testPreviousWestToSouth() {
        MapDirection west = MapDirection.WEST;
        MapDirection nextDirection = west.previous();
        assertEquals(MapDirection.SOUTH, nextDirection);
    }
    @Test
    public void testPreviousSouthToEast() {
        MapDirection south = MapDirection.SOUTH;
        MapDirection nextDirection = south.previous();
        assertEquals(MapDirection.EAST, nextDirection);
    }

}
