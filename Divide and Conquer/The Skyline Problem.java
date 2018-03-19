/*	
	The Skyline Problem
	https://leetcode.com/problems/the-skyline-problem/
	A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
*/
/*
	http://www.cnblogs.com/TinyBobo/p/4592061.html
	题意：记录每一次建筑的拐点，连续的相同高度，只记录最前面的。

思路：将每一个竖线按顺序存入，对于每一个矩形，都有高度相同的两条竖线，为了在一次扫描的时候，能将所有的竖线都加入，
利用一个小技巧，将右边界的高度存为负值，恰好可以对其标记。将存入的list排序，需要自定义一个比较器比较a[],b[]，
如果a[0] == b[0] 说明竖线重合，这个时候应该是高的在前面，如果a[0] != b[0] 只需按照a[0]b[0]从小到大即可。

最后在遍历list的时候，为了满足note4，即连续相同的高度只记录最前即可，因此需要用到pre和cur分别记录当前的最高和上一次的最高。
在判断的时候，需要维护每次当前的最大值，可以用PriorityQueue权重队列，其构造原理是大顶堆，即根节点为最小(或最大)的二叉树，
默认跟为最小值，根据题意，本题需要自己定义一个比较器，根保存当前最大值
(可以注意我下面说的堆个队里，是同一个概念，为了方便理解，不同的时候用了不同的说法)。

最核心的思想：
	扫描到左边界的时候，将高度加入到大顶堆，cur的值去peek即为当前的最大值，当cur和pre不同的时候，
	将坐标加入结果队列即可，当为右边界时（高度为负值），证明该矩形已经到头，在堆中去掉其高度值，如果此时队列为空，
	则证明此处非连续，即此时应加入的高度为0，如果不为空，则更新相应当前最高即可。
*/
public class Solution {
	//maxheap 顶堆比较器
    public class MaxCom implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return b - a; //大的在堆顶端
        }
    }
    
    //array comparator
    public class ArrayCom implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            if (a[0] != b[0]) {
                return a[0] - b[0];// sort by the left side
            }
            return b[1] - a[1];//if equal, higher in the front
        }
    }
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<int[]>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new MaxCom());
        List<int[]> ver = new ArrayList<int[]>();
        for (int i = 0; i < buildings.length; i++) {
            int[] temp = buildings[i];
            ver.add(new int[]{temp[0], temp[2]});// left side
            ver.add(new int[]{temp[1], -temp[2]});//right side 为了区分 存入负值
        }
        Collections.sort(ver, new ArrayCom());
        int cur = 0, pre = 0;
        for (int i = 0; i < ver.size(); i++) {
            int[] temp = ver.get(i);
            if (temp[1] > 0) { //left side
                maxHeap.offer(temp[1]); //高度入队
                cur = maxHeap.peek();// 当前最高的
            } else { // 右边界
                maxHeap.remove(-temp[1]);// 将对应的高度从堆中删除 这里就是右边存负值的方便之处
                cur = (maxHeap.peek() == null ? 0 : maxHeap.peek());// 如果右边界是最后一个则高度为0，否则更新当前最高
            }
            if (cur != pre) {// 与上一个最高的不相等
                res.add(new int[]{temp[0], cur});
                pre = cur;// 保存当前高度为下一次的前面高度
            }
        }
        return res;
    }
}