import java.util.*;

class Solution {

    int[] segmentTree;
    int MAX_POSITION = 50005;

    private void updateSegmentTree(int node, int leftBound, int rightBound,
                                   int targetIndex, int gapLength) {

        if (leftBound == rightBound) {
            segmentTree[node] = gapLength;
            return;
        }

        int middle = leftBound + (rightBound - leftBound) / 2;

        if (targetIndex <= middle) {
            updateSegmentTree(2 * node, leftBound, middle,
                              targetIndex, gapLength);
        } else {
            updateSegmentTree(2 * node + 1, middle + 1, rightBound,
                              targetIndex, gapLength);
        }

        segmentTree[node] = Math.max(segmentTree[2 * node],
                                     segmentTree[2 * node + 1]);
    }

    private int getMaximumGap(int node, int leftBound, int rightBound,
                              int queryLeft, int queryRight) {

        if (queryRight < leftBound || rightBound < queryLeft) {
            return 0;
        }

        if (queryLeft <= leftBound && rightBound <= queryRight) {
            return segmentTree[node];
        }

        int middle = leftBound + (rightBound - leftBound) / 2;

        int leftMaximum =
                getMaximumGap(2 * node, leftBound, middle,
                              queryLeft, queryRight);

        int rightMaximum =
                getMaximumGap(2 * node + 1, middle + 1, rightBound,
                              queryLeft, queryRight);

        return Math.max(leftMaximum, rightMaximum);
    }

    public List<Boolean> getResults(int[][] queries) {

        segmentTree = new int[4 * MAX_POSITION];

        TreeSet<Integer> obstaclePositions = new TreeSet<>();

        obstaclePositions.add(0);
        obstaclePositions.add(MAX_POSITION);

        updateSegmentTree(1, 0, MAX_POSITION,
                          MAX_POSITION, MAX_POSITION);

        List<Boolean> result = new ArrayList<>();

        for (int[] query : queries) {

            int queryType = query[0];

            // Add obstacle
            if (queryType == 1) {

                int obstaclePosition = query[1];

                Integer previousObstacle =
                        obstaclePositions.floor(obstaclePosition);

                Integer nextObstacle =
                        obstaclePositions.ceiling(obstaclePosition);

                updateSegmentTree(
                        1,
                        0,
                        MAX_POSITION,
                        obstaclePosition,
                        obstaclePosition - previousObstacle
                );

                updateSegmentTree(
                        1,
                        0,
                        MAX_POSITION,
                        nextObstacle,
                        nextObstacle - obstaclePosition
                );

                obstaclePositions.add(obstaclePosition);
            }

            // Check block placement
            else {

                int rightLimit = query[1];
                int blockSize = query[2];

                int nearestObstacleOnLeft =
                        obstaclePositions.floor(rightLimit);

                int endingGap =
                        rightLimit - nearestObstacleOnLeft;

                int largestGapBeforeLimit =
                        getMaximumGap(
                                1,
                                0,
                                MAX_POSITION,
                                0,
                                nearestObstacleOnLeft
                        );

                result.add(
                        endingGap >= blockSize ||
                        largestGapBeforeLimit >= blockSize
                );
            }
        }

        return result;
    }
}