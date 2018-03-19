//不乘以10，dfs
    public int sumNumbers(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return sumNumbersHelper(root, 0);
    }
        
    public int sumNumbersHelper(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }
        int sum = root.val + preSum;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return sumNumbersHelper(root.left, sum) + sumNumbersHelper(root.right, sum);
    }

    //DFS2
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode root, int prev) {
        if (root == null) {
            return 0;
        }
        
        int sum = prev + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        return dfs(root.right, sum) + dfs(root.left, sum);
    }
}

    //BFS:
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        LinkedList<Integer> sumQueue = new LinkedList<Integer>();
        nodeQueue.offer(root);
        sumQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            Integer num = sumQueue.poll();
            if (cur.left != null) {
                nodeQueue.offer(cur.left);
                sumQueue.offer((Integer) (num + cur.left.val));
            }
            if (cur.right != null) {
                nodeQueue.offer(cur.right);
                sumQueue.offer((Integer) (num + cur.right.val));
            }
            if (cur.right == null && cur.left == null) {
                res = (int)num + res;
            }
        }
        return res;
    }