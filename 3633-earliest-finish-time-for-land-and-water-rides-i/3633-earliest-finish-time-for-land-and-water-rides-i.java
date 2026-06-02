class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int max=0;
        int min=Integer.MAX_VALUE;
        for(int i=0;i<landStartTime.length;i++){
            for(int j=0;j<waterStartTime.length;j++){
                int firstRideLand=landStartTime[i]+landDuration[i];
            max=Math.max(firstRideLand,waterStartTime[j]);
            int waterRide=max+waterDuration[j];
            min=Math.min(min,waterRide);
            int firstRideWater=waterStartTime[j]+waterDuration[j];
            max=Math.max(firstRideWater,landStartTime[i]);
            int landRide=max+landDuration [i];
            min=Math.min(min,landRide);
            }
            
        }
        return min;
    }
}