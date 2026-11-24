import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0], v = road[1], dist = road[2];
            adj.get(u).add(new int[]{v, dist});
            adj.get(v).add(new int[]{u, dist});
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;

        int minScore = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int[] edge : adj.get(curr)) {
                int next = edge[0];
                int dist = edge[1];
                minScore = Math.min(minScore, dist);

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        return minScore;
    }
}
