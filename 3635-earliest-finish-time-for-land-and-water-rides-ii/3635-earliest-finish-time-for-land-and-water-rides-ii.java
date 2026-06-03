class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int landRideMinimum=Integer.MAX_VALUE;
        int waterRideMinimum=Integer.MAX_VALUE;

        for(int i=0;i<landStartTime.length;i++){
            landRideMinimum=Math.min(landRideMinimum,landStartTime[i]+landDuration[i]);
        }
        for(int i=0;i<waterStartTime.length;i++){
            waterRideMinimum=Math.min(waterRideMinimum,waterStartTime[i]+waterDuration[i]);
        }
        int res=Integer.MAX_VALUE;
        for(int i=0;i<waterStartTime.length;i++){
            res=Math.min(res,Math.max(landRideMinimum,waterStartTime[i])+waterDuration[i]);
        }
        for(int i=0;i<landStartTime.length;i++){
            res=Math.min(res,Math.max(waterRideMinimum,landStartTime[i])+landDuration[i]);
        }
        return res;
    }
}