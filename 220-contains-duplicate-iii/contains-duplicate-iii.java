import java.util.*;

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if (valueDiff < 0) return false;

        Map<Long, Long> buckets = new HashMap<>();
        long size = (long) valueDiff + 1;

        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];
            long bucketId = getBucketId(num, size);

            if (buckets.containsKey(bucketId)) return true;

            if (buckets.containsKey(bucketId - 1) &&
                Math.abs(num - buckets.get(bucketId - 1)) <= valueDiff) return true;

            if (buckets.containsKey(bucketId + 1) &&
                Math.abs(num - buckets.get(bucketId + 1)) <= valueDiff) return true;

            buckets.put(bucketId, num);

            if (i >= indexDiff) {
                long oldBucketId = getBucketId(nums[i - indexDiff], size);
                buckets.remove(oldBucketId);
            }
        }

        return false;
    }

    private long getBucketId(long num, long size) {
        if (num >= 0) return num / size;
        return ((num + 1) / size) - 1;
    }
}