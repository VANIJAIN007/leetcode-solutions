class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        int count=0;
            Arrays.sort(asteroids);
            long current=mass;
        for(int i=0;i<asteroids.length;i++){
            if(asteroids[i]<=current){
                current=current+asteroids[i];
                count++;
                System.out.println("entered "+count);
            }
        }
        if(count==asteroids.length){
            return true;
        }
        return false;
    }
}