class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        // Maximum possible value of lcm(arr) * gcd(arr) when nums[i] <= 10
        // lcm(1..10) = 2520, max gcd = 10 -> 2520 * 10 = 25200
        int maxPossibleProduct = 25200;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            int currentGcd = 0;
            int currentLcm = 1;

            for (int j = i; j < n; j++) {
                prod *= nums[j];
                
                // Early exit to prevent integer/long overflow
                if (prod > maxPossibleProduct) {
                    break;
                }

                currentGcd = gcd(currentGcd, nums[j]);
                currentLcm = lcm(currentLcm, nums[j]);

                if (prod == (long) currentLcm * currentGcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
