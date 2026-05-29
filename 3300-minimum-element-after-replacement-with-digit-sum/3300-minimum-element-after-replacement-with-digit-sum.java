class Solution {
    public int minElement(int[] nums) {
        int arr[]=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int value=nums[i];
            findmin(arr,nums,value,i);
        }
        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        return arr[0];
        
    }
    public void findmin(int[] arr,int[] nums,int value,int i){
        int sum=0;
        while(value>0){
            // System.out.println(value);
            sum+=value%10;
            value=value/10;
        }
        arr[i]=sum;
    }
}