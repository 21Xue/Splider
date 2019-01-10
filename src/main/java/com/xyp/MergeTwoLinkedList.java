package com.xyp;

/**
 * Created by xyp on 19/1/9.
 */
public class MergeTwoLinkedList {


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

            } else if (l2.val <= l1.val) {
                needAdd = new ListNode(l2.val);
                l2 = l2.next == null ? null : l2.next;

            } else if (l1.val < l2.val) {
                needAdd = new ListNode(l1.val);
                l1 = l1.next == null ? null : l1.next;
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

    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);

        ListNode l12 = new ListNode(2);
        l11.next = l12;

        ListNode l13 = new ListNode(4);
        l12.next = l13;

        ListNode l21 = new ListNode(1);

        ListNode l22 = new ListNode(3);
        l21.next = l22;

        ListNode l23 = new ListNode(4);
        l22.next = l23;

        mergeTwoLists(l11, l21);

        System.out.print("12");

    }


    public static class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
