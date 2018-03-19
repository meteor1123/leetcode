/*
	Group Anagrams
	Given an array of strings, group anagrams together.

	For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
	Return:

	[
	  ["ate", "eat","tea"],
	  ["nat","tan"],
	  ["bat"]
	]
	Note:
	For the return value, each inner list's elements must follow the lexicographic order.
	All inputs will be in lower-case.
	Update (2015-08-09):
	The signature of the function had been updated to return list<list<string>> instead of list<string>, as suggested here. If you still see your function signature return a list<string>, please click the reload button  to reset your code definition.
*/

//Solution1
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) {
            return res;
        }
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String sortS = new String(arr);
            //String sortS = convert(s); follow up 做法
            if (!map.containsKey(sortS)) {
                map.put(sortS, new ArrayList<>());
            } 
            map.get(sortS).add(s);
        }
        
        for (String s : map.keySet()) {
            List<String> item = new ArrayList<>();
            item = map.get(s);
            Collections.sort(item);
            res.add(item);
        }
        return res;
    }
}

//Follow -up 1
/*
    如果不用sort之后的str作为key，如何解这题？
    可以把每一个string统计一下a,b,c,....的频率，然后输出一个pattern a1b3c4, 把这个pattern作为map的key。
 */
    public String convert(String word) {
        char[] array = word.toCharArray();
        int[] res = new int[26];
        for(char c : array) {
            res[c - 'a'] ++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < res.length; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }


//Follow up 2
public class Solution {
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> map = new HashMap<>();
 
        for (String s : strs) {
            String sortedS = makeHashString(s);
            if (!map.containsKey(sortedS)) {
                map.put(sortedS,new ArrayList<String>());
            }
            map.get(sortedS).add(s);
        }
        
        for (List<String> item : map.values()) {
            Collections.sort(item);
            res.add(item);
        }
        return res;
    }
    

    public static String makeHashString(String s) {
        int[] charset = new int[26];

        for (char c : s.toCharArray()) {
            charset[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) { //O(26*b)
            if (charset[i] != 0) {
                for (int j = 0; j < charset[i]; j++) {
                    char c = (char) (i + 'a');
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}