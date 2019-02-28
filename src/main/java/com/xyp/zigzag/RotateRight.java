package com.xyp.zigzag;

/**
 * Created by xyp on 19/1/15.
 */
public class RotateRight {

    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode save = head;

        ListNode saveLast = null;

        int index = 0;
        while (head != null) {
            index++;
            saveLast = head;
            head = head.next;

        }
        saveLast.next = save;

        int step = index > k ? k : k % index;


        for (int i = 0; i < index - step; i++) {
            saveLast = save;
            save = save.next;

        }
        saveLast.next = null;


        return save;
    }

    public static void main(String[] args) {
        System.out.print(4 % 3);

        ListNode l11 = new ListNode(1);

        ListNode l12 = new ListNode(2);
        l11.next = l12;

        ListNode l13 = new ListNode(3);
        l12.next = l13;

        ListNode l14 = new ListNode(4);
        l13.next = l14;

        ListNode l15 = new ListNode(5);
        l14.next = l15;

//        ListNode l16 = new ListNode(6);
//        l15.next = l16;

        ListNode need = rotateRight(l11, 2);

        System.out.print("asd");
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
