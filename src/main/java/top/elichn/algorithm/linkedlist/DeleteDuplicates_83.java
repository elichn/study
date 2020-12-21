package top.elichn.algorithm.linkedlist;

/**
 * <p>Title: DeleteDuplicates_83</p>
 * <p>Description: 删除排序链表中的重复元素 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/21
 */
public class DeleteDuplicates_83 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return current;
    }
}
