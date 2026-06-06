class Solution {
    public int[] leftRightDifference(int[] nums) {
        int leftSum[]=new int[nums.length];
        int indexleft=0;
        int indexRight=0;
        int sum=0;
        int rightSum[]=new int[nums.length];
        int[] ans=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int j=i-1;
            
                while(j>-1){
                    sum=sum+nums[j];
                    j--;
                }

            leftSum[indexleft]=sum;
            sum=0;
            // System.out.println(i+" "+leftSum[indexleft]);
            indexleft++;

        }
        for(int i=0;i<nums.length;i++){
            int j=i+1;
                while(j<nums.length){
                    sum=sum+nums[j];
                    j++;
                }
            
            rightSum[indexRight]=sum;
            sum=0;
            indexRight++;
        }
        for(int i=0;i<nums.length;i++){
            ans[i]=Math.abs(leftSum[i]-rightSum[i]);
        }
        return ans;
    }
}