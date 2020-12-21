package top.elichn.algorithm.linkedlist;

/**
 * <p>Title: DeleteNode_237</p>
 * <p>Description: 删除链表中的节点 https://leetcode-cn.com/problems/delete-node-in-a-linked-list/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/21
 */
public class DeleteNode_237 {

    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }
        // 删除的节点就是最后一个节点
        if (node.next == null) {
            // node的前一个节点应该指向空了
            node = null;
            return;
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
