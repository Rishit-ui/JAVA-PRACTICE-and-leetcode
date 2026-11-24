import java.util.*;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k %= total;

        int[][] shifted = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int newIdx = (i * n + j + k) % total;
                int newRow = newIdx / n;
                int newCol = newIdx % n;
                shifted[newRow][newCol] = grid[i][j];
            }
        }

        List<List<Integer>> result = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                row.add(shifted[i][j]);
            }
            result.add(row);
        }

        return result;
    }
}
