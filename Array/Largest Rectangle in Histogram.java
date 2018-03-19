/*
	Given n non-negative integers representing the histogram's bar height 
	where the width of each bar is 1, 
	find the area of largest rectangle in the histogram.

	Above is a histogram where width of each bar is 1, 
	given height = [2,1,5,6,2,3].

	The largest rectangle is shown in the shaded area, 
	which has area = 10 unit.

	For example,
	Given height = [2,1,5,6,2,3],
	return 10.
*/

/*
    Best Solution:
        http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
    
    Step1: Using a Stack to store the index of array height;
    Step2: Copy the height array, and made the length equals height.length + 1;
    Step3: Using a while loop iterate the array
    Step4: if stack is empty or h[stack.peek()] smaller than the h[i],push the index i to array
    Step5: else pop the peek of stack, and continue while loop check the next number
           so the h[top] is the smallest bottle neck height, and just mutiply the width i or i - stack.peek() - 1;
*/


//Solution1
public class Solution {
    public int largestRectangleArea(int[] height) {
        //the stack only store the index of height,not the value
    	Stack<Integer> stack = new Stack<Integer>();
    	int i = 0 ;
    	int res = 0;
        //We need to copy the array[] height,and generate a new array h[],the length is large more than 1 
    	int[] h = new int[height.length + 1];//h[height.length]存放的是人为假尾结点0，使其可以终止
    	h = Arrays.copyOf(height, height.length + 1);
    	// 假如是递增序列，则持续进栈，一遇到小于栈顶元素的数就一直出栈到栈顶元素小于等于 h[i],然后持续递增进栈，或者递减出栈重新算
        //when the stack is empty or the top element is not bigger than h[i] ,push to the stack
        //which means the element store in the stack with a ascending sequence.
    	while (i < h.length) {
    		if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
    			stack.push(i);
                i++;
    		} else {

    			int top = stack.pop();//一遇到小的数就栈顶元素出栈
    			res = Math.max(res, h[top] * (stack.isEmpty() ? i: i - stack.peek() - 1)); //peek()是已经出栈之后的前一个数的position
                // top is the largest number's position , 
                // h[top] is the largest number's value;
                // h[top] * i ,only when the stack is empty
                // along with the continuly pop(), i - stack.peek() - 1 will be increase(this is the width)

                //假如栈顶元素非空，则宽度等于 i - stack.peek() - 1
    		}
    	}
    	return res;
    }
}

//Solution2
public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0;
        int res = 0;
        int len = heights.length;
        int[] h = new int[len + 1];
        h = Arrays.copyOf(heights, len + 1);
        
        while (i < h.length) {
            if (stack.isEmpty() || h[stack.peek()] <= h[i]) {
                stack.push(i);
                i++;
            } else {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    res = Math.max(res, h[top] * i);
                } else {
                    res = Math.max(res, h[top] * (i - stack.peek() - 1));
                }
            }
        }
        return res;
    }
}


//Solution3: prefer
public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack < Integer > stack = new Stack < > ();
        stack.push(-1);
        int res = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                res = Math.max(res, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        // if we reach the end of the array, we pop all the elements of the stack and at every pop
        while (stack.peek() != -1)
            res = Math.max(res, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return res;
    }
}
