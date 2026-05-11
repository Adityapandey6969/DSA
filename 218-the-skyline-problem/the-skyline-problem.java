import java.util.*;

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> events = new ArrayList<>();

        // Create start and end events
        for (int[] b : buildings) {
            // start event: height negative (to prioritize higher starts first)
            events.add(new int[]{b[0], -b[2]});
            // end event: height positive
            events.add(new int[]{b[1], b[2]});
        }

        // Sort events
        Collections.sort(events, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Max heap to track active building heights
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(0);

        int prevMax = 0;

        for (int[] event : events) {
            int x = event[0];
            int h = event[1];

            if (h < 0) {
                // Start of building
                maxHeap.add(-h);
            } else {
                // End of building
                maxHeap.remove(h);
            }

            int currMax = maxHeap.peek();

            if (currMax != prevMax) {
                result.add(Arrays.asList(x, currMax));
                prevMax = currMax;
            }
        }

        return result;
    }
}