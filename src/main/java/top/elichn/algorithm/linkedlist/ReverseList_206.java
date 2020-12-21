package top.elichn.algorithm.linkedlist;

/**
 * <p>Title: ReverseList_206</p>
 * <p>Description: 反转链表 https://leetcode-cn.com/problems/reverse-linked-list/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/21
 */
public class ReverseList_206 {

    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode current = head;
        ListNode reverseNode = null;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            current.next = reverseNode;
            reverseNode = current;
            current = next;
        }
        return reverseNode;
    }
}
