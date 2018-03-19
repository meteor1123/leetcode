1. The Definition of Tree

	Root – The top node in a tree.
	Parent – The converse notion of a child.
	Siblings – Nodes with the same parent.
	Descendant – a node reachable by repeated proceeding from parent to child.
	Ancestor – a node reachable by repeated proceeding from child to parent.
	Leaf – a node with no children

	Level – The level of a node is defined by 1 + (the number of connections between the node and the root).
	Height of tree – The height of a tree is the number of edges on the longest downward path between the root and a leaf.

	Height of node – The height of a node is the number of edges on the longest downward path between that node and a leaf.
	Depth – The depth of a node is the number of edges from the node to the tree root node.

	Internal node – a node with at least one child.
	External node – a node with no children.
	Degree – number of sub trees of a node.
	Edge – connection between one node to another.
	Path – a sequence of nodes and edges connecting a node with a descendant.
	Forest – A forest is a set of n ≥ 0 disjoint trees.


1.Binary Tree Traversal Problem，树的各种遍历方法
	1.1 Preorder : root -> left -> right 中 左 右
			1.1.1 Recursive prefer
			//DFS
			public class Solution {
				public List<Integer> preorderTraversal(TreeNode root) {
			    	List<Integer> res = new ArrayList<>();
			    	preorder(res, root);
			    	return res;
			    }
			    public void preorder(List<Integer> res, TreeNode root) {
			        if (root == null) {
			            return;
			        }
			        res.add(root.val);
			        preorder(res, root.left);
			        preorder(res, root.right);
			    }
			}
			1.1.2 Iterative1 prefer
			public class Solution {
				public List<Integer> preorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        Stack<TreeNode> stack = new Stack<>();
			        while (root != null || !stack.isEmpty()) {
			            if (root != null) {
			                stack.push(root);//只要有左边的叶子节点就一直遍历入栈
			                res.add(root.val);// 跟inorder遍历的唯一区别在于， inorder中res.add(root.val)放到  遍历完最left的时候才add， 正好如何 
			                root = root.left;
			            } else {
			                root = stack.pop();//左边的遍历完了再出栈给root，将右节点的值 //加入结果
			                root = root.right;
			            }
			        }
			        return res;
			    }
			}
			1.1.3 Iterative2
			//Using stack to imitate the recursive
			public class Solution {
	    		public List<Integer> preorderTraversal(TreeNode root) {
			    	List<Integer> res = new ArrayList<>();
			    	if (root == null)
			    		return res;
			    	Stack<TreeNode> stack = new Stack<>();
			    	stack.push(root);
			    	while (!stack.isEmpty()) {
			    		TreeNode cur = stack.pop();
			    		res.add(cur.val);
			    		if (cur.right != null)
			    			stack.push(cur.right);
			    		if (cur.left != null)
			    			stack.push(cur.left);
			    	}
			    	return res;
	    		}
	    	}
	1.2 Inorder  left -> root -> right
			1.2.1 Recursive 
			public class Solution {
				public List<Integer> inorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        dfs(root, res);
			        return res;
			    }
			    public void dfs(TreeNode root, List<Integer> res) {
			        if (root == null)
			            return ;
			        dfs(root.left, res);
			        res.add(root.val);
			        dfs(root.right, res);
			    }
			}
			1.2.2 Iterative prefer
			public class Solution {
				public List<Integer> inorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        LinkedList<TreeNode> stack = new LinkedList<>();
			        while (root != null || !stack.isEmpty()) {
			            if (root != null) {
			                stack.push(root);
			                root = root.left;
			            } else {
			                root = stack.pop();
			                res.add(root.val);
			                root = root.right;
			            }
			        }
			        return res;
			    }
			}
	1.3 Postorder left -> right -> root
			1.3.1 Recursive
			public class Solution {
			    public List<Integer> postorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        dfs(root, res);
			        return res;
			    }
			    public void dfs(TreeNode root, ArrayList<Integer> res) {
			        if (root == null)
			            return ;
			        dfs(root.left, res);
			        dfs(root.right, res);
			        res.add(root.val);
			    }
			}
			1.3.2 Iterative1 prefer , real postorder
				/*
					后序遍历的情况就复杂多了。我们需要维护当前遍历的cur指针和前一个遍历的pre指针来追溯当前的情况
					注意这里是遍历的指针，并不是真正按后序访问顺序的结点）。具体分为几种情况：
						1）如果pre的左孩子或者右孩子是cur，那么说明遍历在往下走，按访问顺序继续，即如果有左孩子，则是左孩子进栈，
						   否则如果有右孩子，则是右孩子进栈，如果左右孩子都没有，则说明该结点是叶子，可以直接访问并把结点出栈了。
						2）如果反过来，cur的左孩子是pre，则说明已经在回溯往上走了，但是我们知道后序遍历要左右孩子走完才可以访问自己，
						   所以这里如果有右孩子还需要把右孩子进栈，否则说明已经到自己了，可以访问并且出栈了。
						3）如果cur的右孩子是pre，那么说明左右孩子都访问结束了，可以轮到自己了，访问并且出栈即可。
						   算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)。

					下面在弹栈的时候需要分情况一下：
						1）如果当前栈顶元素的右结点存在并且还没访问过（也就是右结点不等于上一个访问结点），那么就把当前结点移到右结点继续循环；
						2）如果栈顶元素右结点是空或者已经访问过，那么说明栈顶元素的左右子树都访问完毕，应该访问自己继续回溯了。
					*/
			public class Solution {
				public List<Integer> postorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        if (root == null)
			            return res;
			        Stack<TreeNode> stack = new Stack<>();
			        TreeNode pre = null;
			        while (root != null || !stack.isEmpty()) {
			            //往左探底
			            if (root != null) {
			                stack.push(root);
			                root = root.left;
			            } else {
			                TreeNode peekNode = stack.peek();
			                //一直往右探底
			                if (peekNode.right != null && peekNode.right != pre) {
			                    root = peekNode.right;
			                } else {
			                    //已经探底，加入这个值
			                    stack.pop();
			                    res.add(peekNode.val);
			                    pre = peekNode;
			                }
			                
			            }
			        }
			        return res;
			    }
			}
			1.3.3 Iterative2 
			/* 
			 	it was like cheating:) 
			 	Using root->right->left to do traversal just like preorder，
			 	and then reverse the result
			 	it will be left -> right -> root
			*/
			public class Solution {
			    public List<Integer> postorderTraversal(TreeNode root) {
			        List<Integer> res = new ArrayList<>();
			        Stack<TreeNode> stack = new Stack<TreeNode>();
			        while (!stack.isEmpty() || root != null) {
			            if (root != null) {
			                stack.push(root);
			                res.add(root.val);
			                root = root.right;
			            } else {
			                root = stack.pop();
			                root = root.left;
			            }
			        }
			        Collections.reverse(res);
			        return res;
			    }
			}
	1.4 Level Order
			1.4.1 Normal DFS
			public class Solution {
			    public List<List<Integer>> levelOrder(TreeNode root) {
			         List<List<Integer>> res = new ArrayList<>();
			         if (root == null) {
			             return res;
			         }
			         dfs(res, root, 0);
			         return res;
			    }
			    public void dfs(List<List<Integer>> res, TreeNode root, int depth) {
			        if (root == null) {
			            return;
			        }
			        if (res.size() <= depth) {
			            res.add(new ArrayList<>());
			        }
			        res.get(depth).add(root.val);
			        /*
			        	LevelOrder II problem
				        if (res.size() <= depth) {
	            			res.add(0, new ArrayList<>());
				        }
	        			res.get(res.size() - depth - 1).add(root.val);
        			*/
			        dfs(res, root.left, depth + 1);
			        dfs(res, root.right, depth + 1);
			    }
			}
			1.4.2 Normal BFS
			public class Solution {
				public List<List<Integer>> levelOrder(TreeNode root) {
					List<List<Integer>> res = new ArrayList<>();
					if (root == null)
						return res;
					Queue<TreeNode> queue = new LinkedList<>();
					queue.add(root);
					int curNodeNum = 1;//to record the number of node which in same level
					int nexNodeNum = 0;//to record the number of node which in next level
					List<Integer> item = new ArrayList<>();// store every result array in same level 
					while (!queue.isEmpty()) {
						TreeNode cur = queue.poll();
						curNodeNum--;
						item.add(cur.val);
						if (cur.left != null) {
							nexNodeNum++;
							queue.add(cur.left);
						}
						if (cur.right != null) {
							nexNodeNum++;
							queue.add(cur.right);
						}
						//假如一个层上的结点数量为0，则表示该层遍历完，开始下一层的遍历，next设为cur
						if (curNodeNum == 0) {
							curNodeNum = nexNodeNum;
							nexNodeNum = 0;
							res.add(item);
							//res.add(0, levelres);  the only place we need to modify, and it can be used in Level Order II problem
							item = new ArrayList<>();
						}
					}
					return res;
				}
			}
			1.4.3 ZigZag DFS
			public class Solution {
				public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
			        List<List<Integer>> res = new ArrayList<>();
			        if (root == null) {
			            return res;
			        }
			        dfs(res, 0, root);
			        return res;
			    }
			    public void dfs(List<List<Integer>> res, int depth, TreeNode root) {
			        if (root == null) {
			            return;
			        }
			        if (res.size() <= depth) {
			            res.add(new ArrayList<>());
			        }
			        if (depth % 2== 0) {
			            res.get(depth).add(root.val);
			        } else {
			            res.get(depth).add(0, root.val);
			        }
			        dfs(res, depth + 1, root.left);
			        dfs(res, depth + 1, root.right);
			    }
			}
			1.4.4 ZigZag BFS
			public class Solution {
				public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
					List<List<Integer>> res = new ArrayList<>();
					if (root == null)
						return res;
					boolean reverse = false;
					Queue<TreeNode> queue = new LinkedList<>();
					queue.add(root);
					int curNum = 1;
					int nextNum = 0;
					List<Integer> item = new ArrayList<>();
					while(!queue.isEmpty()) {
						TreeNode cur = queue.poll();
						curNum--;
						item.add(cur.val);
			            if (cur.left != null) {
							queue.add(cur.left);
							nextNum++;
						}
						if (cur.right != null) {
							queue.add(cur.right);
							nextNum++;
						}
						if (curNum == 0) {
							curNum = nextNum;
							nextNum = 0;
							if (reverse) {			
								Collections.reverse(item);//关键 reverse为true时，用Collections的reverse转置
								reverse = false;
							} else {
								reverse = true;
							}
							res.add(item);
							item = new ArrayList<>();
						}
					}
					return res;
				}
			}

	/*
        Why we need Morris?
        Morris Traversal方法可以做到这两点，与前两种方法的不同在于该方法只需要O(1)空间，而且同样可以在O(n)时间内完成。
        要使用O(1)空间进行遍历，最大的难点在于，遍历到子节点的时候怎样重新返回到父节点（假设节点中没有指向父节点的p指针），
        由于不能用栈作为辅助空间。为了解决这个问题，Morris方法用到了线索二叉树（threaded binary tree）的概念。
        在Morris方法中不需要为每个节点额外分配指针指向其前驱（predecessor）和后继节点（successor），
        只需要利用叶子节点中的左右空指针指向某种顺序遍历下的前驱节点或后继节点就可以了。
    */
	1.5 Morris Travseral
			1.5.1 Preorder 
		    /*
		    	Preorder Morris Solution:
		    	前序遍历与中序遍历相似，代码上只有一行不同，不同就在于输出的顺序。
				1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
				2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
					a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。输出当前节点（在这里输出，这是与中序遍历唯一一点不同）。当前节点更新为当前节点的左孩子。
					b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。当前节点更新为当前节点的右孩子。
				3. 重复以上1、2直到当前节点为空。
		    */

			//Morris用的pre，不管在哪个遍历里，实际上最后都是当前节点在inorder下的前驱结点！！！
			public class Solution {
				public List<Integer> preorderTraversal(TreeNode root) {
			        if (root == null) {
			            return Collections.emptyList();
			        }
			        List<Integer> res = new ArrayList<>();
			        TreeNode pre = null;
			        while (root != null) {
			            if (root.left == null) {
			                res.add(root.val);
			                root = root.right;
			            } else {
			                pre = root.left;
			                while (pre.right != null && pre.right != root) {
			                    pre = pre.right;
			                }
			                //假如pre.right 为空，叶子节点，记录root的信息，方便回溯
			                if (pre.right == null) {
			                    pre.right = root;
			                    res.add(root.val);//关键步骤，在这里将当前的root结点输出到结果，先将root输出，再遍历下面的叶子节点输出
			                    root = root.left;
			                }  else {
			                    pre.right = null;
			                    root = root.right;
			                }
			            } 
			        }
			        return res;
			    }
			}
			1.5.2 Inorder
			/*  Inorder Morris Solution:
		        例子图片在这里：http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
		        1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
		        2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
		            a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
		            b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
		        3. 重复以上1、2直到当前节点为空。
		    */
			public class Solution {
			    public List<Integer> inorderTraversal(TreeNode root) {
			        if(root == null) return new ArrayList<Integer>();
			        List<Integer> res = new ArrayList<Integer>();
			        TreeNode pre = null;
			        while(root != null){
			            if(root.left == null){
			                res.add(root.val);
			                root = root.right;
			            } else{
			                pre = root.left;
			                while (pre.right != null && pre.right != root){
			                    pre = pre.right;
			                }
			                if(pre.right == null){
			                    pre.right = root;
			                    root = root.left;
			                    //res.add(root.val); //preorder是在这里加入res
			                }else{
			                    pre.right = null;
			                    res.add(root.val);//与preorder唯一的不同
			                    root = root.right;
			                }
			            }
			        }
			        return res;
			    }
			}
	1.6 Vertical Level Traversal Of Tree
			//Print arbitrary binary tree by vertical levels / columns from left to right,high to low
			public class Solution {
				public List<List<Integer>> verticalLevelTraversalofTree(TreeNode root){
					List<List<Integer>> res = new ArrayList<>();
					//map's key is column, we assume the root column is zero, the left node will minus 1 ,and the right node will plus 1
					HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
					//use a queue to do bfs
					LinkedList<TreeNode> queue = new LinkedList<>();
					//use a HashMap to store the TreeNode and the according cloumn value
					HashMap<TreeNode, Integer> weight = new HashMap<>();
					if (root == null) {
						return res;
					}
					queue.add(root);
					weight.put(root, 0);
					int min = 0;
					while (!queue.isEmpty()) {
						TreeNode node = queue.poll();
						int w = weight.get(node);
						//if map doesn't has this column value, just create a list ,and put into map
						if (!map.containsKey(w)) {
							ArrayList<Integer> list = new ArrayList<>();
							list.add(node.val);
							map.put(w, list);
						} else {
							ArrayList<Integer> list = map.get(w);
							list.add(node.val);
							map.put(w, list);
						}
						//enqueue
						if (node.left != null) {
							queue.add(node.left);
							//put the node to weight hashmap
							weight.put(node.left, w - 1);
						}
						if (node.right != null) {
							queue.add(node.right);
							weight.put(node.right, w + 1);
						}
						//update min ,min means the minimum column value, which is the left most node
						min = Math.min(min, w);
					}
					//generate res
					while(map.containsKey(min)) {
						res.add(map.get(min++));
					}
					return res;
				}
			}

   


2. Path Problem In Binary Tree
	2.1 Get All Binary Tree Paths， 
		2.1.1 DFS
		//Hint: Do not need to backtracking
		//在终止返回的语句中，是res.add(item + root.val)， 而且往下走的时候item + root.val + “->"只会影响当前节点，并不会影响上一层的item使用
			public class Solution {
				public List<String> binaryTreePaths(TreeNode root) {
					List<String> res = new ArrayList<>();
					if (root == null) {
						return res;
					}
					helper(root, "", res);
					return res;
				}
				public void helper(TreeNode root, String item, List<String> res) {
					if (root.left == null && root.right == null) {
						res.add(item + root.val);
					}
					if (root.left != null) {
						helper(root.left, item + root.val + "->", res);
					}
					
					if (root.right != null) {
						helper(root.right, item + root.val + "->", res);
					}	
				}
			}
		2.1.2 BFS
			public List<String> binaryTreePaths(TreeNode root) {
		        List<String> res = new ArrayList<>();
		        if (root == null) {
		            return res;
		        }
		        Queue<TreeNode> queue = new LinkedList<>();
		        Queue<String> path = new LinkedList<>();
		        path.offer(root.val + "");
		        queue.offer(root);
		        while (!queue.isEmpty()) {
		            TreeNode cur = queue.poll();
		            String item = path.poll();
		            if (cur.left == null && cur.right == null) {
		                res.add(item);
		            }
		            if (cur.left != null) {
		                queue.offer(cur.left);
		                path.offer(item + "->" + cur.left.val + "");
		            }
		            if (cur.right != null) {
		                queue.offer(cur.right);
		                path.offer(item + "->" + cur.right.val + "");
		            }
		        }
		        return res;
		    }
	2.2 Path Sum Problem
		2.2.1 Path Sum I， find a path sum == sum, path (root to leaf)
			//DFS
			public boolean hasPathSum(TreeNode root, int sum) {
		        if (root == null) {
		            return false;
		        }
		        if (root.left == null && root.right == null) {
		            return root.val == sum;
		        }
		        int newSum = sum - root.val;
		        return hasPathSum(root.left, newSum) || hasPathSum(root.right, newSum);
		    }
		2.2.2 Path Sum II, need to return all the possible path node
			//DFS
			public class Solution {
			    public List<List<Integer>> pathSum(TreeNode root, int sum) {
			        List<List<Integer>> res = new ArrayList<>();
			        if (root == null) {
			            return res;
			        }
			        dfs(root, sum, res, new ArrayList<Integer>());
			        return res;
			    }
			    public void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> item) {
			        if (root == null) {
			            return;
			        }
			        item.add(root.val);
			        if (root.left == null && root.right == null) {
			            if (root.val == sum) {
			                res.add(new ArrayList<>(item));
			            }
			        }
			        dfs(root.left, sum - root.val, res, item);
			        dfs(root.right, sum - root.val, res, item);
			        item.remove(item.size() - 1);
			    }
			}
		2.2.3 Maximum Path Sum
			//DFS
			public class Solution {
				int max;
				public int maxPathSum(TreeNode root) {
					if (root == null) {
						return 0;
					}
					max = Integer.MIN_VALUE;
					findMax(root);
					return max;
				}
				public int findMax(TreeNode root) {
					if (root == null) {
						return 0;
					}
					int leftSum = Math.max(0, findMax(root.left));
					int rightSum = Math.max(0, findMax(root.right));
					max = Math.max(leftSum + rightSum + root.val, max);
					return Math.max(leftSum, rightSum) + root.val;
				}
			}
		2.2.4 Sum Root To Leaf Numbers
			//DFS
			public class Solution {
			    public int sumNumbers(TreeNode root) {
			        if (root == null) {
			            return 0;
			        }
			        return pathSum(root, 0);
			    }
			    public int pathSum(TreeNode root, int sum) {
			        if (root == null) {
			            return 0;
			        }
			        int newSum = sum * 10 + root.val;
			        if (root.left == null && root.right == null) {
			            return newSum;
			        }
			        return pathSum(root.left, newSum) + pathSum(root.right, newSum);
			    }
			}
			//BFS
			public int sumNumbers(TreeNode root) {
		        if (root == null) {
		            return 0;
		        }
		        int res = 0;
		        Queue<TreeNode> queue = new LinkedList<>();
		        Queue<Integer> pathSum = new LinkedList<>();
		        queue.offer(root);
		        pathSum.offer(root.val);
		        while (!queue.isEmpty()) {
		            TreeNode cur = queue.poll();
		            int newSum = pathSum.poll();
		            if (cur.left == null && cur.right == null) {
		                res += newSum;
		            }
		            if (cur.left != null) {
		                queue.offer(cur.left);
		                pathSum.offer(newSum * 10 + cur.left.val);
		            }
		            if (cur.right != null) {
		                queue.offer(cur.right);
		                pathSum.offer(newSum * 10 + cur.right.val);
		            }
		        }
		        return res;
    		}
    2.3 Binary Tree Longest Consecutive Sequence Path
			 //   1
			 //    \
			 //     3
			 //    / \
			 //   2   4
			 //        \
			 //         5
		     //Longest consecutive sequence path is 3-4-5, so return 3.
			 //   2
			 //    \
			 //     3
			 //    / 
			 //   2    
			 //  / 
			 // 1
		     //Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
			public class Solution {
			    int max;
			    public int longestConsecutive(TreeNode root) {
			        if (root == null) {
			            return 0;
			        }
			        max = 0;
			        dfs(root, root, 0);
			        return max;
			    }
			    public void dfs(TreeNode cur, TreeNode pre, int count) {
			        if (cur == null) {
			            return;
			        }
			        count = pre.val + 1 == cur.val ? count + 1 : 1;
			        max = Math.max(count, max);
			        dfs(cur.left, cur, count);
			        dfs(cur.right, cur, count);
			    }
			}


3. The Property Of Tree
	3.1 Depth And Height
		3.1.1 Height Of Tree
		    public int height(TreeNode root) {
		        if (root == null) {
		            return 0;
		        }
		        return 1 + height(root.left);
		    }
		3.1.2 Depth Of Tree(Maximum Depth)
			public int depth(TreeNode root) {
		        if (root == null) {
		            return 0;
		        }
		        return Math.max(depth(root.left), depth(root.right)) + 1;
    		}
		3.1.3 Minimum Depth
			public int minDepth(TreeNode root) {
		    	if (root == null)
		    		return 0;
		    	if (root.left == null)
		    		return minDepth(root.right) + 1;
		    	if (root.right == null)
		    		return minDepth(root.left) + 1;
		    	return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
		    }
		3.1.4 Balanced Binary Tree
			public boolean isBalanced(TreeNode root) {
		        if (root == null) {
		            return true;
		        }
		        int leftDepth = depth(root.left);//3.1.2 Depth of Tree
		        int rightDepth = depth(root.right);
		        if (Math.abs(leftDepth - rightDepth) > 1) {
		            return false;
		        } 
		        return isBalanced(root.left) && isBalanced(root.right);
		    }
		3.1.5 Count Complete Tree
			/*
				In a complete binary tree every level, except possibly the last, is completely filled, 
				and all nodes in the last level are as far left as possible. 
				It can have between 1 and 2h nodes inclusive at the last level h.
			*/
		    public int countNodes(TreeNode root) {
		        int rootHeight = height(root);//3.1.1 Height of Tree Method
		        if (rootHeight < 0) {
		            return 0;
		        } 
		        if (height(root.right) == rootHeight - 1) {
		            return (1 << (rootHeight - 1)) - 1 + countNodes(root.right) + 1;// 2^(h - 1)(左子树) - 1 + count(root.right) + 1(root);
		            /*
		                     1
		                   /   \
		                  2     3
		                 / \   / 
		                4   5 6
		            */
		        } else {
		            /*
		                     1
		                   /  \
		                  2    3
		                 / \
		                4   5
		            */
		            return (1 << (rootHeight - 2)) - 1 + countNodes(root.left) + 1;// 2^(h - 2)（右子树） - 1 + cout(root.left) + 1(root);
		        }
		    }
    3.2 SubTree, Symmetric Tree, Same Tree, Balanced Binary Tree
    	3.2.1 Same Tree
    		public boolean isSameTree(TreeNode p, TreeNode q) {
		        if (p == null && q == null) {
		            return true;
		        }
		        if (p == null || q == null) {
		            return false;
		        }
		        if (p.val != q.val) {
		            return false;
		        }
		        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		    }
		3.2.2 Symmetric Tree
			public boolean isSymmetric(TreeNode root) {
		    	if (root == null) {
		    		return false;
		    	}
		    	return isSymmetric(root.left, root.right);
		    }
		    public boolean isSymmetric(TreeNode p, TreeNode q) {
		    	if (p == null && q == null) {
		    		return true;
		    	}
		    	if (p == null || q == null) {
		    		return false;
		    	}
		    	return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
		    }
		3.2.3 SubTree
	        // T2 is a subtree of T1 in the following case:
			//        1                3
			//       / \              / 
			// T1 = 2   3      T2 =  4
			//         /
			//        4
			// T2 isn't a subtree of T1 in the following case:

			//        1               3
			//       / \               \
			// T1 = 2   3       T2 =    4
			//         /
			//        4
	    	/**
		     * @param T1, T2: The roots of binary tree.
		     * @return: True if T2 is a subtree of T1, or false.
		     */
		    public boolean isSubtree(TreeNode T1, TreeNode T2) {
		        if (T2 == null) {
		            return true;
		        }
		        if (T1 == null) {
		            return false;
		        }
		        return isSameTree(T1, T2) || isSubtree(T1.left, T2) || isSubtree(T1.right, T2);
		    }
		3.2.4 Count Univalue Subtrees
			public class Solution {
			    int count = 0;
			    public int countUnivalSubtrees(TreeNode root) {
			        all(root, 0);
			        return count;
			    }
			    public boolean all(TreeNode root, int val) {
			        if (root == null)
			            return true;
			        if (!all(root.left, root.val) | !all(root.right, root.val))// || 或，判断第一个表达式的结果如果是正确的话，就会进入if内，不会判断后面的表达式，因此会漏算后面的计数，这就是short-circuit
			                                                                   // | 或 无论前面的结果如何，都会将所有表达式运行
			            return false;
			        count++;
			        return root.val == val;
			    }
			}
	3.3 Lowest Common Ancestor Of A Binary Tree
			public class Solution {
			    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			        if (root == null || root == p || root == q) {
			            return root;
			        }
			        TreeNode leftChild = lowestCommonAncestor(root.left, p, q);
			        TreeNode rightChild = lowestCommonAncestor(root.right, p, q);
			        if (leftChild != null && rightChild != null) {
			            return root;
			        }
			        if (leftChild != null) {
			            return leftChild;
			        }
			        if (rightChild != null) {
			            return rightChild;
			        }
			        return null;
			    }
			}

		    
4. Binary Search Tree 
		4.1 Binary Search Tree Iterator //Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
			public class BSTIterator {
			    private Stack<TreeNode> stack = new Stack<TreeNode>();
			    public BSTIterator(TreeNode root) {
			        pushAll(root);
			    }
			    /** @return whether we have a next smallest number */
			    public boolean hasNext() {
			        return !stack.isEmpty();
			    }
			    /** @return the next smallest number */
			    public int next() {
			        TreeNode tmpNode = stack.pop();
			        pushAll(tmpNode.right);
			        return tmpNode.val;
			    }
			    private void pushAll(TreeNode node) {
			        while (node != null) {
			            stack.push(node);
			            node = node.left;
			        }
			    }
			}
		4.2 Closest Binary Search Tree Value
			//Iterative
			public int closestValue(TreeNode root, double target) {
				int closet = root.val;
				while (root != null) {
					if (Math.abs(root.val - target) <= Math.abs(closet - target)) {
						closet = root.val;
					}
					root = root.val < target ? root.right : root.left;
				}
				return closet;
			}
			//Recursive
			public int closestValue(TreeNode root, double target) {
		        TreeNode child = target < root.val ? root.left : root.right;
		        if (child == null) {
		            return root.val;
		        }
		        int closetVal = closestValue(child, target);
		        return Math.abs(closetVal - target) < Math.abs(root.val - target) ? closetVal : root.val;
		    }
		4.3 Closest Binary Search Tree Value II
			/*
					中序遍历结果是将树中元素从小到大排列，逆式的中序遍历即先遍历右子树再访问根节点最后遍历左子树会得到树中元素从大到小排列的结果，
		    	因此可通过中序遍历获取最接近target节点的predecessors，通过逆中序遍历获取最接近target节点的successors,
		    	然后merge perdecessors 和 successors 获取最接近target节点的 k个节点值。 注意到在中序遍历时遇到比target 大的节点即停止，
		    	因为由BST的性质可知后面的元素均会比target 大，即所有target的predecessors均已找到，同理逆中序遍历时遇到不大于 target的节点即可停止递归。 
			*/
		    O(n + k)
			public class Solution {
				    public List<Integer> closestKValues(TreeNode root, double target, int k) {
				        List<Integer> res = new ArrayList<>();
				        Stack<Integer> pre = new Stack<>();
				        Stack<Integer> post = new Stack<>();
				        
				        inorder(root, target, false, pre);
				        inorder(root, target, true, post);

				        while (k-- > 0) {
				            if (pre.isEmpty()) {
				                res.add(post.pop());
				            } else if (post.isEmpty()) {
				                res.add(pre.pop());
				            } else if (Math.abs(pre.peek() - target) < Math.abs(post.peek() - target)) {
				                res.add(pre.pop());
				            } else {
				                res.add(post.pop());
				            }
				        }
				        return res;
				    }

				    public void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
				        if (root == null) {
				            return;
				        }
				        inorder(reverse ? root.right : root.left, target, reverse, stack);
				        // early terminate, no need to traverse the whole tree
				        if ((reverse && root.val <= target) || (!reverse && root.val > target)) {
				            return;
				        }
				        // track the value of current node
				        stack.push(root.val);
				        inorder(reverse ? root.left : root.right, target, reverse, stack);
				    }
			}
		4.4 Convert Sorted Array To Binary Search Tree
			public class Solution {    
			    public TreeNode sortedArrayToBST(int[] num) {
			    	//Corner case
			    	if (num == null || num.length <= 0)
			    		return null; 
			    	//use a dfs helper to  generate the bst
			    	return dfs(num, 0, num.length - 1);
				}
				//as we know,  using inorder traverse the bst ,we will get a ascending array,
				public TreeNode dfs(int[] num, int start, int end) {
					//this is the termination condition.
					if (start > end) //mean we reach the null node
						return null;
					int mid = (start + end) / 2; //every time we let mid = start + end / 2
					TreeNode root = new TreeNode(num[mid]);//generate a root node
					//and recursive genarate the left tree and right tree.
					root.left = dfs(num, start, mid - 1);
					root.right = dfs(num, mid + 1, end);
					return root; 
				}
			}
		4.5 Inorder Successor In BST
			public class Solution {
			    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
			        if (root == null) {
			            return null;
			        }
			        if (root.val <= p.val) {
			            return inorderSuccessor(root.right, p);
			        } else {
			            TreeNode left = inorderSuccessor(root.left, p);
			            return (left != null) ? left : root;
			        }
			    }
			}
		4.6 Inorder Predecessor In BST
			public class Solution {
				public TreeNode predecessor(TreeNode root, TreeNode p) {
				  	if (root == null)
				    	return null;
				  	if (root.val >= p.val) {
				    	return predecessor(root.left, p);
				  	} else {
				    	TreeNode right = predecessor(root.right, p);
				    	return (right != null) ? right : root;
				  	}
				}
			}
		4.7 Kth Smallest Element In A BST
			public class Solution {
			    public int kthSmallest(TreeNode root, int k) {
			        int leftNum = countNode(root.left);
			        if (leftNum + 1 == k) {
			            return root.val;
			        } else if (leftNum + 1 < k) {
			            return kthSmallest(root.right, k - leftNum - 1);
			        } else {
			            return kthSmallest(root.left, k);
			        }
			    }
			    
			    public int countNode(TreeNode root) {
			        if (root == null) {
			            return 0;
			        }
			        return countNode(root.left) + countNode(root.right) + 1;
			    }
			}
		4.8 Lowest Common Ancestor Of A Binary Search Tree
			    //iterative1 prefer
			    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			        while (true) {
			            if (root.val > p.val && root.val > q.val) {
			                root = root.left;
			            } else if (root.val < p.val && root.val < q.val) {
			                root = root.right;
			            } else {
			                return root;
			            }
			        }
			    }
			    //recursive1 prefer
			    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			        if (root.val > p.val && root.val > q.val) {
			            return lowestCommonAncestor(root.left, p, q);
			        } else if (root.val < p.val && root.val < q.val) {
			            return lowestCommonAncestor(root.right, p, q);
			        } else {
			            return root;
			        }
			    }
		4.9 Recover Binary Search Tree
				public class Solution {
				    public TreeNode firstElement = null;
				    public TreeNode secondElement = null;
				    public TreeNode preElement = new TreeNode(Integer.MIN_VALUE);
				    public void recoverTree(TreeNode root) {
				        if (root == null) {
				            return;
				        }
				        dfs(root);
				        int temp = firstElement.val;
				        firstElement.val = secondElement.val;
				        secondElement.val = temp;
				    }
				    public void dfs(TreeNode root) {
				        if (root == null) {
				            return ;
				        }
				        dfs(root.left);
				        if (firstElement == null && preElement.val > root.val) {
				            firstElement = preElement;
				        }
				        if (firstElement != null && preElement.val > root.val) {
				            secondElement = root;
				        }
				        preElement = root;
				        dfs(root.right);
				    }
				}
		4.10 Validate Binary Search Tree
			public class Solution {
			    public boolean isValidBST(TreeNode root) {
			        if (root == null) {
			            return true;
			        }
			        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
			    }
			    public boolean isValid(TreeNode root, long min, long max) {
			        if (root == null) {
			            return true;
			        }
			        if (min < root.val && root.val < max) {
			            return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
			        } else {
			            return false;
			        }
			    }
			}
		4.11 Verify Preorder Sequence In Binary Search Tree
			public class Solution {
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
			}
		4.12 Unique Binary Search Trees
			public class Solution {
			    public int numTrees(int n) {
			        if (n == 0 || n == 1) {
			            return n;
			        }
			        int[] dp = new int[n + 1];
			        dp[0] = 1;
			        dp[1] = 1;
			        for (int i = 2; i <= n; i++) {
			            for (int j = 0; j < i; j++) {
			                dp[i] += dp[j] * dp[i - j - 1];
			            }
			        }
			        return dp[n];
			    }
			}
		4.13 Unique Binary Search Trees II(return all the unique BST)
			public class Solution {
			    public List<TreeNode> generateTrees(int n) {
			        return helper(1, n);
			    }
			    public List<TreeNode> helper (int left, int right) {
			        List<TreeNode> res = new ArrayList<>();
			        if (left > right) {
			            res.add(null);
			            return res;
			        }
			        for (int i = left; i <= right; i++) {
			            List<TreeNode> leftList = helper(left, i - 1);
			            List<TreeNode> rightList = helper(i + 1, right);
			            for (int j = 0; j < leftList.size(); j++) {
			                for (int k = 0; k < rightList.size(); k++) {
			                    TreeNode root = new TreeNode(i);
			                    root.left = leftList.get(j);
			                    root.right = rightList.get(k);
			                    res.add(root);
			                }
			            }
			        }
			        return res;
			    }
			}
		4.14 Insert Node In A Binary Search Tree
			/*
				Insert Node in a Binary Search Tree
				Given a binary search tree and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.

				Have you met this question in a real interview? Yes
				Example
				Given binary search tree as follow, after Insert node 6, the tree should be:

				  2             2
				 / \           / \
				1   4   -->   1   4
				   /             / \ 
				  3             3   6
				Challenge
				Can you do it without recursion?
			 */

			//Recursive
			public class Solution {
			    public TreeNode insertNode(TreeNode root, TreeNode node) {
			        // write your code here
			        if (root == null) {
			            return node;
			        }
			        if (root.val < node.val) {
			           root.right = insertNode(root.right, node);
			        }
			        if (root.val > node.val) {
			            root.left = insertNode(root.left, node);
			        }
			        
			        return root;
			    }
			}

			//Iterative
			public class Solution {
				public TreeNode insertNode(TreeNode root, TreeNode node) {
				    if (root == null) {
				        return node;
				    }
				    TreeNode cur = root;
				    TreeNode pre = null;
				    while (cur != null) {
				        pre = cur;
				        if (node.val > cur.val) {
				            cur = cur.right;
				        } else if (node.val < cur.val) {
				            cur = cur.left;
				        } 
				    }
				    
				    if (pre.val < node.val) {
				        pre.right = node;
				    } else {
				        pre.left = node;
				    }
				    return root;
				}
			}
		4.15 Remove Node In Binary Search Tree
			/*
				Remove Node In Binary Search Tree
				Given a root of Binary Search Tree with unique value for each node. Remove the node with given value. If there is no such a node with given value in the binary search tree, do nothing. You should keep the tree still a binary search tree after removal.
				Example
				Given binary search tree:

				    5
				   / \
				  3   6
				 / \
				2   4
				Remove 3, you can either return:

				    5
				   / \
				  2   6
				   \
				    4
				or

				    5
				   / \
				  4   6
				 /
				2
			 */
			/*
				case1: 要删除的结点只有left child -> 将left child 接到父节点
				case2: 要删除的结点只有right child -> 将right child 接到父节点
				case3: 要删除的结点有两个孩子
						(1) find the minimum node in the right children of root
						(2) change the root to the minimum node in right children
						(3) 将这个最小的右孩子结点连接原先的左右孩子结点。
			 */
			public class Solution {
			    public TreeNode removeNode(TreeNode root, int value) {
			        // write your code here
			        if (root == null) {
			            return root;
			        }
			        if (value < root.val) {
			            root.left = removeNode(root.left, value);
			        } else if (value > root.val) {
			            root.right = removeNode(root.right, value);
			        } else {
			        	//case1:left child == null
			            if (root.left == null) {
			                return root.right;
			            //case2: right child == null
			            } else if (root.right == null) {
			                return root.left;
			                
			            //case3: the delete node has two children
			            } else {
			                TreeNode cur = root;
			                root = findMin(root.right);
			                root.right = deleteMin(cur.right);
			                root.left = cur.left;
			            }
			        }
			        return root;
			    }
			    
			    //找root结点下的最小点
			    public TreeNode findMin(TreeNode root) {
			        if (root.left == null) {
			            return root;
			        }
			        return findMin(root.left);
			    }
			    //删除root结点下的最小结点，只需要简单的将root.left = root.right
			    public TreeNode deleteMin(TreeNode root) {
			        if (root.left == null) {
			            return root.right;
			        }
			        root.left = deleteMin(root.left);
			        return root;
			    }

			}
		4.16 Verify Preorder Serialization Of A Binary Tree
			/*
				Verify Preorder Serialization of a Binary Tree
				One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

				     _9_
				    /   \
				   3     2
				  / \   / \
				 4   1  #  6
				/ \ / \   / \
				# # # #   # #
				For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

				Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

				Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

				You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

				Example 1:
				"9,3,4,#,#,1,#,#,2,#,6,#,#"
				Return true

				Example 2:
				"1,#"
				Return false

				Example 3:
				"9,#,#,1"
				Return false
			 */

			//Solution1: prefer
			/*
				In a binary tree, if we consider null as leaves, then

				all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
				all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
				Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree.
				 When the next node comes, we then decrease diff by 1, because the node provides an in degree. 
				 If the node is not null, we increase diff by 2, because it provides two out degrees. 
				 If a serialization is correct, diff should never be negative and diff will be zero when finished.
			 */
			public class Solution {
			    public boolean isValidSerialization(String preorder) {
			       String[] nodes = preorder.split(",");
			       int diff = 1;
			       for (String node : nodes) {
			           if (--diff < 0) {
			               return false;
			           } 
			           if (!node.equals("#")) {
			               diff += 2;
			           }
			       }
			       return diff == 0;
			    }
			}


5. Binary Tree Transform
		5.1 Binary Tree Upside Down
			// Given a binary tree where all the right nodes are either leaf nodes with a sibling 
			// (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where 
			// the original right nodes turned into left leaf nodes. Return the new root.
			// Given a binary tree {1,2,3,4,5},
			//     1
			//    / \
			//   2   3
			//  / \
			// 4   5
			// return the root of the binary tree [4,5,2,#,#,3,1].
			//    4
			//   / \
			//  5   2
			//     / \
			//    3   1  
			public class Solution {
				//DFS 
				//规则 一个结点的左孩子的左孩子是这个结点的右孩子，一个结点的左孩子的右节点是它自己
				public TreeNode upsideDownBinaryTree(TreeNode root) {
					if (root == null || (root.left == null && root.right == null)) {
						return root;
					}
					//每次返回的newRoot 
					TreeNode newRoot = upsideDownBinaryTree(root.left);
					//每次迭代的将当前root的right结点 赋值给下面left孩子的 right节点，
					//将root赋值给下面left孩子的left结点，然后设置root.left , root.right
					root.left.left = root.right;//该节点的父亲节点的右节点为该节点的左孩子
					root.left.right = root;//该节点的父亲节点为该节点的右孩子
					root.left = null;//父节点的左右孩子设空
					root.right = null;//父节点的左右孩子设空
					//依然返回root结点
					return newRoot;

				}
				//BFS
				public TreeNode upsideDownBinaryTree(TreeNode root) {
					TreeNode cur = root;
					TreeNode pre = null;
					TreeNode next = null;
					TreeNode temp = null;
					while (cur != null) {
						next = cur.left;
						cur.left = temp;
						temp = cur.right;
						cur.right = pre;
						pre = cur;
						cur = next;
					}
					return pre;
				}
			}
		5.2 Flatten Binary Tree To Linked List（in-place）
					 1
			        / \
			       2   5
			      / \   \
			     3   4   6     ->  	   1
									    \
									     2
									      \
									       3
									        \
									         4
									          \
									           5
									            \
									             6 
			public class Solution {
			    TreeNode lastvisited = null;
			    public void flatten(TreeNode root) {
			        if (root == null) {
			            return;
			        }
			        TreeNode realRight = root.right;
			        if (lastvisited != null) {
			            lastvisited.left = null;
			            lastvisited.right = root;
			        }
			        lastvisited = root;
			        flatten(root.left);
			        flatten(realRight);
			    }
			}
		5.3 Invert Binary Tree
			public class Solution {
			    public TreeNode invertTree(TreeNode root) {
			        if (root == null) {
			            return null;
			        }
			        TreeNode tempLeft = root.left;
			        root.left = invertTree(root.right);
			        root.right = invertTree(tempLeft);
			        return root;
			    }
			}
		5.4 Populating Next Right Pointers In Each Node(Perfect Binary Tree)
			public class Solution {
				//Recursive
			    public void connect(TreeLinkNode root) {
			        if (root == null) {
			            return;
			        }
			        if (root.left != null) {
			            root.left.next = root.right;
			        }
			        if (root.right != null && root.next != null) {
			            root.right.next = root.next.left;
			        }
			        connect(root.left);
			        connect(root.right);
			    }
    			//Iterative
			    public void connect(TreeLinkNode root) {
			        if (root == null) {
			            return;
			        }
			        TreeLinkNode cur = root;
			        while (cur != null && cur.left != null) {
			            cur.left.next = cur.right;
			            if (cur.next != null) {
			                cur.right.next = cur.next.left;
			                cur = cur.next;
			            } else {
			                root = root.left;
			                cur = root;
			            }
			        }
			    }
			}
		5.5	Populating Next Right Pointers In Each Node II(No Need To A Perfect Binary Tree)
				public void connect(TreeLinkNode root) {
				    //tempChild 是每层的头结点（dummy node） tempChild.next是每层最左边的结点
			        TreeLinkNode tempChild = new TreeLinkNode(0);
			        while (root != null) {
			            TreeLinkNode curChild = tempChild;//用curChild 来遍历root下的left和right child 并设置next
			            while (root != null) {
			                if (root.left != null) {
			                    curChild.next = root.left;
			                    curChild = curChild.next;
			                }
			                if (root.right != null) {
			                    curChild.next = root.right;
			                    curChild = curChild.next;
			                }
			                //root的左右儿子都设置完后，root向右前进.
			                root = root.next;
			            }
			            //遍历完一层的root，也就是设置完root这一层的结点的儿子们的next指针后， 将tempChild 赋值给root，因为tempChild是root下面那一层的最左边的孩子
			            root = tempChild.next;
			            tempChild.next = null;
			        }
			    }



6. Construct Binary Tree
		6.1 Construct Binary Tree From Preorder And Inorder Traversal
			public class Solution {
			    public TreeNode buildTree(int[] preorder, int[] inorder) {
			        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
			    }
			    public TreeNode build(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
			        if (preStart > preEnd || inStart > inEnd) {
			            return null;
			        }
			        int rootVal = pre[preStart];
			        int rootIndex = 0;
			        for (int i = inStart; i <= inEnd; i++) {
			            if (in[i] == rootVal) {
			                rootIndex = i;
			                break;
			            }
			        }
			        int len = rootIndex - inStart;
			        TreeNode root = new TreeNode(rootVal);
			        root.left = build(pre, preStart + 1, preStart + len, in, inStart, rootIndex - 1);
			        root.right = build(pre, preStart + len + 1, preEnd, in, rootIndex + 1, inEnd);
			        return root;
			    }
			}
		6.2 Construct Binary Tree From Inorder And Postorder Traversal
			public class Solution {
			    public TreeNode buildTree(int[] inorder, int[] postorder) {
			        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
			    }
			    public TreeNode build(int[] in, int inStart, int inEnd, int[] post, int postStart, int postEnd) {
			        if (inStart > inEnd || postStart > postEnd) {
			            return null;
			        }
			        int rootVal = post[postEnd];
			        int rootIndex = postEnd;
			        for (int i = inStart; i <= inEnd; i++) {
			            if (in[i] == rootVal) {
			                rootIndex = i;
			                break;
			            }
			        }
			        int len = rootIndex - inStart;
			        TreeNode root = new TreeNode(rootVal);
			        root.left = build(in, inStart, rootIndex - 1, post, postStart, postStart + len - 1);
			        root.right = build(in, rootIndex + 1, inEnd, post, postStart + len, postEnd - 1);
			        return root;
			    }
			}
		6.3 Serialize And Deserialize Binary Tree
				//Recursive
				public class Solution {
					//
					public String serialize(TreeNode root) {
				        StringBuilder sb = new StringBuilder();
				        buildString(root, sb);
				        return sb.toString();
				    }
				    private void buildString(TreeNode node, StringBuilder sb) {
				        if (node == null) {
				            sb.append("#").append(" ");
				        } else {
				            sb.append(node.val).append(" ");
				            buildString(node.left, sb);
				            buildString(node.right, sb);
				        }
				    }
				    // Decodes your encoded data to tree.
				    public TreeNode deserialize(String data) {
				        if (data == null) {
				            return null;
				        }
				        String[] strs = data.split(" ");
				        Queue<String> queue = new LinkedList<>();
				        queue.addAll(Arrays.asList(strs));
				        return buildTree(queue);
				    }
				    
				    public TreeNode buildTree(Queue<String> queue) {
				        String val = queue.poll();
				        if (val.equals("#")) {
				            return null;
				        } else {
				            TreeNode node = new TreeNode(Integer.valueOf(val));
				            node.left = buildTree(queue);
				            node.right = buildTree(queue);
				            return node;
				        }
				    }
				}
				//Iterative
				public class Codec {
					public String serialize(TreeNode root) {
				        if (root == null) {
				            return "";
				        }
				        Queue<TreeNode> queue = new LinkedList<>();
				        queue.offer(root);
				        StringBuilder sb = new StringBuilder();
				        while (!queue.isEmpty()) {
				            int size = queue.size();
				            for (int i = 0; i < size; i++) {
				                TreeNode cur = queue.poll();
				                if (cur != null) {
				                    queue.offer(cur.left);
				                    queue.offer(cur.right);
				                }
				                String val = cur == null ? "#" : String.valueOf(cur.val);
				                sb.append(val);
				                sb.append(",");
				            }
				        }
				        sb.deleteCharAt(sb.length() - 1);
				        return sb.toString();
				    }
					// Decodes your encoded data to tree.
				     public TreeNode deserialize(String data) {
				        if (data == null || data.length() == 0) {
				            return null;
				        }
				        String[] val = data.split(",");
				        TreeNode root = new TreeNode(Integer.valueOf(val[0]));
				        Queue<TreeNode> queue = new LinkedList<>();
				        queue.offer(root);
				        //i控制string数组里的值，queue控制遍历的TreeNode，val[i] ,val[i + 1] 确保是 cur的left和right节点值，则有 
				        for (int i = 1; i < val.length; i += 2) {
				            TreeNode cur = queue.poll();
				            if (!"#".equals(val[i])) {
				                TreeNode left = new TreeNode(Integer.valueOf(val[i]));
				                cur.left = left;
				                queue.offer(left);
				            }
				            if (i + 1 < data.length() && !"#".equals(val[i + 1])) {
				                TreeNode right = new TreeNode(Integer.valueOf(val[i + 1]));
				                cur.right = right;
				                queue.offer(right);
				            }
				        }
				        return root;
				    }
				}

		6.4  Verify Preorder Serialization Of A Binary Tree
				/*
					Verify Preorder Serialization of a Binary Tree
					One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

					     _9_
					    /   \
					   3     2
					  / \   / \
					 4   1  #  6
					/ \ / \   / \
					# # # #   # #
					For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

					Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

					Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

					You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

					Example 1:
					"9,3,4,#,#,1,#,#,2,#,6,#,#"
					Return true

					Example 2:
					"1,#"
					Return false

					Example 3:
					"9,#,#,1"
					Return false
				 */

				//Solution1: prefer
				/*
					In a binary tree, if we consider null as leaves, then

					all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
					all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
					Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree.
					 When the next node comes, we then decrease diff by 1, because the node provides an in degree. 
					 If the node is not null, we increase diff by 2, because it provides two out degrees. 
					 If a serialization is correct, diff should never be negative and diff will be zero when finished.
				 */
				public class Solution {
				    public boolean isValidSerialization(String preorder) {
				       String[] nodes = preorder.split(",");
				       int diff = 1;
				       for (String node : nodes) {
				           if (--diff < 0) {
				               return false;
				           } 
				           if (!node.equals("#")) {
				               diff += 2;
				           }
				       }
				       return diff == 0;
				    }
				}


				//Solution2: use depth to check
				public class Solution {
				    public boolean isValidSerialization(String preorder) {
				        if (preorder == null || preorder.length() == 0) {
				            return false;
				        }
				        String[] strs = preorder.split(",");
				        int depth = 0;
				        int i = 0;
				        while (i < strs.length - 1) {
				            if (strs[i++].equals("#")) {
				                if (depth == 0) {
				                    return false;
				                } else {
				                    depth--;
				                }
				            } else {
				                depth++;
				            }
				        }
				        if (depth != 0) {
				            return false;
				        }
				        return strs[strs.length - 1].equals("#");
				    }
				}












