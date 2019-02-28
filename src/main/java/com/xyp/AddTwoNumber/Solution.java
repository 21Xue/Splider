package com.xyp.AddTwoNumber;

/**
 * Created by xyp on 18/10/12.
 */
public class Solution {


    public static void main(String[] args) {

//        [2,4,3]
//[5,6,4]

        ListNode l11 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(3);

        ListNode l21 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l23 = new ListNode(4);

        l11.next = l12;
        l12.next = l13;

        l21.next = l22;
        l22.next = l23;

        ListNode result = addTwoNumbers(l11, l21);

        System.out.print("asd");

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1now = l1;
        ListNode l2now = l2;

        while (l2now != null) {

            if (l1now.next == null) {
                l1now.next = new ListNode(0);
            }

            l1now.val = l1now.val + l2now.val;

            if (l1now.val > 9) {
                l1now.val = l1now.val - 10;

                if (l2now.next != null) {
                    l2now.next.val = l2now.next.val + 1;
                } else {
                    l2now.next = new ListNode(1);
                }
            }


            l2now = l2now.next;
            l1now = l1now.next;
        }



        return l1;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}


