class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = Integer.MAX_VALUE;
        
        // Check all positions in the array
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                // Calculate clockwise distance
                int clockwise = (i - startIndex + n) % n;
                // Calculate counter-clockwise distance
                int counterClockwise = (startIndex - i + n) % n;
                // Take minimum of both directions
                int distance = Math.min(clockwise, counterClockwise);
                minDistance = Math.min(minDistance, distance);
            }
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}