/*
	Given an array of integers, find two numbers such that they add up to a specific target number.
	The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
	Please note that your returned answers (both index1 and index2) are not zero-based.
	You may assume that each input would have exactly one solution.

	Input: numbers={2, 7, 11, 15}, target=9
	Output: index1=1, index2=2
*/

/*
 	利用HashMap，把target-numbers[i]的值放入hashmap中，value存index。	
 	遍历数组时，检查hashmap中是否已经存能和自己加一起等于target的值存在，存在的话把index取出，连同自己的index也出去，
 	加1（index要求从1开始）后存入结果数组中返回。如果不存在的话，把自己的值和index存入hashmap中继续遍历。
 	由于只是一遍遍历数组，时间复杂度为O(n)。
*/

/*
	Using HashMap to store the numbers[i] and position,if find target - numbers[i] in map,mean find successfull. 

	time complicity: O(n)
*/

public class Solution {
	public int[] twoSum(int[] numbers, int target) {
		int[] res = new int[2];
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			//并不是判断number[i]是否存在hashmap，而是巧妙判断target-number[i]是否存在
			if (!hm.containsKey(target - numbers[i])) {
				hm.put(numbers[i], i);
			} else {
				res[0] = hm.get(target - numbers[i]) + 1;
				res[1] = i + 1;
				break;
			}
		}
		return res;
	}

	//twoSum 
	//sorted array
	public int[] twoSum(int[] numbers, int target) {
	    int[] res = new int[2];
	    if(numbers==null || numbers.length<2)
	        return null;
	    int left = 0;
	    int right = numbers.length-1;
	    while(left < right)
	    {
	        if(numbers[left] + numbers[right] == target)
	        {
	            res[0] = number[left];
	            res[1] = number[right];
	            return res;
	        }
	        else if(numbers[left] + numbers[right] > target)
	        {
	            right--;
	        }
	        else
	        {
	            left++;
	        }
	    }
	    return null;
	}
	//brute force unsorted
	public int[] twoSum(int[] numbers, int target) {
		int[] res = new int[2];
		if (number == null || numbers.length < 2) {
			return null;
		}
		for (int i = 0; i < numbers.length - 1; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] + numbers[j] == target) {
					res[0] = i + 1;
					res[1] = j + 1;
					return res;
				} 
			}
		}
		res[0] = -1;
		res[1] = -1;
		return res;
	}
}