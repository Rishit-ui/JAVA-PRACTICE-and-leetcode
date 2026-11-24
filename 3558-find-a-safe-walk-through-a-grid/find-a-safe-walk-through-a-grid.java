import java.util.*;

class Solution {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0) {
            return false;
        }

        int[][] maxHealth = new int[m][n];
        for (int[] row : maxHealth) {
            Arrays.fill(row, -1);
        }

        Deque<int[]> deque = new ArrayDeque<>();
        maxHealth[0][0] = startHealth;
        deque.offerFirst(new int[]{0, 0, startHealth});

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0], c = curr[1], currHealth = curr[2];

            if (r == m - 1 && c == n - 1) {
                return true;
            }

            // Skip stale entries if a better path to (r, c) was already processed
            if (currHealth < maxHealth[r][c]) {
                continue;
            }

            for (int[] d : DIRS) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int cost = grid.get(nr).get(nc);
                    int nextHealth = currHealth - cost;

                    if (nextHealth >= 1 && nextHealth > maxHealth[nr][nc]) {
                        maxHealth[nr][nc] = nextHealth;
                        if (cost == 0) {
                            deque.offerFirst(new int[]{nr, nc, nextHealth});
                        } else {
                            deque.offerLast(new int[]{nr, nc, nextHealth});
                        }
                    }
                }
            }
        }

        return false;
    }
}
