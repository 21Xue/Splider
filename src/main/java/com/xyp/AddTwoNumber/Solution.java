package com.xyp.AddTwoNumber;

/**
 * Created by xyp on 18/10/12.
 */
public class Solution {


    public static void main(String[] args) {

//        [2,4,3]
//[5,6,4]

        ListNode l11 = new ListNode(9);
        ListNode l12 = new ListNode(9);
//        ListNode l13 = new ListNode(3);

        ListNode l21 = new ListNode(9);
//        ListNode l22 = new ListNode(3);
//        ListNode l23 = new ListNode(4);

        l11.next = l12;
//        l12.next = l13;

//        l21.next = l22;
//        l22.next = l23;

        ListNode result = addTwoNumbers(l11, l21);

        System.out.print("asd");

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1now = l1;
        ListNode l2now = l2;

        while (l2now != null) {

            if (l1now == null) {
                l1now = new ListNode(0);
            }

            l1now.val = l1now.val + l2now.val;

            if (l1now.val > 9) {
                l1now.val = l1now.val - 10;
                if (l1now.next != null) {
                    l1now.next.val = l1now.next.val + 1;
                } else {
                    l1now.next = new ListNode(1);
                }
            }


            l2now = l2now.next;

            if (l2now != null && l1now.next == null) {
                l1now.next = new ListNode(0);
            }
            l1now = l1now.next;
        }


//        ListNode result = null;
//

//
//        ListNode resultnow = new ListNode(0);
//
//        while (l1now != null || l2now != null) {
//
//            int n1 = 0;
//            int n2 = 0;
//
//            if (l1now != null) {
//                n1 = l1now.val;
//            }
//            if (l2now != null) {
//                n2 = l2now.val;
//            }
//
//            if ((l1now != null && l1now.next != null) || (l2now != null && l2now.next != null)) {
//                resultnow.next = new ListNode(0);
//            }
//
//            int nowplus = n1 + n2 + resultnow.val;
//
//            if (nowplus > 9) {
//                nowplus = nowplus - 10;
//                resultnow.next = new ListNode(1);
//            }
//            resultnow.val = nowplus;
//
//            if (result == null) {
//                result = resultnow;
//            }
//
//            if (l1now != null) {
//                l1now = l1now.next;
//            }
//            if (l2now != null) {
//                l2now = l2now.next;
//            }
//            resultnow = resultnow.next;
//        }
//
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


