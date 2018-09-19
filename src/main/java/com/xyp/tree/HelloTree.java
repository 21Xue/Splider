package com.xyp.tree;

import java.util.Random;

/**
 * Created by xyp on 18/9/17.
 */
public class HelloTree {

    public static void main(String[] args) {

        Random random = new Random();

        Tree tree = new Tree();

        for (int i = 0; i < 15; i++) {

            int number = random.nextInt();
            TreeNode node = new TreeNode(number);
            tree.add(node);
        }

    }

}
