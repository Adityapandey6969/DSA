class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        if (numRows <= 0) {
            return triangle;
        }

        triangle.add(java.util.Arrays.asList(1));

        for (int i = 1; i < numRows; i++) {
            List<Integer> prev = triangle.get(i - 1);
            List<Integer> curr = new ArrayList<>();

            curr.add(1);
            for (int j = 1; j < i; j++) {
                curr.add(prev.get(j - 1) + prev.get(j));
            }
            curr.add(1);

            triangle.add(curr);
        }

        return triangle;
    }
}
 