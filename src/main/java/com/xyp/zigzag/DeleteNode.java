package com.xyp.zigzag;

/**
 * Created by xyp on 19/1/14.
 */
public class DeleteNode {

    private static ListNode totalNode;

//    public static void deleteNode(ListNode node) {
//        ListNode lastNode = totalNode;
//
//        ListNode pointNode = totalNode;
//
//        while (pointNode != null) {
//            if (pointNode.val == node.val) {
//                lastNode.next = pointNode.next;
//            } else {
//                lastNode = pointNode;
//            }
//            pointNode = pointNode.next;
//
//        }
//
//    }

    public static void deleteNode1(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    public static void main(String[] args) {
        ListNode l11 = new ListNode(1);
        totalNode = l11;

        ListNode l12 = new ListNode(2);
        l11.next = l12;

        ListNode l13 = new ListNode(3);
        l12.next = l13;

        ListNode l14 = new ListNode(4);
        l13.next = l14;

        ListNode l15 = new ListNode(5);
        l14.next = l15;

        ListNode l16 = new ListNode(6);
        l15.next = l16;


        ListNode needDelete = new ListNode(4);
        deleteNode1(needDelete);
        System.out.print("asd");
    }


    static class ListNode {
        int val;

        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


}
