class Solution {
    private static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long[] prefVal = new long[n + 1];
        long[] prefSum = new long[n + 1];
        int[] prefCount = new int[n + 1];
        long[] pow10 = new long[n + 1];

        pow10[0] = 1;
        for (int i = 0; i < n; i++) {
            pow10[i + 1] = (pow10[i] * 10) % MOD;

            int d = s.charAt(i) - '0';
            if (d != 0) {
                prefVal[i + 1] = (prefVal[i] * 10 + d) % MOD;
                prefSum[i + 1] = prefSum[i] + d;
                prefCount[i + 1] = prefCount[i] + 1;
            } else {
                prefVal[i + 1] = prefVal[i];
                prefSum[i + 1] = prefSum[i];
                prefCount[i + 1] = prefCount[i];
            }
        }

        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int k = prefCount[r + 1] - prefCount[l];
            long sub = (prefVal[l] * pow10[k]) % MOD;
            long x = (prefVal[r + 1] - sub + MOD) % MOD;
            long sum = (prefSum[r + 1] - prefSum[l]) % MOD;

            ans[i] = (int) ((x * sum) % MOD);
        }

        return ans;
    }
}
