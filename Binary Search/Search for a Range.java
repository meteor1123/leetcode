/*
	Search for a Range 
	Given a sorted array of integers, find the starting and ending position of a given target value.
	Your algorithm's runtime complexity must be in the order of O(log n).

	If the target is not found in the array, return [-1, -1].

	For example,
	Given [5, 7, 7, 8, 8, 10] and target value 8,
	return [3, 4].
	Tags: Array, Binary Search
*/


/*
	二分查找方法
       二分查找经常用来在有序的数列查找某个特定的位置。因此，应用二分查找法，这个数列必须包含以下特征：
	1.存储在数组中
	2.有序排列
     同时这道题让我们确定 starting position和ending position，这就让我们联想到之前做过的Search Insert Position一题，
     
    3.当无法查找到given target时，利用非递归二分查找法所得的最终low和high指针，将会指向该无法查找到的元素的左右两个元素。
     说不清楚看例子，
     	例如，给定arraylist [1,2,4,5] target为3，那么通过传统非递归二分查找法，
     		low指针将会指向4（位置为2），
     		high指针指向2（位置为1）。
     	利用这种规律，我们就能够找到target元素的左右边界。所以本题的解题思路为：
第一步，在给定数组中找到该target，记录该位置。这时我们并不关心这个target是边界还是中间值，我们只需确定，在数组中是能够找到这样一个target值。
	   如果找不到返回{-1，-1}。为了保证时间复杂度是O(logn), 这里自然而然使用传统二分查找法实现。

第二步，确定该target的右边界。此时我们将对数组从刚才确定的那个target的pos作为起始点，到数组结束，来确定右边界。
	   同样是使用二分查找法，当新的mid值仍然等于target值时，我们能确定该mid左半边（到pos）都是等于target，继续在右半边查找。
	   如果新的mid值不等于target值，我们就知道右边界一定在新mid值的左半边，继续查找。最后新的high指针指向的就是右边界的位置。

第三步，确定该target的左边界。这一步与第二步对称操作，最后新的low指针指向的就是左边界的位置。

最后，返回结果数组。
*/
public int[] searchRange(int[] A, int target) {
	int[] res = {-1, -1};
	if (A == null || A.length == 0)
		return res;

	int low = 0;
	int high = A.length - 1;
	int pos = 0;

	//first iteration, find target wherever it is
	while (low <= high) {
		int mid = (low + high) / 2;
		pos = mid;
		if (A[mid] > target) 
			high = mid - 1;
		else if (A[mid] < target)
			low = mid + 1;
		else {
			res[0] = pos;
			res[1] = pos;
			break;
		}
	}

	if (A[pos] != target)
		return res;

	//second iteration, find the right boundary of this target
	int newlow = pos;
	int newhigh = A.length - 1;
	while (newlow <= newhigh) {
		int newmid = (newlow + newhigh) / 2;
		//如果newmid 依然等于target ，能确定newmid的左边都等于target
		if (A[newmid] == target)
			newlow = newmid + 1;
		else 
			newhigh = newmid - 1;
	}
	res[1] = newhigh;

	//third iteration, find the left boundary of this target
	newlow = 0;
	newhigh = pos;
	while (newlow <= newhigh) {
		int newmid = (newlow + newhigh) / 2;
		if (A[newmid] == target)
			newhigh = newmid - 1;
		else 
			newlow = newmid + 1;
	}
	res[0] = newlow;
	return res;
}



//solution2 chapter 9 prefer!
/*
    为什么我们用 start + 1 < end 而不用 start < end 或者 start <= end?
    因为可以看到 我们需要找到target值出现的左右边界，如果start + 1 < end 跳出，则一定有 start + 1 > end ==> start > end - 1 ,--> start >= end
*/

public int[] searchRange(int[] A, int target) {
         // write your code here
        int start, end, mid;
        int[] bound = new int[2];
        
        //search for left bound
        start = 0;
        end = A.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            //if the A[mid] == target, we move the bound to left, so let end = mid;
            if (A[mid] == target) {
                end = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        //check the start position and end position to set the left bound
        if (A[start] == target) {
            bound[0] = start;
        } else if (A[end] == target) {
            bound[0] = end;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }
        
        //search for right bound
        start = 0;
        end = A.length - 1;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            //if the A[mid] == target, we move the bound to right, so let start = mid;
            if (A[mid] == target) {
                start = mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        //check the start position and end position to set the right bound
        if (A[end] == target) {
            bound[1] = end;
        } else if (A[start] == target) {
            bound[1] = start;
        } else {
            bound[0] = bound[1] = -1;
            return bound;
        }
        return bound;
    }
}