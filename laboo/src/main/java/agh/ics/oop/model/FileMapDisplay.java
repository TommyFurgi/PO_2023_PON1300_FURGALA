package agh.ics.oop.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class FileMapDisplay implements MapChangeListener {

    private static final AtomicInteger updateCount = new AtomicInteger(1);
    private final String mapId;
    private final File file;

    public FileMapDisplay(UUID mapId) {
        this.mapId = mapId.toString();
        this.file = new File("FileMaps/" + this.mapId + ".txt");
    }

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println("Update #" + updateCount.getAndIncrement() + ": " + message);
            writer.println(worldMap);
            writer.println("--------------------------------------");
        } catch (IOException e) {
            System.err.println("Error writing to the log file: " + e.getMessage());
        }
    }
}
