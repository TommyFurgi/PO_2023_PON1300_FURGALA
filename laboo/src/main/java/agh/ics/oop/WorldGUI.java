package agh.ics.oop;

import javafx.application.Application;

public class WorldGUI {
    public static void main(String[] args) {
        try {
            Application.launch(SimulationApp.class, args);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
