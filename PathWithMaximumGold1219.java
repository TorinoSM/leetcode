//package org.example;

import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class MainTest {

    @Test
    void getMaximumGold() {
//        int[][] grid = {{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
//        int[][] grid = {{1, 0, 7}, {2, 0, 6}, {3, 4, 5}, {0, 3, 0}, {9, 0, 20}};
        int[][] grid = {{1, 0, 7, 0, 0, 0}, {2, 0, 6, 0, 1, 0}, {3, 5, 6, 7, 4, 2}, {4, 3, 1, 0, 2, 0}, {3, 0, 5, 0, 20, 0}};

        int max = 0;

        for (int y = 0; y < grid.length; y++) {
            int[] row = grid[y];
            for (int x = 0; x < row.length; x++) {
                if (row[x] == 0) {
                    continue;
                }
                int gold = row[x];
                Set<Cell> visited = new HashSet<>();
                visited.add(new Cell(x, y, gold));
                max = Math.max(max, gold + findMaxNeighborGold(x, y, grid, visited));
            }
        }
        System.out.println(">>>>>>: " + max);
    }

    private int findMaxNeighborGold(int x, int y, int[][] grid, Set<Cell> visited) {
        int max = 0;
        Set<Cell> neighbors = getNeighbors(x, y, grid);
        for (Cell cell : neighbors) {
            if (visited.contains(cell)) {
                continue;
            }
            visited.add(cell);
            max = Math.max(max, cell.gold + findMaxNeighborGold(cell.x, cell.y, grid, visited));
            visited.remove(cell);
        }
        return max;
    }

    private Set<Cell> getNeighbors(int x, int y, int[][] grid) {
        Set<Cell> neighbors = new HashSet<>();
        // up
        if (y > 0 && grid[y - 1][x] != 0) {
            neighbors.add(new Cell(x, y - 1, grid[y - 1][x]));
        }
        // down
        if (y < grid.length - 1 && grid[y + 1][x] != 0) {
            neighbors.add(new Cell(x, y + 1, grid[y + 1][x]));
        }
        // left
        if (x > 0 && grid[y][x - 1] != 0) {
            neighbors.add(new Cell(x - 1, y, grid[y][x - 1]));
        }
        // right
        if (x < grid[y].length - 1 && grid[y][x + 1] != 0) {
            neighbors.add(new Cell(x + 1, y, grid[y][x + 1]));
        }
        return neighbors;
    }

    @ToString
    public static class Cell {
        int x;
        int y;
        int gold;

        public Cell(int x, int y, int gold) {
            this.x = x;
            this.y = y;
            this.gold = gold;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Cell)) return false;
            final Cell other = (Cell) o;
            if (!other.canEqual(this)) return false;
            if (this.x != other.x) return false;
            if (this.y != other.y) return false;
            return this.gold == other.gold;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof Cell;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            result = result * PRIME + this.x;
            result = result * PRIME + this.y;
            result = result * PRIME + this.gold;
            return result;
        }
    }
}