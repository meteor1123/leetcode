/*
	Trapping Rain Water
	Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


	For example, 
	Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

	The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
    In this case, 6 units of rain water (blue section) are being trapped.
	Tags: Array, Stack,  TwoPointers
	Time Complexity : O(2n), space:O(n)
*/


public class Solution {
	/*	Solution1:

		这种方法是基于动态规划的，基本思路就是维护一个长度为n的数组，进行两次扫描，一次从左往右，一次从右往左。
		第一次扫描的时候维护对于每一个bar左边最大的高度是多少，存入数组对应元素中，
        第二次扫描的时候维护右边最大的高度，
		并且比较将左边和右边小的最大高度（我们成为瓶颈）存入数组对应元素中。这样两遍扫描之后就可以得到每一个bar能承受的最大水量，
		从而累加得出结果。这个方法只需要两次扫描，所以时间复杂度是O(2*n)=O(n)。空间上需要一个长度为n的数组，复杂度是O(n)
	*/
	//By Code Ganker
    public int trap(List<Integer> height) {
        if (height == null || height.size() == 0) {
            return 0;
        }
        //use container to store the highest value from left and right
        int[] container = new int[height.size()];
        int max = 0;
        int res = 0;
        //container[i] equals the max left height value of height[i]
        for (int i = 0; i < height.size(); i++) {
            container[i] = max;
            max = Math.max(max, height.get(i));
        }
        max = 0;
        //we traverse the array start from right to left, get the max right height value of height[i]
        //and compare to the max left height value, chose the minimum max height,
        //finally if the container[i] minus the height[i] large than 0, means can add to the water volumn, otherwise just add 0
        for (int i = height.size() - 1; i >= 0; i--) {
            container[i] = Math.min(max, container[i]);
            max = Math.max(max, height.get(i));
            res += container[i] - height.get(i) > 0 ? container[i] - height.get(i): 0;
        }
        return res;
    }


    /*
    	Solution2:
    	首先，碰到这样的题目不要慌张，挨个分析每个A[i]能trapped water的容量，然后将所有的A[i]的trapped water容量相加即可
		其次，对于每个A[i]能trapped water的容量，取决于A[i]左右两边的高度（可延展）较小值与A[i]的差值，
		即volume[i] = [min(left[i], right[i]) - A[i]] * 1，这里的1是宽度，如果the width of each bar is 2,那就要乘以2了”
 		那么如何求A[i]的左右高度呢？ 要知道，能盛多少水主要看短板。那么对每个A[i]来说，要求一个最高的左短板，再求一个最高的右短板，
 		这两个直接最短的板子减去A[i]原有的值就是能成多少水了。
		所以需要两遍遍历，一个从左到右，找最高的左短板；一个从右到左，找最高的右短板。最后记录下盛水量的总值就是最终结果了。

			index:  0  1  2  3  4  5  6  7  8  9  10 11

   		 A[index]:  0  1  0  2  1  0  1  3  2  1  2  1

 	  left[index]:  0  1  1  2  2  2  2  3  3  3  3  3

	 right[index]:  3  3  3  3  3  3  3  3  2  2  2  1

       	   min[i]:  0  1  1  2  2  2  2  3  2  2  2  1    min[i] = min (left[i] and right[i])

           bit[i]:  -  0  1  0  1  2  1  0  0  1  0  0    bit[i] = min[i] - A[index] ,if > 0 plus,if < 0 do not plus
    */

    //Solution3:
    /*
    	基本思路是这样的，用两个指针从两端往中间扫，在当前窗口下，如果哪一侧的高度是小的，那么从这里开始继续扫，
    	如果比它还小的，肯定装水的瓶颈就是它了，可以把装水量加入结果，如果遇到比它大的，立即停止，重新判断左右窗口的大小情况，重复上面的步骤。
    	这里能作为停下来判断的窗口，说明肯定比前面的大了，所以目前肯定装不了水（不然前面会直接扫过去）。
    	这样当左右窗口相遇时，就可以结束了，因为每个元素的装水量都已经记录过了
    */
    public int trap(List<Integer> height) {
        if (height == null || height.size() == 0) {
            return 0;
        }
        int left = 0;
        int right = height.size() - 1;
        int res = 0;
        while (left < right) {
            int min = Math.min(height.get(left), height.get(right));
            if (height.get(left) == min) {
                left++;
                while (left < right && height.get(left) < min) {
                    res += min - height.get(left);
                    left++;
                }
            } else {
                right--;
                while (left < right && height.get(right) < min) {
                    res += min - height.get(right);
                    right--;
                }
            }
        }
        return res;
    }
}

/*
    1. 两个指针从两端往中间扫， 在当前窗口中，如果哪一侧高度小，则从该侧开始扫描
    2. 如果在扫描的过程中发现有比它还小的，就可以把leftHeight - curHeight 加入结果，为什么呢？因为另外一边的高度肯定大于左边开始的高度，
       所以这个装水量一定会满足
    3. 当左右窗口相遇结束
*/
public class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        
        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            if (height[left] == minHeight) {
                left++;
                while (left < right && height[left] < minHeight) {
                    res += minHeight - height[left];
                    left++;
                }
            } else {
                right--;
                while (left < right && height[right] < minHeight) {
                    res += minHeight - height[right];
                    right--;
                }
            }
        }
        return res;
    }
}