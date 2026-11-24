import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        // Build adjacency list with only online nodes and compute in-degrees for topological sort
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] inDegree = new int[n];
        int maxCost = 0;

        for (int[] e : edges) {
            int u = e[0], v = e[1], cost = e[2];
            if (online[u] && online[v]) {
                adj.get(u).add(new int[]{v, cost});
                inDegree[v]++;
                maxCost = Math.max(maxCost, cost);
            }
        }

        // Precompute topological order (Kahn's Algorithm)
        List<Integer> topoOrder = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);
            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                if (--inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // Binary search for the maximum valid minimum edge cost
        int low = 0, high = maxCost;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReach(n, adj, topoOrder, mid, k)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int n, List<List<int[]>> adj, List<Integer> topoOrder, int minEdgeCost, long k) {
        long INF = Long.MAX_VALUE / 2;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int u : topoOrder) {
            if (dist[u] == INF) continue;
            for (int[] edge : adj.get(u)) {
                int v = edge[0];
                int cost = edge[1];
                if (cost >= minEdgeCost && dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}
