class Solution {
    public int maxNumberOfBalloons(String text) {
        int arr[]=new int[26];
        for(int c:text.toCharArray()){
            arr[c-'a']++;
        }
        int b=arr['b'-'a'];
        int a=arr['a'-'a'];
        int l=arr['l'-'a']/2;
        int o=arr['o'-'a']/2;
        int n=arr['n'-'a'];

        return Math.min(Math.min(Math.min(Math.min(o,n),l),a),b);
    }
}