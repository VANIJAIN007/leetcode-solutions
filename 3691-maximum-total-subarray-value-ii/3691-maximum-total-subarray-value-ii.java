import java.util.*;

class RangeTree {

    int size;
    int[] segment;
    int[] values;
    boolean findMinimum;

    RangeTree(int[] nums, boolean isMinTree) {

        size = nums.length;
        values = nums;

        findMinimum = isMinTree;
        segment = new int[4 * size + 1];

        construct(1, 0, size - 1);
    }

    int merge(int first, int second) {
        return findMinimum ? Math.min(first, second)
                           : Math.max(first, second);
    }

    void construct(int node, int start, int end) {

        if (start == end) {
            segment[node] = values[start];
            return;
        }

        int middle = start + (end - start) / 2;

        construct(node * 2, start, middle);
        construct(node * 2 + 1, middle + 1, end);

        segment[node] = merge(segment[node * 2],
                              segment[node * 2 + 1]);
    }

    int getValue(int node, int start, int end,
                 int left, int right) {

        if (right < start || left > end) {
            return findMinimum ? Integer.MAX_VALUE
                               : Integer.MIN_VALUE;
        }

        if (left <= start && end <= right) {
            return segment[node];
        }

        int middle = start + (end - start) / 2;

        int leftPart =
            getValue(node * 2, start, middle, left, right);

        int rightPart =
            getValue(node * 2 + 1, middle + 1, end,
                     left, right);

        return merge(leftPart, rightPart);
    }

    int queryRange(int left, int right) {
        return getValue(1, 0, size - 1, left, right);
    }
}

class Solution {

    public long maxTotalValue(int[] nums, int k) {

        int length = nums.length;

        RangeTree maximumTree = new RangeTree(nums, false);
        RangeTree minimumTree = new RangeTree(nums, true);

        PriorityQueue<int[]> heap =
            new PriorityQueue<>(
                (a, b) -> Integer.compare(b[0], a[0])
            );

        Set<String> visitedRanges = new HashSet<>();

        int highest = maximumTree.queryRange(0, length - 1);
        int lowest = minimumTree.queryRange(0, length - 1);

        heap.offer(new int[]{highest - lowest, 0, length - 1});

        visitedRanges.add("0," + (length - 1));

        long total = 0;

        while (!heap.isEmpty() && k > 0) {

            int[] current = heap.poll();

            int score = current[0];
            int start = current[1];
            int end = current[2];

            total += score;
            k--;

            if (start + 1 <= end) {

                String nextLeft = (start + 1) + "," + end;

                if (!visitedRanges.contains(nextLeft)) {

                    int mx =
                        maximumTree.queryRange(start + 1, end);

                    int mn =
                        minimumTree.queryRange(start + 1, end);

                    heap.offer(
                        new int[]{mx - mn, start + 1, end}
                    );

                    visitedRanges.add(nextLeft);
                }
            }

            if (start <= end - 1) {

                String nextRight = start + "," + (end - 1);

                if (!visitedRanges.contains(nextRight)) {

                    int mx =
                        maximumTree.queryRange(start, end - 1);

                    int mn =
                        minimumTree.queryRange(start, end - 1);

                    heap.offer(
                        new int[]{mx - mn, start, end - 1}
                    );

                    visitedRanges.add(nextRight);
                }
            }
        }

        return total;
    }
}