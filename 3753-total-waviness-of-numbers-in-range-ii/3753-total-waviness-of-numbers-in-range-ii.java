class Solution {

    private String numberString;
    private Map<String, long[]> memo = new HashMap<>();

    private long[] countWaviness(
            int position,
            int secondLastDigit,
            int lastDigit,
            int isTight,
            int hasStarted,
            int digitCount) {

        if (position == numberString.length()) {
            return new long[]{1, 0};
        }

        String state = position + "," + secondLastDigit + "," + lastDigit + ","
                + isTight + "," + hasStarted + "," + digitCount;

        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        long totalNumbers = 0;
        long totalWavyPoints = 0;

        int maxDigit = (isTight == 1)
                ? numberString.charAt(position) - '0'
                : 9;

        for (int currentDigit = 0; currentDigit <= maxDigit; currentDigit++) {

            int nextTight =
                    (isTight == 1 &&
                     currentDigit == numberString.charAt(position) - '0')
                    ? 1 : 0;

            int nextStarted =
                    (hasStarted == 1 || currentDigit > 0)
                    ? 1 : 0;

            int nextLength =
                    (hasStarted == 1 || currentDigit > 0)
                    ? digitCount + 1
                    : 0;

            long[] nextResult = countWaviness(
                    position + 1,
                    lastDigit,
                    currentDigit,
                    nextTight,
                    nextStarted,
                    nextLength
            );

            long ways = nextResult[0];
            long waviness = nextResult[1];

            if (digitCount > 1 &&
                    ((secondLastDigit < lastDigit && lastDigit > currentDigit) ||
                     (secondLastDigit > lastDigit && lastDigit < currentDigit))) {
                waviness += ways;
            }

            totalNumbers += ways;
            totalWavyPoints += waviness;
        }

        long[] answer = {totalNumbers, totalWavyPoints};
        memo.put(state, answer);

        return answer;
    }

    private long calculate(long limit) {
        numberString = Long.toString(limit);
        memo.clear();

        return countWaviness(
                0,  // position
                0,  // secondLastDigit
                0,  // lastDigit
                1,  // tight
                0,  // started
                0   // length
        )[1];
    }

    public long totalWaviness(long left, long right) {
        return calculate(right) - calculate(left - 1);
    }
}