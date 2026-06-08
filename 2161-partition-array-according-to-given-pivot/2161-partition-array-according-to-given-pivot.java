class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        ArrayList<Integer> listLeft=new ArrayList<>();
        ArrayList<Integer> listRight=new ArrayList<>();
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<pivot){
                listLeft.add(nums[i]);
            }
            else if(nums[i]==pivot){
                count++;
            }
            else if(nums[i]>pivot){
                listRight.add(nums[i]);
            }
        }
        System.out.println(listLeft);
                System.out.println(listRight);

        int index=0;
        for(index=0;index<listLeft.size();index++){
            nums[index]=listLeft.get(index);
        }
        System.out.println(index);
        while(count!=0){
            nums[index]=pivot;
            count--;
            index++;
        }
        // System.out.println(index);
        for(int rightIndex=0;rightIndex<listRight.size();rightIndex++){
            nums[index]=listRight.get(rightIndex);
            index++;
        }
        return nums;
    }
}