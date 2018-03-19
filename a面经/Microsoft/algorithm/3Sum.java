/*
	Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	Find all unique triplets in the array which gives the sum of zero.

	Note:
	Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
	The solution set must not contain duplicate triplets.

	For example, given array S = {-1 0 1 2 -1 -4},
	A solution set is:
    	(-1,  0, 1)
    	(-1, -1, 2)
*/
    	
/*
	3 Sum是two Sum的变种，可以利用two sum的二分查找法来解决问题。
 		本题比two sum增加的问题有：解决duplicate问题，3个数相加返回数值而非index。
 		1. 对数组进行排序。
 		2. 从0位置开始到倒数第三个位置（num.length-3)，进行遍历，假定num[i]就是3sum中得第一个加数，然后从i+1的位置开始，进行2sum的运算。
 		3. 当找到一个3sum==0的情况时，判断是否在结果hashset中出现过，没有则添加。(利用hashset的value唯一性）
 		4. 因为结果不唯一，此时不能停止，继续搜索，low和high指针同时挪动。
*/


/*
	Key point:
		1. Using HashSet<ArrayList<Integer>> to store the result array,cause HashSet can avoid the duplicate result;
		2. Sort the original Array in advance;
		3. Using two pointers: low and high to traverse the array
		
	time complexity: O(n2)
*/


//Solution 0 , 重点在于如何处理duplicate
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 3; i++) {
            // if (i > 0 && nums[i] == nums[i - 1]) {
            //     continue;
            // }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[left]);
                    item.add(nums[right]);
                    if (!res.contains(item)) {
                        res.add(item);
                    }
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                }
            }
            while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                i++;
            }
        }
        return res;
    }
}


//Solution1 TLE
public class Solution {
	//return all the result
	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

		//corner case
		if (num.length < 3 || num == null)
			return res;

		//avoid duplication,key point
		HashSet<ArrayList<Integer>> hs = new HashSet<ArrayList<Integer>>();
		Arrays.sort(num);

		for (int i = 0; i <= num.length - 3; i++) {
			int low = i + 1;
			int high = num.length - 1;
			while (low < high) {
				int sum = num[i] + num[low] + num[high];
				
				//matched res
				if (sum == 0) {
					ArrayList<Integer> item = new ArrayList<Integer>();
					item.add(num[i]);
					item.add(num[low]);
					item.add(num[high]);
					if (!hs.contains(item)) {
						hs.add(item);
						res.add(item);
					}
					//that's very important! dont forget!
					low++;
					high--;
				} else if (sum > 0) {
					high--;
				} else {
					low++;
				}
			}
		}
		return res;
	}
	//return only one result
	public ArrayList<Integer> threeSum(int[] num) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		//corner case
		if (num.length < 3 || num == null)
			return res;
		Arrays.sort(num);

		for (int i = 0; i <= num.length - 3; i++) {
			int low = i + 1;
			int high = num.length - 1;
			while (low < high) {
				int sum = num[i] + num[low] + num[high];
				
				//matched res
				if (sum == 0) {
					res.add(num[i]);
					res.add(num[low]);
					res.add(num[high]);
					return res;
				} else if (sum > 0) {
					high--;
				} else {
					low++;
				}
			}
		}
		return res;
	}
}

// avoid TLE
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        HashSet<List<Integer>> set = new HashSet();
        
        if (nums == null || nums.length <= 2) 
            return res;
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {// skip same result
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> list = new ArrayList();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    if (!set.contains(list)) {
                        set.add(list);
                        res.add(list);
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
                
            }
        }
        return res;
    }
}


