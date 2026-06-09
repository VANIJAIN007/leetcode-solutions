class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long min=Long.MAX_VALUE;
        long max=Long.MIN_VALUE;
        long sum=0;
            for(int i=0;i<nums.length;i++){
                if(max<nums[i]){
                    max=nums[i];
                }
            }
            for(int i=0;i<nums.length;i++){
                if(min>nums[i]){
                    min=nums[i];
                }
            }
            sum=sum+(max-min);
        
        return k*sum;
    }
}