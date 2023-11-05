import org.w3c.dom.Node;

import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// soltuion. my ideas were right about BFS and using nested loop ato captur size, but th issue was needing to track
    // level indices


class Solution {
    static class Pair{ // languages like python have a native Pair class, but java does not. Feels somewhat similar to
        // a hashmap where we store a node as a key and its value as its index in its row in the BT
        TreeNode node;
        int index;

        public Pair(TreeNode node, int index) { // contruct the whole Pair object so a node will always have an index
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        LinkedList<Pair> queue = new LinkedList<>(); // BFS queue but will now hold pairs
        Integer maxWidth = 0;

        queue.addLast(new Pair(root, 0)); // where we traverse the tree and use the node as key and assign index val
        while (queue.size() > 0) {
            Pair head = queue.getFirst(); // used for comparison later

            Integer currLevelSize = queue.size(); // determine size so we only loop through parents before finishing loop iteration
            Pair elem = null;
            for (int i = 0; i < currLevelSize; ++i) {
                elem = queue.removeFirst();
                TreeNode node = elem.node; // get node from the pair
                if (node.left != null) // create a new pair if child exists
                    queue.addLast(new Pair(node.left, 2 * elem.index));
                if (node.right != null)
                    queue.addLast(new Pair(node.right, 2 * elem.index + 1));
            }

            maxWidth = Math.max(maxWidth, elem.index - head.index + 1); // update max width by calculating span of indexes
        }

        return maxWidth;
    }
}

// having trouble thinkign about how to know the size of each row. how to differentiate one row from next in que?
// done thhis beforee.... nnestd whilee loop so can catch thee size each iteration? how to disclude nulls that are to L
// or R of 'real' nodes on final row
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        ArrayDeque<TreeNode> que = new ArrayDeque<>();
        int rowSize = 1;
        ArrayList<TreeNode> row = new ArrayList<>();

        que.add(root.val);
        while (!que.isEmpty()){
            TreeNode n = que.pop();
            if(que.left == null){
                que.add(new TreeNode(-1);
            }
            if(que.right == null){
                que.add(new TreeNode(-1));
            }
        }

    }
}

// THOUGHTS

// BFS but how to count for "ghost" nodes that could have their own "ghost" children? Maybe create a node with
// a negative val to indicate a placehholder? when down to final BFS row in queue, would need to iterate and say
// to start the width count when first non-neg # reached, have a temp counter ++ for each ghost found and add ghostCount + 1 if another real node found

// math where DFS find null at 0 and increment depth++ and multiply depth * 2, but how to figure out where the last null is like eexample 2? Seems BFS better approach...