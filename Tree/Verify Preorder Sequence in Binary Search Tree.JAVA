/*
	Verify Preorder Sequence in Binary Search Tree
		Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

	You may assume each number in the sequence is unique.

	Follow up:
	Could you do it using only constant space complexity?
*/

/*
	Solution 1

	Kinda simulate the traversal, keeping a stack of nodes (just their values) of which we're still in the left subtree.
     If the next number is smaller than the last stack value,
     then we're still in the left subtree of all stack nodes,
     so just push the new one onto the stack. But before that, pop all smaller ancestor values,
     as we must now be in their right subtrees (or even further, in the right subtree of an ancestor).
     Also, use the popped values as a lower bound, since being in their right subtree means
     we must never come across a smaller number anymore.
*/


/*
    Explation:
    1. Push to stack till you get higher element than the topmost element of the stack. [i.e. you keep pushing till you hit the leftmost leaf]
    2. If you find current value which is greater than the TOP of Stack, POP till you hit higher element, and also mark the last poped value, 
       which is your variable (Left_Tree_Highest). This variable is used to check whether the order is correct or not.
    3. In all the steps, if you find current element lesser than Left_Tree_Highest, then your tree is not a Binary Search Tree and it should return “NO”.
    4. If all the element traversed, and you have not hit “NO”, means given sequence follows the Binary Search Tree rule.

    Above step might be confusing, but take a pen and paper,
    try to follow the steps by taking stacks and Left_Tree_Highest values at each step.
     Once you are able to track it, you will able to co-relate it with the steps given above.

    So the basic idea is that at any point, left subtree’s highest element should be lowest for the untraversed elements [Right Tree].
*/
/*
   这道题极其坑，只给一个preorder 并不能确定一个 bst的准确序列

   我刚开始以为  4215768,如下不对，但是！其实可以排成如下另外一个合格的序列，这就对了，
   因此我们这里最关键的判断准则是 左子树的最高点元素应该是为遍历过的右子树的元素中最小的

          4                            4
        2   7                        2   5
      1  5 6  8                    1       7
                                          6  8
*/
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int val : preorder) {
            if (val < low) {
                return false;
            }
            while (!stack.empty() && val > stack.peek()) {
                low = stack.pop();
            }
            stack.push(val);
        }
        return true;
    }



//Solution 2 ... O(1) extra space, Same as above, but abusing the given array for the stack.

public boolean verifyPreorder(int[] preorder) {
    int low = Integer.MIN_VALUE;
    int i = -1;
    for (int val : preorder) {
        if (val < low)
            return false;
        while (i >= 0 && val > preorder[i])
            low = preorder[i--];
        preorder[++i] = val;
    }
    return true;
}


//Solution3 recursive 
/*
  先复习一下BST，给定一个节点，其左子树的所有节点都小于该节点，右子树的所有节点都大于该节点；preorder序列是指在遍历该BST的时候，先记录根节点，
  再遍历左子树，然后遍历右子树；所以一个preorder序列有这样一个特点，左子树的序列必定都在右子树的序列之前；并且左子树的序列必定都小于根节点，右子树的序列都大于根节点；

  根据上面的特点很容易通过递归的方式完成：
  如果序列只有一个元素，那么肯定是正确的，对应只有一个节点的树；如果多于一个元素，以当前节点为根节点；并从当前节点向后遍历，直到大于根节点的节点出现（或者到尾巴），
  那么根节点之后，该大节点之前的，是左子树；该大节点及之后的组成右子树；递归判断左右子树即可；
  那么什么时候一个序列肯定不是一个preorder序列呢？前面得到的右子树，如果在其中出现了比根节点还小的数，那么就可以直接返回false了；
*/

public boolean verifyPreorder(int[] preorder) {
    return verifyPreorder(preorder, 0, preorder.length);
}

public boolean verifyPreorder(int[] seq, int start, int end) {
    if (start + 1 >= end) {
        return true;
    }

    int root = seq[start];

    int i = start + 1;
    while (i < end && seq[i] < root) {
        i++;
    }

    if (i < end) {
        int j = i;
        while (j < end && seq[j] > root) {
            j++;
        }
        if (j < end) {
            return false;
        }

        return verifyPreorder(seq, start + 1, i) && verifyPreorder(seq, i, end);
    } else {
        return verifyPreorder(seq, start + 1, end);
    }
}