/*
	HashTable
*/


0. Basic HashTable Operation
		0.0 Simple HashTable
			public class HashEntry {
			      private int key;
			      private int value;
			 
			      HashEntry(int key, int value) {
			            this.key = key;
			            this.value = value;
			      }      
			 
			      public int getKey() {
			            return key;
			      }
			 
			      public int getValue() {
			            return value;
			      }
			}
			public class HashMap {
			      private final static int TABLE_SIZE = 128;
			 
			      private HashEntry[] table;
			 
			      public HashMap() {
			            table = new HashEntry[TABLE_SIZE];
			            for (int i = 0; i < TABLE_SIZE; i++) {
			                  table[i] = null;
			            }
			      }
			 
			      public int get(int key) {
			            int hash = (key % TABLE_SIZE);
			            while (table[hash] != null && table[hash].getKey() != key) {
			                  hash = (hash + 1) % TABLE_SIZE;
			            }
			            if (table[hash] == null) {
			                  return -1;
			            } else {
			                  return table[hash].getValue();
			            }
			      }
			 
			      public void put(int key, int value) {
			            int hash = (key % TABLE_SIZE);
			            while (table[hash] != null && table[hash].getKey() != key) {
			                  hash = (hash + 1) % TABLE_SIZE;
			            }
			            table[hash] = new HashEntry(key, value);
			      }
			}
		0.1 Rehashing
			/*
				The size of the hash table is not determinate at the very beginning. If the total size of keys is too large (e.g. size >= capacity / 10), we should double the size of the hash table and rehash every keys. Say you have a hash table looks like below:

				size=3, capacity=4

				[null, 21, 14, null]
				       ↓    ↓
				       9   null
				       ↓
				      null
				The hash function is:

				int hashcode(int key, int capacity) {
				    return key % capacity;
				}
				here we have three numbers, 9, 14 and 21, where 21 and 9 share the same position as they all have the same hashcode 1 (21 % 4 = 9 % 4 = 1). We store them in the hash table by linked list.

				rehashing this hash table, double the capacity, you will get:

				size=3, capacity=8

				index:   0    1    2    3     4    5    6   7
				hash : [null, 9, null, null, null, 21, 14, null]
				Given the original hash table, return the new hash table after rehashing .

				Have you met this question in a real interview? Yes
				Example
				Given [null, 21->9->null, 14->null, null],

				return [null, 9->null, null, null, null, 21->null, 14->null, null]

				Note
				For negative integer in hash table, the position can be calculated as follow:

				C++/Java: if you directly calculate -4 % 3 you will get -1. You can use function: a % b = (a % b + b) % b to make it is a non negative integer.
				Python: you can directly use -1 % 3, you will get 2 automatically.
			 */
				public class Solution {
				    /**
				     * @param hashTable: A list of The first node of linked list
				     * @return: A list of The first node of linked list which have twice size
				     */    
				    public ListNode[] rehashing(ListNode[] hashTable) {
				        // write your code here
				        int oldSize = hashTable.length;
				        int newSize = hashTable.length * 2;
				        if (hashTable == null || oldSize == 0) {
				            return null;
				        }
				        ListNode[] res = new ListNode[newSize];
				        for (int i = 0; i < oldSize; i++) {
				            if (hashTable[i] != null) {
				                rehash(hashTable, res, i);
				            }
				        }
				        return res;
				    }
				    
				    public void rehash(ListNode[] hashTable, ListNode[] res, int i) {
				        int newSize = res.length;
				        ListNode cur = hashTable[i];
				        while (cur != null) {
				            int val = cur.val;
				            int newPos = val >= 0 ? val % newSize : (val % newSize + newSize) % newSize;
				            if (res[newPos] == null) {
				                res[newPos] = new ListNode(val);
				            } else {
				                ListNode temp = res[newPos];
				                while (temp.next != null) {
				                    temp = temp.next;
				                }
				                temp.next = new ListNode(val);
				            }
				            cur = cur.next;
				        }
				    }
				};
		0.2 Hash Function
			/*
				n data structure Hash, hash function is used to convert a string(or any other type) into an integer smaller than hash size and bigger or equal to zero. The objective of designing a hash function is to "hash" the key as unreasonable as possible. A good hash function can avoid collision as less as possible. A widely used hash function algorithm is using a magic number 33, consider any string as a 33 based big integer like follow:

				hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE 

				                              = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE

				                              = 3595978 % HASH_SIZE

				here HASH_SIZE is the capacity of the hash table (you can assume a hash table is like an array with index 0 ~ HASH_SIZE-1).

				Given a string as a key and the size of hash table, return the hash value of this key.f

				Have you met this question in a real interview? Yes
				Example
				For key="abcd" and size=100, return 78
				Clarification
				For this problem, you are not necessary to design your own hash algorithm or consider any collision issue, you just need to implement the algorithm as described.
			 */
			/*
				(a + b) % p = (a % p + b % p) % p （1） 
				(a - b) % p = (a % p - b % p) % p （2） 
				(a b) % p = (a % p b % p) % p （3） 
				 a ^ b % p = ((a % p)^b) % p （4）

				应该还有一个（3）的变式 (a b) % p = (a % p b) % p （3）
			 */
			public class Solution {
			     public int hashCode(char[] key,int HASH_SIZE) {
			         // write your code here
			         long res = 0;
			         for (int i = 0; i < key.length; i++) {
			             res = 33 * res + key[i];
			             res %= HASH_SIZE;
			         }
			         return (int)res;
			     }
			};


1. String And Word Problem
		1.1 Palindrome Permutation
			/*
				Given a string, determine if a permutation of the string could form a palindrome.
				For example,
				"code" -> False, "aab" -> True, "carerac" -> True.
			*/
				public class Solution {
				    public boolean canPermutePalindrome(String s) {
				        int[] letter = new int[256];
				        int count = 0;
				        for (char c : s.toCharArray()) {
				            if (letter[c] == 0) {
				                letter[c]++;
				            } else {
				                letter[c]--;
				            }
				        }
				        for (int i : letter) {
				            count += i;
				        }
				        return count > 1 ? false : true;
				    }
				}
		1.2 Valid Anagram
			/*
				For example,
					s = "anagram", t = "nagaram", return true.
					s = "rat", t = "car", return false.

				Note:
					You may assume the string contains only lowercase alphabets.
			*/
				public class Solution {
				    public boolean isAnagram(String s, String t) {
				        if (s.length() != t.length()) {
				            return false;
				        }
				        int[] arr = new int[26];
				        for (int i = 0; i < s.length(); i++) {
				            arr[s.charAt(i) - 'a']++;
				            arr[t.charAt(i) - 'a']--;
				        }
				        for (int value : arr) {
				            if (value != 0) {
				                return false;
				            }
				        }
				        return true;
				    }
				}

		1.2 Group Shifted Strings
			/*
				For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
				Return:

				[
				  ["abc","bcd","xyz"],
				  ["az","ba"],
				  ["acef"],
				  ["a","z"]
				]
			*/
			// (s.charAt(i) - s.charAt(0) + 26) % 26
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
			        for (List<String> item : map.values()) {
			            Collections.sort(item);
			            res.add(item);
			        }
			        return res;
			    }
		1.3 Shortest Word Distance II
			/*
				For example,
					Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

					Given word1 = “coding”, word2 = “practice”, return 3.
					Given word1 = "makes", word2 = "coding", return 1.
				Note:
					You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
					Call mutiple time
			*/
			public class WordDistance {
			    private HashMap<String, ArrayList<Integer>> map;
			    public WordDistance(String[] words) {
			        map = new HashMap<String, ArrayList<Integer>>();
			        for (int i = 0; i < words.length; i++) {
			            if (!map.containsKey(words[i])) {
			                map.put(words[i], new ArrayList<>());
			            }
			            map.get(words[i]).add(i);
			        }
			    }
			    public int shortest(String word1, String word2) {
			        List<Integer> list1 = map.get(word1);
			        List<Integer> list2 = map.get(word2);
			        int minLen = Integer.MAX_VALUE;
			        int i = 0;
			        int j = 0;
			        while (i < list1.size() && j < list2.size()) {
			            minLen = Math.min(minLen, Math.abs(list1.get(i) - list2.get(j)));
			            if (list1.get(i) > list2.get(j)) {
			                j++;
			            } else {
			                i++;
			            }
			        }
			        return minLen;
			    }
			}
		1.4 Isomorphic Strings
			/*
				Isomorphic Strings 
				Given two strings s and t, determine if they are isomorphic.

				For example,
					Given "egg", "add", return true.
					Given "foo", "bar", return false.
					Given "paper", "title", return true.
			*/
			/*
				主要的edge case 就是：ab, aa
			*/
			//Solution1
			public boolean isIsomorphic(String s, String t) {
		        int[] map1 = new int[256];
		        int[] map2 = new int[256];
		        for (int i = 0; i < s.length(); i++) {
		            if (map1[s.charAt(i)] != map2[t.charAt(i)]) {
		                return false;
		            } else {
		                map1[s.charAt(i)] = map2[t.charAt(i)] = i + 1;
		            }
		        }
		        return true;
			}
			//Solution2
			public class Solution {
			    public boolean isIsomorphic(String s, String t) {
			        int len = s.length();
			        HashMap<Character, Character> hm = new HashMap<Character, Character>();
			        HashSet<Character> used = new HashSet<Character>();
			        for (int i = 0; i < len; i++) {
			            char sChar = s.charAt(i);
			            char tChar = t.charAt(i);
			            if (!hm.containsKey(sChar)) {
			                if (used.contains(tChar)) {
			                    return false;
			                }
			                hm.put(sChar, tChar);
			                used.add(tChar);
			            } else {
			                if (hm.get(sChar) != tChar) {
			                    return false;
			                }
			            }
			        }
			        return true;
			    }
			}
		1.5 Word Pattern
			/*
				Given a pattern and a string str, find if str follows the same pattern.

				Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

				Examples:
				pattern = "abba", str = "dog cat cat dog" should return true.
				pattern = "abba", str = "dog cat cat fish" should return false.
				pattern = "aaaa", str = "dog cat cat dog" should return false.
				pattern = "abba", str = "dog dog dog dog" should return false.
				Notes:
				You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
			*/
			//Solution1 Same as above
			//Solution2
			public boolean wordPattern(String pattern, String str) {
		        String[] words = str.split(" ");
		        if (words.length != pattern.length())
		            return false;
		        Map index = new HashMap();
		        for (Integer i = 0; i < words.length; ++i)
		            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))//如果之前有值就返回之前的value的值
		                return false;
		        return true;
		    }
	    1.6 Bulls And Cows
		    /*
		    	Secret number:  "1807"
				Friend's guess: "7810": "1A3B"

				Secret number:  "1123"
				Friend's guess: "0111": "1A1B"
		    */
			public class Solution {
			    public String getHint(String secret, String guess) {
			        int bulls = 0;
			        int cows = 0;
			        int[] numbers = new int[10];
			        for (int i = 0; i < secret.length(); i++) {
			            if (secret.charAt(i) == guess.charAt(i)) {
			                bulls++;
			            } else {
			                if (numbers[secret.charAt(i) - '0']++ < 0) {
			                    cows++;
			                } 
			                if (numbers[guess.charAt(i) - '0']-- > 0) {
			                    cows++;
			                }
			            }
			        }
			        return bulls + "A" + cows + "B";
			    }
			}
		1.7 Repeated DNA Sequences
			/*
				All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, 
				it is sometimes useful to identify repeated sequences within the DNA.
				Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

				For example,
					Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

				Return:
					["AAAAACCCCC", "CCCCCAAAAA"].
			*/
			public class Solution {
			    public List<String> findRepeatedDnaSequences(String s) {
			        List<String> res = new ArrayList<>();
			        if (s == null || s.length() <= 10) {
			            return res;
			        }
			        HashMap<Character, Integer> mapping = new HashMap<>();
			        mapping.put('A', 0);
			        mapping.put('C', 1);
			        mapping.put('G', 2);
			        mapping.put('T', 3);
			        //key是10位数字通过左移+组成的hash值， value是出现的次数
			        HashMap<Integer, Integer> map = new HashMap<>();
			        int hash = 0;
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (i < 9) {
			                hash = (hash << 2) + mapping.get(c);
			            } else {
			                hash = (hash << 2) + mapping.get(c);
			                hash &= (1 << 20) - 1;//关键点，20位的掩码，取20位hash值
			                if (map.containsKey(hash) && map.get(hash) == 1) {
			                    res.add(s.substring(i - 9, i + 1));
			                    map.put(hash, map.get(hash) + 1);
			                } else if (!map.containsKey(hash)) {
			                    map.put(hash, 1);
			                } 
			            }
			        }
			        return res;
			    }
			}
		1.8 Unique Word Abbreviation
			/*
				An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
				a) it                      --> it    (no abbreviation)

				     1
				b) d|o|g                   --> d1g

				              1    1  1
				     1---5----0----5--8
				c) i|nternationalizatio|n  --> i18n

				              1
				     1---5----0
				d) l|ocalizatio|n          --> l10n
				Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

				Example: 
				Given dictionary = [ "deer", "door", "cake", "card" ]

				isUnique("dear") -> false
				isUnique("cart") -> true
				isUnique("cane") -> false
				isUnique("make") -> true
			*/
			public class ValidWordAbbr {
			    HashMap<String, String> map;
			    public ValidWordAbbr(String[] dictionary) {
			        map = new HashMap<>();
			        for (String word : dictionary) {
			            String abbr = generateAbb(word);
			            if (!map.containsKey(abbr)) {
			                map.put(abbr, word);
			            } else {
			                if (!map.get(abbr).equals(word)) {
			                    map.put(abbr, "-1");
			                }
			            }
			        }
			    } 

			    public boolean isUnique(String word) {
			        String key = generateAbb(word);
			        return !map.containsKey(key) || map.get(key).equals(word);
			    }
			    
			    public String generateAbb(String word) {
			        if (word.length() <= 2) {
			            return word;
			        }
			        String abb = word.charAt(0) + String.valueOf(word.length() - 2) + word.charAt(word.length() - 1);
			        return abb;
			    }
			}
		1.9 Word Pattern I
				/*
					Word Pattern
					Given a pattern and a string str, find if str follows the same pattern.

					Examples:
					pattern = "abba", str = "dog cat cat dog" should return true.
					pattern = "abba", str = "dog cat cat fish" should return false.
					pattern = "aaaa", str = "dog cat cat dog" should return false.
					pattern = "abba", str = "dog dog dog dog" should return false.
					Notes:
					patterncontains only lowercase alphabetical letters, and str contains words separated by a single space. Each word in str contains only lowercase alphabetical letters.
					Both pattern and str do not have leading or trailing spaces.
					Each letter in pattern must map to a word with length that is at least 1
				*/

				public class Solution {
				    //Solution2 prefer
				    public boolean wordPattern(String pattern, String str) {
				        String[] words = str.split(" ");
				        if (pattern.length() != words.length) {
				            return false;
				        }
				        HashMap<Character, String> map = new HashMap<>();
				        for (int i = 0;i < pattern.length(); i++) {
				            char c = pattern.charAt(i);
				            String word = words[i];
				            if (!map.containsKey(c)) {
				                if (map.containsValue(word)) {
				                    return false;
				                }
				                map.put(c, word); 
				            } else {
				                if (!map.get(c).equals(word)) {
				                    return false;
				                }
				            } 
				        }
				        return true;
				    }
				}
		1.10 Word Pattern II
				/*
					Given a pattern and a string str, find if str follows the same pattern.

					Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

					Examples:
					pattern = "abab", str = "redblueredblue" should return true.
					pattern = "aaaa", str = "asdasdasdasd" should return true.
					pattern = "aabb", str = "xyzabcxzyabc" should return false.
					Notes:
					You may assume both pattern and str contains only lowercase letters.
				*/
				public class Solution {
				    public boolean wordPatternMatch(String pattern, String str) {
				        Map<Character, String> map = new HashMap();
				        Set<String> set = new HashSet<>();
				        return isMatch(str, 0, pattern, 0, map, set);
				    }
				    public boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
				        if (i == str.length() && j == pat.length()) {
				            return true;
				        }
				        if (i == str.length() || j == pat.length()) {
				            return false;
				        }
				        char c = pat.charAt(j);
				        if (map.containsKey(c)) {
				            String s = map.get(c);
				            if (!str.startsWith(s, i)) {
				                return false;
				            }
				            return isMatch(str, i + s.length(), pat, j + 1, map, set);
				        }
				        for (int k = i; k < str.length(); k++) {
				            String subStr = str.substring(i, k + 1);
				            if (set.contains(subStr)) {
				                continue;//subStr之前已经被用过了 这里不能再用这个匹配新的char c
				            }
				            map.put(c, subStr);
				            set.add(subStr);
				            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
				                return true;
				            }
				            set.remove(map.get(c));
				            map.remove(c);
				        }
				        return false;
				    }
				}

2. Array Problem
		2.1 Duplicate Problem
			2.1.1 Contains Duplicate I
				public class Solution {
				    public boolean containsDuplicate(int[] nums) {
				        if (nums.length == 0 || nums == null) {
				            return false;
				        }
				        HashSet<Integer> set = new HashSet<Integer>();
				        for (int i : nums) {
				            if (set.contains(i)) {
				                return true;
				            } else {
				                set.add(i);
				            }
				        }
				        return false;
				    }
				}
			2.1.2 Contains Duplicate II
				/*
					Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] 
					and the difference between i and j is at most k.
				*/
				public class Solution {
				    public boolean containsNearbyDuplicate(int[] nums, int k) {
				        if (nums == null || nums.length <= 1) {
				            return false;
				        }
				        HashMap<Integer, Integer> map = new HashMap<>();
				        for (int i = 0; i < nums.length; i++) {
				            if (map.containsKey(nums[i])) {
				                if (i - map.get(nums[i]) <= k) {
				                    return true;
				                }
				            }
				            map.put(nums[i], i);
				        }
				        return false;
				    }
				}
			2.1.3 Contains Duplicate III
				/*
					Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t 
					and the difference between i and j is at most k.
				*/
				public class Solution {
				    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
				        if (t < 0) {
				            return false;
				        }
				        HashMap<Integer, Integer> map = new HashMap<>();//key存储的是nums[i]的值，value是下标
				        t++;
				        for (int i = 0; i < nums.length; i++) {
				            //距离只要超过k就移除最左边界限的数
				            if (i > k) {
				                map.remove(getID(nums[i - k - 1], t));
				            }
				            int m = getID(nums[i], t);
				            //只要存在duplicate则一定会在下标范围k以内，因为上一句已确保
				            if (map.containsKey(m)) {
				                return true;
				            }
				            if (map.containsKey(m - 1) && Math.abs(nums[i] - map.get(m - 1)) < t) {
				                return true;
				            }
				            if (map.containsKey(m + 1) && Math.abs(nums[i] - map.get(m + 1)) < t) {
				                return true;
				            }
				            map.put(m, nums[i]);
				        }
				        return false;
				    }
				    //ID是根据数组的值计算bucket，而不是下标！
				    private int getID(int i, int t) {
				        return i < 0 ? (i + 1)/t - 1 : i / t;
				        //为什么要这么做？因为在java中 -3/5 == 0 而不是-1
				        /*
				            比如t = 4， 则 （-5，-4， -3， -2， -1） 在bucket为 -1的桶里， 
				                         （0， 1， 2， 3， 4）在bucket为0的桶里， 
				                          （-10， -9， -8， -7， -6） 在bucket为-2 的桶里，   i < 0 == (i + 1) / t - 1, 解决负数时的索引问题
				        */
				    }
				}
		2.2 Maximum Size Subarray Sum Equals K
			/*
				Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
				Example 1:
				Given nums = [1, -1, 5, -2, 3], k = 3,
				return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

				Example 2:
				Given nums = [-2, -1, 2, 1], k = 1,
				return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

				Follow Up:
				Can you do it in O(n) time?
			*/
			public class Solution {
			    public int maxSubArrayLen(int[] nums, int k) {
			        HashMap<Integer, Integer> map = new HashMap<>();
			        int sum = 0;
			        int maxLen = 0;
			        for (int i = 0; i < nums.length; i++) {
			            sum += nums[i];
			            if (sum == k) {
			                maxLen = i + 1;
			            } else if (map.containsKey(sum - k)) {
			                maxLen = Math.max(maxLen, i - map.get(sum - k));
			            }
			            if (!map.containsKey(sum)) {
			                map.put(sum, i);
			            }
			            
			        }
			        return maxLen;
			    }
			}



3. Math Problem
	3.1	Happy Number
		/*
			A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
			Example: 19 is a happy number
				12 + 92 = 82
				82 + 22 = 68
				62 + 82 = 100
				12 + 02 + 02 = 1
		*/	
		public class Solution {
		    public boolean isHappy(int n) {
		        HashSet<Integer> set = new HashSet<>();
		        int sum = 0;
		        int remain = 0;
		        while (set.add(n)) {
		            sum = 0;
		            while (n > 0) {
		                remain = n % 10;
		                sum += remain * remain;
		                n = n / 10;
		            }
		            if (sum == 1) {
		                return true;
		            } else {
		                n = sum;
		            }
		        }
		        return false;
		    }
		}
	3.2 Strobogrammatic Number I
		/*
			A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
			Write a function to determine if a number is strobogrammatic. The number is represented as a string.
			For example, the numbers "69", "88", and "818" are all strobogrammatic.
		*/
			public class Solution {
			    public boolean isStrobogrammatic(String num) {
			        if (num == null || num.length() == 0) {
			            return false;
			        }
			        HashMap<Character, Character> map = new HashMap<>();
			        map.put('6', '9');
			        map.put('9', '6');
			        map.put('8', '8');
			        map.put('0', '0');
			        map.put('1', '1');
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
	3.3 Count Primes
		/*
			Description:
				Count the number of prime numbers less than a non-negative number, n.
		*/
			public class Solution {
			    public int countPrimes(int n) {
			        boolean[] notPrimes = new boolean[n];
			        int count = 0;
			        for (int i = 2; i < n; i++) {
			            if (!notPrimes[i]) {
			                count++;
			                for (int j = 2 * i; j < n; j = j + i) {
			                    notPrimes[j] = true;
			                }
			            }
			        }
			        return count;
			    }
			}

	3.4 Fraction To Recurring Decimal
		/*
			Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

			If the fractional part is repeating, enclose the repeating part in parentheses.

			For example,

			Given numerator = 1, denominator = 2, return "0.5".
			Given numerator = 2, denominator = 1, return "2".
			Given numerator = 2, denominator = 3, return "0.(6)".
		*/
			public class Solution {
			    public String fractionToDecimal(int numerator, int denominator) {
			        if (numerator == 0) {
			            return "0";
			        }
			        if (denominator == 0) {
			            return "";
			        }
			        String ans = "";
			        
			        if (((numerator < 0) && (denominator > 0)) || ((numerator > 0) && (denominator < 0)) ) {//按位异或
			            ans += "-";
			        }
			        
			        long num = numerator;
			        long den = denominator;
			        
			        num = Math.abs(num);
			        den = Math.abs(den);
			        
			        long res = num / den;
			        ans += String.valueOf(res);
			        
			        long rem = (num % den) * 10;
			        if (rem == 0) {
			            return ans;
			        }
			        
			        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
			        ans += ".";
			        while (rem != 0) {
			            if (map.containsKey(rem)) {
			                int begin = map.get(rem);
			                String part1 = ans.substring(0, begin);
			                String part2 = ans.substring(begin, ans.length());
			                ans = part1 + "(" + part2 + ")";
			                return ans;
			            }
			            
			            map.put(rem, ans.length());
			            res = rem / den;
			            ans += res + "";
			            rem = (rem % den) * 10;
			        }
			        return ans;
			    }
			}

4. Binary Tree 
		4.1 Binary Tree Vertical Order Traversal
			/*
				Given binary tree [3,9,20,4,5,2,7],
				    _3_
				   /   \
				  9    20
				 / \   / \
				4   5 2   7
				return its vertical order traversal as:
				[
				  [4],
				  [9],
				  [3,5,2],
				  [20],
				  [7]
				]
			*/
			public class Solution {
			    public List<List<Integer>> verticalOrder(TreeNode root) {
			        List<List<Integer>> res = new ArrayList<>();
			        if (root == null) {
			            return res;
			        }
			        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
			        Queue<TreeNode> queue = new LinkedList<>();
			        HashMap<TreeNode, Integer> weight = new HashMap<TreeNode, Integer>();
			        queue.offer(root);
			        weight.put(root, 0);
			        int min = 0;
			        while (!queue.isEmpty()) {
			            TreeNode node = queue.poll();
			            int w = weight.get(node);
			            if (!map.containsKey(w)) {
			                map.put(w, new ArrayList<>());
			            }
			            map.get(w).add(node.val);
			            if (node.left != null) {
			                queue.add(node.left);
			                weight.put(node.left, w - 1);
			            } 
			            if (node.right != null) {
			                queue.add(node.right);
			                weight.put(node.right, w + 1);
			            }
			            min = Math.min(min, w);
			        }
			        while (map.containsKey(min)) {
			            res.add(map.get(min++));
			        }
			        return res;
			    }
			}



















5.1 HashTable Implement
	5.1 Chainning
		public class LinkedHashEntry {
		      private int key;
		      private int value;
		      private LinkedHashEntry next;
		 
		      LinkedHashEntry(int key, int value) {
		            this.key = key;
		            this.value = value;
		            this.next = null;
		      }
		 
		      public int getValue() {
		            return value;
		      }
		 
		      public void setValue(int value) {
		            this.value = value;
		      }
		 
		      public int getKey() {
		            return key;
		      }
		 
		      public LinkedHashEntry getNext() {
		            return next;
		      }
		 
		      public void setNext(LinkedHashEntry next) {
		            this.next = next;
		      }
		}
		 
		public class HashMap {
		      private final static int TABLE_SIZE = 128;
		 
		      LinkedHashEntry[] table;
		 
		      HashMap() {
		            table = new LinkedHashEntry[TABLE_SIZE];
		            for (int i = 0; i < TABLE_SIZE; i++)
		                  table[i] = null;
		      }
		 
		      public int get(int key) {
		            int hash = (key % TABLE_SIZE);
		            if (table[hash] == null)
		                  return -1;
		            else {
		                  LinkedHashEntry entry = table[hash];
		                  while (entry != null && entry.getKey() != key)
		                        entry = entry.getNext();
		                  if (entry == null)
		                        return -1;
		                  else
		                        return entry.getValue();
		            }
		      }
		 
		      public void put(int key, int value) {
		            int hash = (key % TABLE_SIZE);
		            if (table[hash] == null)
		                  table[hash] = new LinkedHashEntry(key, value);
		            else {
		                  LinkedHashEntry entry = table[hash];
		                  while (entry.getNext() != null && entry.getKey() != key)
		                        entry = entry.getNext();
		                  if (entry.getKey() == key)
		                        entry.setValue(value);
		                  else
		                        entry.setNext(new LinkedHashEntry(key, value));
		            }
		      }
		 
		      public void remove(int key) {
		            int hash = (key % TABLE_SIZE);
		            if (table[hash] != null) {
		                  LinkedHashEntry prevEntry = null;
		                  LinkedHashEntry entry = table[hash];
		                  while (entry.getNext() != null && entry.getKey() != key) {
		                        prevEntry = entry;
		                        entry = entry.getNext();
		                  }
		                  if (entry.getKey() == key) {
		                        if (prevEntry == null)
		                             table[hash] = entry.getNext();
		                        else
		                             prevEntry.setNext(entry.getNext());
		                  }
		            }
		      }
		}


	5.2 Open Addressing
		public class HashEntry {
		      private int key;
		      private int value;
		 
		      HashEntry(int key, int value) {
		            this.key = key;
		            this.value = value;
		      }
		 
		      public int getValue() {
		            return value;
		      }
		 
		      public void setValue(int value) {
		            this.value = value;
		      }
		 
		      public int getKey() {
		            return key;
		      }
		}
		 
		public class DeletedEntry extends HashEntry {
		      private static DeletedEntry entry = null;
		 
		      private DeletedEntry() {
		            super(-1, -1);
		      }
		 
		      public static DeletedEntry getUniqueDeletedEntry() {
		            if (entry == null)
		                  entry = new DeletedEntry();
		            return entry;
		      }
		}
		 
		public class HashMap {
		      private final static int TABLE_SIZE = 128;
		 
		      HashEntry[] table;
		 
		      HashMap() {
		            table = new HashEntry[TABLE_SIZE];
		            for (int i = 0; i < TABLE_SIZE; i++)
		                  table[i] = null;
		      }
		 
		      public int get(int key) {
		            int hash = (key % TABLE_SIZE);
		            int initialHash = -1;
		            while (hash != initialHash
		                        && (table[hash] == DeletedEntry.getUniqueDeletedEntry() || table[hash] != null
		                                   && table[hash].getKey() != key)) {
		                  if (initialHash == -1)
		                        initialHash = hash;
		                  hash = (hash + 1) % TABLE_SIZE;
		            }
		            if (table[hash] == null || hash == initialHash)
		                  return -1;
		            else
		                  return table[hash].getValue();
		      }
		 
		      public void put(int key, int value) {
		            int hash = (key % TABLE_SIZE);
		            int initialHash = -1;
		            int indexOfDeletedEntry = -1;
		            while (hash != initialHash
		                        && (table[hash] == DeletedEntry.getUniqueDeletedEntry() || table[hash] != null
		                                   && table[hash].getKey() != key)) {
		                  if (initialHash == -1)
		                        initialHash = hash;
		                  if (table[hash] == DeletedEntry.getUniqueDeletedEntry())
		                        indexOfDeletedEntry = hash;
		                  hash = (hash + 1) % TABLE_SIZE;
		            }
		            if ((table[hash] == null || hash == initialHash)
		                        && indexOfDeletedEntry != -1)
		                  table[indexOfDeletedEntry] = new HashEntry(key, value);
		            else if (initialHash != hash)
		                  if (table[hash] != DeletedEntry.getUniqueDeletedEntry()
		                             && table[hash] != null && table[hash].getKey() == key)
		                        table[hash].setValue(value);
		                  else
		                        table[hash] = new HashEntry(key, value);
		      }
		 
		      public void remove(int key) {
		            int hash = (key % TABLE_SIZE);
		            int initialHash = -1;
		            while (hash != initialHash
		                        && (table[hash] == DeletedEntry.getUniqueDeletedEntry() || table[hash] != null
		                                   && table[hash].getKey() != key)) {
		                  if (initialHash == -1)
		                        initialHash = hash;
		                  hash = (hash + 1) % TABLE_SIZE;
		            }
		            if (hash != initialHash && table[hash] != null)
		                  table[hash] = DeletedEntry.getUniqueDeletedEntry();
		      }
		}




5. Appendix Implement HashTable
public class HashTable {
    public Bucket[] buckets;
    public int entryNum;
    public static final int DEFAULT_SIZE = 2029;
    /**
     * default constructor with size 2029
     */
    public HashTable(){
    	buckets = new Bucket[DEFAULT_SIZE];
    	entryNum = 0;
    }
	
    /**
     * constructor for hash table with a given size
     * @param size
     */
    public HashTable(int size){
    	buckets = new Bucket[size];
    	entryNum = 0;
    }
    
    /**
     * hash function using double hashing
     * for the collision resolution function, we choose
     * hash_2(k) = R - (k mod R) 
     * where R is a prime number that is smaller than the size of the table
     * since we know the size of hash table will grow larger than the default size
     * as we keep inserting, we can choose R to be 2027 
     * 
     * We choose this collision resolution function because it never evaluates to zero and 
     * all cells in the table can be probed.
     * This function is also easy and quick to compute and achieves even distribution
     * Also, the lecture slide says it is a popular hash function for double addressing
     * @param b
     * @param j
     * @return value of hash function
     */
    public int hashFunction(Bucket b, int j){
    	int i = b.getKey();
    	int m = buckets.length;
    	int hash_1 = i % m;
    	int hash_2 = 2027 - (i % 2027);
    	return (hash_1 + j*hash_2) % m;
    }
    
    /**
     * insert a process as a bucket
     * @param b
     * @return index of the inserted item
     *         -1 if it is a duplicate
     */
    public int insert(Bucket b){
    	//if table not large enough, do rehasing
    	if(1.0*entryNum/buckets.length >= 0.5){
    		rehash();
    	}
    
    	int i = 0;
    	do{
    		int j = hashFunction(b,i);
    		//find duplicate
    		if(buckets[j] != null){
    			if(b.getKey() == buckets[j].getKey()){
    				return -1;
    			}
    		}
    		
    		//find right position to insert
    		if(buckets[j]==null || buckets[j].getLive()==false){
    			buckets[j] = b;
    			entryNum ++;
    			return j;
    		}
    		//keep probing
    		else{
    			i ++;
    		}
    	}while(i!=buckets.length-1);
    	return -1;
    }
    
    /**
     * remove process from hash table
     * @param b
     */
    public void remove(Bucket b){
    	//find the index of the process to be removed
    	int index = findIndex(b);
    	//set the bucket to dead
    	if(isLive(index)){
    		buckets[index].setLive(false);
    		entryNum --;
    	}
    }
    
    
    /**
     * search for a process in the hash table
     * @param b
     * @return index of process to be searched and -1 otherwise
     */
    public int search(Bucket b){
    	int i = 0;
    	int j = hashFunction(b,i);
    	while(buckets[j]!=null || i!=buckets.length-1){
    		if(b.equals(buckets[j])){
    			return j;
    		}else{
    			i ++;
    			j = hashFunction(b,i);
    		}	
    	}
    	return -1;
    }
    
   
    /**
     * find the index of a given bucket
     * @param b
     * @return index of the given bucket
     */
    public int findIndex(Bucket b){
    	int i = 0;
  
    	int curr = hashFunction(b,i);
    	while(buckets[curr]!=null & !b.equals(buckets[curr])){
    		i ++;
    		curr = hashFunction(b,i);
    	}
    	return curr;
    }
    

    
    /**
     * check if the bucket is live
     * @param i
     * @return true if the bucket is live and false otherwise
     */
    public boolean isLive(int i){
    	if(buckets[i]!=null && buckets[i].getLive()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
     * make hash table empty
     */
    public void makeEmpty(){
    	entryNum = 0;
    	for(int i=0; i<buckets.length; i++){
    		buckets[i] = null;
    	}
    }
    
    /**
     * rehashing the table
     */
    public void rehash(){
    	Bucket[] oldBuckets = buckets;
    	//enlarge the table
    	//the new size is the next prime larger than twice the old size
    	buckets = new Bucket[2*oldBuckets.length];
    	entryNum = 0;
    	//insert old buckets into the new hash table
    	for(int i=0; i<oldBuckets.length; i++){
    		if(oldBuckets[i]!=null && oldBuckets[i].getLive()){
    			insert(oldBuckets[i]);
    		}
    	}
    }
    
    
    /**
     * get the size of hash table
     * @return size of hash table
     */
    public int getSize(){
    	return entryNum;
    }
}

