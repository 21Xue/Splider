package com.xyp.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xyp on 18/9/19.
 */
public class Solution {

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        TreeNode leftTree = root.left;

        TreeNode rightTree = root.right;

        int leftTreeLevel = getTreeLevel(leftTree);

        int rightTreeLevel = getTreeLevel(rightTree);

        int cha = 0;
        if (leftTreeLevel < rightTreeLevel) {

            cha = rightTreeLevel - leftTreeLevel;

        } else {

            cha = leftTreeLevel - rightTreeLevel;
        }

        return cha <= 1;
    }

    public static int  getTreeLevel(TreeNode node) {

        int klevel = 0;

        if (node != null) {
            int thislevel = 0;
            int nextlevel = 0;

            if (node.left != null || node.right != null) {
                thislevel = 1;
            }


            int nextleft = getTreeLevel(node.left);

            int nextright = getTreeLevel(node.right);


            if (nextleft > nextright) {
                nextlevel = nextleft;
            } else {
                nextlevel = nextright;
            }
            klevel = thislevel + nextlevel;
        }

        return klevel;
    }


    public static class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }


    public static void main(String[] args) {

        Integer[] numbers = {1,null,2,null,3};

        TreeNode root = null;


        List<TreeNode> nodeList = new ArrayList<TreeNode>(numbers.length);

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null) {
                TreeNode node = new TreeNode(numbers[i]);
                nodeList.add(i, node);
            } else {
                nodeList.add(i, null);
            }

        }

        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode node = nodeList.get(i);

            if (node != null) {
                if ((2 * i + 1) < nodeList.size()) {
                    node.setLeft(nodeList.get(2 * i + 1));
                }
                if ((2 * i + 2) < nodeList.size()) {
                    node.setRight(nodeList.get(2 * i + 2));
                }
            }

        }

        root = nodeList.get(0);
        int k = getTreeLevel(root);

        boolean flag=isBalanced(root);
        System.out.printf("asd");

    }

}

