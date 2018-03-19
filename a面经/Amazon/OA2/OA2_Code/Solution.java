
public class Solution {
	public static void main(String[] args) {
		//CacheMiss solution1 = new CacheMiss();
		//int rvalue = solution1.Solution(new int[]{1, 2, 3, 4, 2, 1, 5, 6, 2, 1, 2, 3, 7, 6, 3, 2, 1, 2, 3, 6}, 3);
		
		//FourInteger solution2 = new FourInteger();
		//int[] rvalue = solution2.Solution(4, 3, 2, 1);
		
		//SumOfWindow solution3 = new SumOfWindow();
		//int[] rvalue = solution3.Solution(new int[] {4, 2, 73, 11, -5}, 3);
		
		//ArithmeticSlice solution4 = new ArithmeticSlice();
		//int rvalue = solution4.Solution(new int[] {-1, 1, 3, 3, 3, 2, 1, 0});
		
		/*TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		node1.left = node2;
		node1.right = node3;
		node2.right = node4;
		node3.left = node5;
		node5.right = node6;
		TreeAmplitude solution5 = new TreeAmplitude();
		int rvalue = solution5.Solution(node1);*/
		
		//RoundRobinScheduling solution6 = new RoundRobinScheduling();
		//float rvalue = solution6.Solution(new int[] {0, 1, 4, 100}, new int[] {5, 2, 3, 100}, 3);
		
		ShortestJobFirst solution7 = new ShortestJobFirst();
		float rvalue = solution7.Solution(new int[] {0, 1, 3, 9}, new int[] {2, 1, 7, 5});
		
		//DaysChange solution8 = new DaysChange();
		//int[] rvalue = solution8.Solution(new int[] {1, 0, 0, 0, 0, 1, 0, 0}, 1);
		//int[] rvalue = solution8.Solution(new int[] {1}, 1);
		
		//Rotate solution9 = new Rotate();
		//int[][] rvalue = solution9.Solution(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8},{9, 10, 11, 12}}, 2);
		//int[][] rvalue = solution9.Solution(new int[][] {{1, 2, 3}, {5, 6, 7},{9, 10, 11,}}, 2);
		
		//PathSum solution10 = new PathSum();
		//int rvalue = solution10.Solution(node1);
		
		/*ListNode head = new ListNode(1);
		ListNode cur = head;
		int[] array = new int[] {1, 7, 7};
		for (int i = 0; i < array.length; i++) {
			ListNode node = new ListNode(array[i]);
			cur.next = node;
			cur = cur.next;
		}
		cur.next = head;
		LinkedListInsert solution11 = new LinkedListInsert();
		ListNode rvalue = solution11.Solution(head, 7);
		cur = rvalue;
		for (int i = 0; i <= array.length + 1; i++) {
			System.out.print(cur.val + " ");
			cur = cur.next;
		}*/
		
		//GCD solution12 = new GCD();
		//int rvalue = solution12.Solution(new int[] {123456, 7890, 12, 24, 60});
		
		/*Maze solution13 = new Maze();
		int[][] maze = new int[][] {
			{9, 0, 0, 0, 0},
			{1, 1, 1, 1, 0},
			{0, 0, 0, 1, 0},
			{0, 1, 0, 1, 9},
			{0, 1, 0, 0, 0},
			{0, 1, 1, 1, 1},
			{0, 0, 0, 0, 9}
		};
		int rvalue = solution13.Solution(maze);*/
		System.out.println(rvalue);
	}
}
