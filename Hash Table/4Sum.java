/*
    Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
    Find all unique quadruplets in the array which gives the sum of target.

    Note:
    Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
    The solution set must not contain duplicate quadruplets.

    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/
    
/*
    Solution: same as 3sum,but need more pointer to travers the whole array
    Time complexity: O(n3)
*/

public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        //result array
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        //corner case
        if (num.length < 4 || num == null)
            return res;

        //avoid replicate
        HashSet<ArrayList<Integer>> hs = new HashSet<ArrayList<Integer>>();

        //sort in advance
        Arrays.sort(num);

        //notice the length of i and j;
        for (int i = 0; i <= num.length - 4; i++) {
            for (int j = i + 1; j <= num.length - 3; j++) {
                int low = j + 1;
                int high = num.length - 1;
                while (low < high) {
                    int sum = num[i] + num[j] + num[low] + num[high];
                    if (sum == target) {
                        ArrayList<Integer> item = new ArrayList<Integer>();
                        item.add(num[i]);
                        item.add(num[j]);
                        item.add(num[low]);
                        item.add(num[high]);
                        if (!hs.contains(item)) {
                            hs.add(item);
                            res.add(item);
                        }
                        low++;
                        high--;
                    } else if (sum > target) {
                        high--;
                    } else {
                        low++;
                    }
                }
            }
        }
        return res;
    }