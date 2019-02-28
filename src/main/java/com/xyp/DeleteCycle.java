package com.xyp;

import java.util.HashMap;
import java.util.Map;

public class DeleteCycle {

    public ListNode detectCycle(ListNode head) {

        Map<Integer, ListNode> listNodeMap = new HashMap<>();

        boolean cycleflag = false;

        ListNode cycleNode = null;
        while (true) {
            if (head == null || head.next == null) {
                cycleflag = true;
                break;
            }

            if (listNodeMap.get(head.hashCode()) != null) {
                cycleNode = head;
                break;
            }
            listNodeMap.put(head.hashCode(), head.next);

            head = head.next;
        }

        if (cycleflag) {
            return null;
        }

        return cycleNode;

    }
}
