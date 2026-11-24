class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] comp = new int[n];
        int currentComp = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                currentComp++;
            }
            comp[i] = currentComp;
        }

        int q = queries.length;
        boolean[] answer = new boolean[q];
        for (int i = 0; i < q; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            answer[i] = (comp[u] == comp[v]);
        }

        return answer;
    }
}
