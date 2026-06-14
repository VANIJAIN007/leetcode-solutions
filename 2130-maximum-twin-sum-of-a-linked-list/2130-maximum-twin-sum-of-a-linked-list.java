/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int pairSum(ListNode head) {
        ListNode temp=head;
        int count=1;
        ListNode slow=head;
        ListNode fast=head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode prev=null;
        ListNode curr=slow;
        ListNode nextpt=curr.next;
        while(nextpt!=null){
            curr.next=prev;
            prev=curr;
            curr=nextpt;
            nextpt=nextpt.next;
        }
        curr.next=prev;
        int maxtwin=Integer.MIN_VALUE;
        int sum=0;
        while(temp!=null && curr!=null){
            sum=sum+temp.val+curr.val;
            temp=temp.next;
            curr=curr.next;
            maxtwin=Math.max(maxtwin,sum);
            sum=0;
        }
        return maxtwin;
        
    }
}