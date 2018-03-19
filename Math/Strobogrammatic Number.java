/*
	Strobogrammatic Number

		A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

	Write a function to determine if a number is strobogrammatic. The number is represented as a string.

	For example, the numbers "69", "88", and "818" are all strobogrammatic.
	Tags: HashMap
*/

//Solution1 HashMap solution
public class Solution {
    public boolean isStrobogrammatic(String num) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        map.put('9', '6');
        map.put('6', '9');
        int start = 0;
        int end = num.length() - 1;
        while (start <= end) {
            char c1 = num.charAt(start);
            char c2 = num.charAt(end);
            if (!map.containsKey(c1) || !map.containsKey(c2)) {
                return false;
            } else if (map.get(c1) == c2) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        
        return true;
    }
}


//Solution2 my solution
public class Solution {
    public boolean isStrobogrammatic(String num) {
        if (!checkValidNum(num)) {
            return false;
        }

        int start = 0;
        int end = num.length() - 1;
        while (start <= end) {
            char left = num.charAt(start);
            char right = num.charAt(end);
            if (left != right) {
                if ((left == '6' && right == '9') || (left == '9' && right == '6')) {
                    start++;
                    end--;
                } else {
                    return false;
                }
            } else {
                if (left == '6' || left == '9') {
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }
    
    public boolean checkValidNum(String num) {
        for (char c : num.toCharArray()) {
            if (c == '2' || c == '3' || c == '4' || c == '5' || c == '7') {
                return false;
            }
        }
        return true;
    }
    

}