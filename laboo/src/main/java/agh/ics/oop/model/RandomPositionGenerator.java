package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int grassCount;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.grassCount = grassCount;
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new GrassPositionIterator();
    }

    private class GrassPositionIterator implements Iterator<Vector2d> {
        private final List<Vector2d> positions;
        private int currentIndex;

        public GrassPositionIterator() {
            this.positions = generatePositions();
            this.currentIndex = 0;
        }

        private List<Vector2d> generatePositions() {
            Random random = new Random();
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < maxWidth * maxHeight; i++) {
                indices.add(i);
            }

            List<Vector2d> result = new ArrayList<>();
            for (int i = 0; i < grassCount; i++) {
                int index = random.nextInt(indices.size());
                int number = indices.remove(index);
                int x = number % maxWidth;
                int y = number / maxWidth;
                result.add(new Vector2d(x, y));
            }
            return result;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < positions.size();
        }

        @Override
        public Vector2d next() {
            Vector2d nextPosition = positions.get(currentIndex);
            currentIndex++;

            return nextPosition;
        }
    }
}
