package com.xyp;

/**
 * Created by xyp on 19/1/9.
 */
public class MergeTwoLinkedList {


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode listNode = null;

        ListNode pointNode = null;

        while (l1 != null || l2 != null) {
            ListNode needAdd = null;

            if (l1 == null) {

                needAdd = new ListNode(l2.val);
                l2 = l2.next == null ? null : l2.next;

            } else if (l2 == null) {

                needAdd = new ListNode(l1.val);
                l1 = l1.next == null ? null : l1.next;

            } else if (l1.val < l2.val) {

                needAdd = new ListNode(l1.val);
                l1 = l1.next == null ? null : l1.next;

            } else {

                needAdd = new ListNode(l2.val);
                l2 = l2.next == null ? null : l2.next;

            }

            if (listNode == null) {
                listNode = needAdd;
                pointNode = needAdd;
            } else {
                pointNode.next = needAdd;
                pointNode = pointNode.next;
            }

        }

        return listNode;

    }


    public class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
