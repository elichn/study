package top.elichn.algorithm.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title: DetectCycle_142</p>
 * <p>Description: 环形链表 II https://leetcode-cn.com/problems/linked-list-cycle-ii/</p>
 * <p>Company: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/12/28
 */
public class DetectCycle_142 {

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode pos = head;
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }
}
