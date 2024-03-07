package March_06_Assignment;

import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class Q3_Burn_Binary_Tree {

    // Method to calculate the time taken for the given start node to burn the entire tree
    public static int timeToBurnTree(int[] nodes, int startNode) {
        TreeNode root = buildTree(nodes);
        return calculateBurningTime(root, startNode);
    }

    // Method to construct a binary tree from the given array representation
    public static TreeNode buildTree(int[] nodes) {
        if (nodes.length == 0)
            return null;

        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < nodes.length) {
            TreeNode current = queue.poll();
            if (nodes[i] != -1) {
                current.left = new TreeNode(nodes[i]);
                queue.add(current.left);
            }
            i++;
            if (i < nodes.length && nodes[i] != -1) {
                current.right = new TreeNode(nodes[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    // Method to calculate the burning time of the tree
    public static int calculateBurningTime(TreeNode root, int startNode) {
        if (root == null) {
            return -1; // The tree is empty
        }

        Map<TreeNode, Integer> burningTime = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode start = findNodeWithValue(root, startNode);

        // Initialize the queue with the start node and its adjacent nodes
        queue.add(start);
        burningTime.put(start, 0);

        int maxTime = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int time = burningTime.get(node);
            maxTime = Math.max(maxTime, time);

            if (node.left != null && !burningTime.containsKey(node.left)) {
                queue.add(node.left);
                burningTime.put(node.left, time + 1);
            }
            if (node.right != null && !burningTime.containsKey(node.right)) {
                queue.add(node.right);
                burningTime.put(node.right, time + 1);
            }
            TreeNode parent = findParent(root, node);
            if (parent != null && !burningTime.containsKey(parent)) {
                queue.add(parent);
                burningTime.put(parent, time + 1);
            }
        }

        return maxTime;
    }

    // Method to find the parent of a given node in the binary tree
    public static TreeNode findParent(TreeNode root, TreeNode node) {
        if (root == null || root == node) {
            return null; // No parent for the root node or if node is null
        }
        if ((root.left == node) || (root.right == node)) {
            return root; // Node found at left or right child of root
        }
        TreeNode leftResult = findParent(root.left, node);
        if (leftResult != null) {
            return leftResult; // Node found in the left subtree
        }
        return findParent(root.right, node); // Node found in the right subtree
    }

    // Method to find the node with a specific value in the binary tree
    public static TreeNode findNodeWithValue(TreeNode root, int value) {
        if (root == null || root.val == value) {
            return root;
        }
        TreeNode leftResult = findNodeWithValue(root.left, value);
        if (leftResult != null) {
            return leftResult;
        }
        return findNodeWithValue(root.right, value);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of nodes in the binary tree: ");
        int n = scanner.nextInt();

        int[] nodes = new int[n];
        System.out.println("Enter the values of the nodes (-1 for empty node): ");
        for (int i = 0; i < n; i++) {
            nodes[i] = scanner.nextInt();
        }

        System.out.println("Enter the value of the start node: ");
        int startNode = scanner.nextInt();

        int burningTime = timeToBurnTree(nodes, startNode);
        if (burningTime != -1) {
            System.out.println("Output: " + burningTime);
        } else {
            System.out.println("Output: Start node not found or tree is empty");
        }
    }
}
