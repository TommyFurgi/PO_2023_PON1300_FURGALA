package agh.ics.oop.model;

import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleMapDisplay implements MapChangeListener{
    private static final AtomicInteger updateCount = new AtomicInteger(1);

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Map ID: " + worldMap.getId());
        System.out.println("Update #" + updateCount.get() + ": " + message);
        System.out.println(worldMap);
        updateCount.incrementAndGet();
    }
}
