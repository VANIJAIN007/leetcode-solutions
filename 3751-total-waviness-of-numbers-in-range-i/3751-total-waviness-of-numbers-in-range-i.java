class Solution {
    public int totalWaviness(int num1, int num2) {
        int count = 0;

        for (int j = num1; j <= num2; j++) {
            String s = Integer.toString(j);
            if (s.length() < 3) {
                continue;
            } else {
                for (int i = 1; i < s.length() - 1; i++) {
                    char ch = s.charAt(i);
                    if (ch > s.charAt(i - 1) && ch > s.charAt(i + 1) || ch < s.charAt(i - 1) && ch < s.charAt(i + 1)) {
                        count++;
                    }
                }
            }
        }
        return count;

    }
}