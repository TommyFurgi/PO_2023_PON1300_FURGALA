package agh.ics.oop;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final ExecutorService threadPool;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        this.threadPool = Executors.newFixedThreadPool(4);
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            System.out.println("Running simulation on map with ID: " + simulation.getWorldMap().getId());
            simulation.run();
            System.out.println("Simulation finished.");
            System.out.println();

        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(() -> {
                System.out.println("Running simulation on map with ID: " + simulation.getWorldMap().getId());
                simulation.run();
                System.out.println("Simulation finished.");
                System.out.println();
            });
            thread.start();
            awaitSimulationsEnd(thread);
        }
    }

    public void awaitSimulationsEnd(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("Wątek przerwany podczas oczekiwania na zakończenie symulacji.");
        }
    }

    public void runAsyncInThreadPool() {
        for (Simulation simulation : simulations) {
            threadPool.submit(() -> {
                System.out.println("Running simulation on map with ID: " + simulation.getWorldMap().getId());
                simulation.run();
                System.out.println("Simulation finished.");
                System.out.println();
            });
        }

        threadPool.shutdown();

        try {
            if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("Wątek przerwany podczas oczekiwania na zakończenie symulacji.");
        }
    }

}

