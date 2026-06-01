class Solution {
    public int minimumCost(int[] cost) {
        int sum=0;
        int free=0;
        int count=0;
        Arrays.sort(cost);
        if(cost.length==1){
            return cost[0];
        }
        for(int i=cost.length-1;i>=0;i--){
            if(count==2){
                free=free+cost[i];
                count=0;
            }
            else{
                sum=sum+cost[i];
                count++;
            }
        }
        return sum;
    }
}