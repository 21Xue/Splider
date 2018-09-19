package com.xyp.tree;

/**
 * Created by xyp on 18/9/17.
 */
public class TreeNode {

    private TreeNode left;

    private TreeNode right;

    private int number;

    public boolean compare(TreeNode node) {
        return this.number > node.getNumber();
    }

    public TreeNode(int number) {
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
