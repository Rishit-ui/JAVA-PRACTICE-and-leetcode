import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                Queue<Integer> queue = new ArrayDeque<>();

                visited[i] = true;
                queue.offer(i);

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    component.add(curr);

                    for (int neighbor : adj.get(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }

                // A component of size k is complete iff every node has degree k - 1
                int size = component.size();
                boolean isComplete = true;
                for (int node : component) {
                    if (adj.get(node).size() != size - 1) {
                        isComplete = false;
                        break;
                    }
                }

                if (isComplete) {
                    completeCount++;
                }
            }
        }

        return completeCount;
    }
}
