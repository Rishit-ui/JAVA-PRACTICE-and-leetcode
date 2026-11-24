import java.util.*;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= 9; i++) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr > high) {
                break;
            }
            if (curr >= low) {
                result.add(curr);
            }

            int lastDigit = curr % 10;
            if (lastDigit < 9) {
                queue.offer(curr * 10 + (lastDigit + 1));
            }
        }

        return result;
    }
}
