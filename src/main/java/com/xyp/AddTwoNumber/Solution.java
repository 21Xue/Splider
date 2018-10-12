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
        ListNode result = null;

        ListNode l1now = l1;

        ListNode l2now = l2;

        ListNode resultnow = new ListNode(0);


        while (l1now != null && l2now != null) {

            int nowplus = l1now.val + l2now.val;

            if (resultnow == null) {
                resultnow = new ListNode(0);
            }

            if (nowplus > 9) {
                nowplus = nowplus - 10;
                resultnow.next = new ListNode(1);
            }
            resultnow.val = resultnow.val + nowplus;


            if (result == null) {
                result = resultnow;
            }

            l1now = l1now.next;
            l2now = l2now.next;
            resultnow = resultnow.next;
        }


        return result;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}


