package LeetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * Given n = 2, prerequisites = [[1,0]]
 * Return [0,1]
 * Given n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
 * Return [0,1,2,3] or [0,2,1,3]
 */
public class CourseScheduleII {
	public int[] schedule(int n, int[][] prerequisite) {
		Set<Integer> courseSet = new HashSet<Integer>();
		int first = 0;
		for (int i = 0; i < n; i++) {
			courseSet.add(prerequisite[i][1]);	
		}
		for (int i = 0; i < n; i++) {
			if (!courseSet.contains(prerequisite[i][1])) {
				first = prerequisite[i][1];
			}
		}
		Queue<Element> q = new LinkedList<Element>();
		q.offer(arg0)
	}
	
	class Element {
		int cur;
		int pre;
		Element(int cur, int pre) {
			this.cur = cur;
			this.pre = pre;
		}
	}
}
