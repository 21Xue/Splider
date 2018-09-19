package com.xyp.tree;

/**
 * Created by xyp on 18/9/18.
 */
public class Tree {

    private TreeNode rootTreeNode;

    public TreeNode getRootTreeNode() {
        return rootTreeNode;
    }

    public void setRootTreeNode(TreeNode rootTreeNode) {
        this.rootTreeNode = rootTreeNode;
    }

    public TreeNode find(int num) {
        return new TreeNode(num);
    }

    public void add(TreeNode node) {
        if (this.rootTreeNode == null) {
            this.rootTreeNode = node;
        } else {



        }


    }

    public void delete(TreeNode node) {

    }
}
