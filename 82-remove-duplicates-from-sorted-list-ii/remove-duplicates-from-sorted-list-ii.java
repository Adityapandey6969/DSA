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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;  // last node before duplicate sequence
        ListNode curr = head;
        
        while (curr != null) {
            // If it's the start of duplicates
            if (curr.next != null && curr.val == curr.next.val) {
                // Skip all nodes with same value
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                prev.next = curr.next; // remove all duplicates
            } else {
                prev = prev.next; // move prev only if no duplicate
            }
            curr = curr.next;
        }
        
        return dummy.next;
    }
}