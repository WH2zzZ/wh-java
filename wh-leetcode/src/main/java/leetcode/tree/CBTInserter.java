package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 919. 完全二叉树插入器
 * https://leetcode.cn/problems/complete-binary-tree-inserter/
 */
class CBTInserter {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(6);
        CBTInserter cbtInserter = new CBTInserter(treeNode);
        System.out.println(cbtInserter.insert(7));
        System.out.println(cbtInserter.insert(8));
        System.out.println(cbtInserter.get_root().val);

    }

    TreeNode root;
    Queue<TreeNode> queue = new LinkedList<>();

    public CBTInserter(TreeNode root) {
        this.root = root;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.peek();
            if (treeNode.left == null) {
                return;
            }
            queue.offer(treeNode.left);

            if (treeNode.right == null) {
                return;
            }

            queue.offer(treeNode.right);
            queue.poll();
        }

    }


    public int insert(int val) {
        TreeNode treeNode = new TreeNode(val);
        queue.offer(treeNode);

        if (root == null) {
            root = treeNode;
            return val;
        }

        TreeNode parentTreeNode = queue.peek();

        if (parentTreeNode.left == null) {
            parentTreeNode.left = treeNode;
            return parentTreeNode.val;
        }

        if (parentTreeNode.right == null) {
            parentTreeNode.right = treeNode;
            queue.poll();
            return parentTreeNode.val;
        }

        return parentTreeNode.val;
    }

    public TreeNode get_root() {
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}