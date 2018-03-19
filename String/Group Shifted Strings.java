/*
	Group Shifted Strings
	Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

	"abc" -> "bcd" -> ... -> "xyz"
	Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

	For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
	Return:

	[
	  ["abc","bcd","xyz"],
	  ["az","ba"],
	  ["acef"],
	  ["a","z"]
	]
	Tags: HashTable, String
*/

/*
	题解：
	给的例子太不具说明性了。应该举这个例子：

	["eqdf", "qcpr"]。

	

	((‘c’ - 'q') + 26) % 26 = 12, ((‘p’ - 'c') + 26) % 26 = 13, ((‘r’ - 'p') + 26) % 26 = 2

	所以"eqdf"和"qcpr"是一组shifted strings。


*/

/*
	Solution1: 这道题作法实际上就是 对于每一个String，比如 eqdf, 如下算出ASCII码， 
	((‘q’ - 'e') + 26) % 26 = 12, ((‘d’ - 'q') + 26) % 26 = 13, ((‘f’ - 'd') + 26) % 26 = 2
	12 13 2， 12132  这样就是一个hashmap key

	当然编码方式有很多种，比如:
		abc , 以第一个字符为参照，全部减第一个， abc = 012 , xyz = 012
*/
/*
	The key to this problem is how to identify strings that are in the same shifting sequence. There are different ways to encode this.

	In the following code, this manner is adopted: for a string s of length n, 
	we encode its shifting feature as "s[1] - s[0], s[2] - s[1], ..., s[n - 1] - s[n - 2],".

	Then we build an unordered_map, using the above shifting feature string as key and strings that share the shifting feature as value.
	We store all the strings that share the same shifting feature in a vector. 
	Well, remember to sort the vector since the problem requires them to be in lexicographic order :-)

	A final note, since the problem statement has given that "az" and "ba" belong to the same shifting sequence. 
	So if s[i] - s[i - 1] is negative, we need to add 26 to it to make it positive and give the correct result.
	BTW, taking the suggestion of @StefanPochmann, we change the difference from numbers to lower-case alphabets using 'a' + diff.
*/
public class Solution {
	//Solution1 prefer
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strings) {
            String key = "";
            for (int i = 0; i < s.length(); i++) {
                char c = (char)((s.charAt(i) - s.charAt(0) + 26) % 26);
                key += c;
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(s);
        }
        
        for (String key : map.keySet()) {
            List<String> item = map.get(key);
            Collections.sort(item);
            res.add(item);
        }
        return res;
    }

    //Solution2
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> hm = new HashMap<>();
        
        for (String s : strings) {
            int offset = s.charAt(0) - 'a';// char 转化成int 不用（int）
            String key = "";
            for (int i = 0; i < s.length(); i++) {
                char c = (char)(s.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                key += c;
            }
            
            if (!hm.containsKey(key)) {
                hm.put(key, new ArrayList<String>());
            }
            hm.get(key).add(s);
        }
        
        for (String key : hm.keySet()) {
            List<String> list = hm.get(key);
            Collections.sort(list);
            res.add(list);
        }
        return res;
    }

    
}