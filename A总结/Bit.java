/*
	Bit Manipulation
	http://www.matrix67.com/blog/archives/263
*/


/*
	 Leetcode summary 
	 https://leetcode.com/problems/sum-of-two-integers/discuss/
*/
/*
	Details
	Basics
	At the heart of bit manipulation are the bit-wise operators & (and), | (or), ~ (not) and ^ (exclusive-or, xor) and shift operators a << b and a >> b.

	There is no boolean operator counterpart to bitwise exclusive-or, but there is a simple explanation. The exclusive-or operation takes two inputs and returns a 1 if either one or the other of the inputs is a 1, but not if both are.
	That is, if both inputs are 1 or both inputs are 0, it returns 0. Bitwise exclusive-or, with the operator of a caret, ^, performs the exclusive-or operation on each pair of bits. Exclusive-or is commonly abbreviated XOR.

	Set union A | B
	Set intersection A & B
	Set subtraction A & ~B
	Set negation ALL_BITS ^ A or ~A
	Set bit A |= 1 << bit
	Clear bit A &= ~(1 << bit)
	Test bit (A & 1 << bit) != 0
	Extract last bit A&-A or A&~(A-1) or x^(x&(x-1))
	Remove last bit A&(A-1)
	Get all 1-bits ~0
*/

/*
	异或操作的一些特点：

	x ^ 0 = x
	x ^ 1s = ~x // 1s = ~0
	x ^ (~x) = 1s
	x ^ x = 0 // interesting and important!
	a ^ b = c => a ^ c = b, b ^ c = a // swap
	a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c // associative
	移位操作

	移位操作可近似为乘以/除以2的幂。0b0010 * 0b0110等价于0b0110 << 2. 下面是一些常见的移位组合操作。

	将x最右边的n位清零 - x & (~0 << n)
	获取x的第n位值(0或者1) - x & (1 << n)
	获取x的第n位的幂值 - (x >> n) & 1
	仅将第n位置为1 - x | (1 << n)
	仅将第n位置为0 - x & (~(1 << n))
	将x最高位至第n位(含)清零 - x & ((1 << n) - 1)
	将第n位至第0位(含)清零 - x & (~((1 << (n + 1)) - 1))
	仅更新第n位，写入值为v; v为1则更新为1，否则为0 - mask = ~(1 << n); x = (x & mask) | (v << i)
 */

1. Single Number Problem
		1.1 Single Number I
			/*
				Given an array of integers, every element appears twice except for one. Find that single one.
				Note:
				Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
			*/
			public class Solution {
			    public int singleNumber(int[] nums) {
			        int res = 0;
			        for (int num : nums) {
			            res ^= num;
			        }
			        return res;
			    }
			}
		1.2 Single Number II
			/*
				Given an array of integers, every element appears three times except for one. Find that single one.
				Note:
				Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
			*/
			/*
				Solution: 
					1. 核心思想，一个int有32位bit，计算每个bit上1出现的次数，因为除了一个数出现一次，其他所有数都出现3次
							   因此 将所有数组里的数的 每个bit 分别相加，一定是3的倍数，不是3的倍数的位数就是那个只出现一次的数的1的位数！
					2. bits[i] += ((A[j] >> i) & 1);  这句话的意思是，将A[j] 右移 i位 再与1 进行单个与，
													  即可以获得A[j]第i + 1 位上的值.
					3. result |= (bits[i] << i); 当我们获得了不同位数上的1时， 比如3 = 11，  则10 | 01 即可还原成3
												 bits[i] << i 就是将这个1 左移i位，再或res，逐步获得singler umber
			*/
			public class Solution {
			    public int singleNumber(int[] nums) {
			        if (nums == null || nums.length == 0) {
			            return -1;
			        }
			        int res = 0;
			        int[] bits = new int[32];
			        for (int i = 0; i < 32; i++) {
			            for (int j = 0; j < nums.length; j++) {
			                bits[i] += ((nums[j] >> i) & 1);
			                bits[i] %= 3;
			            }
			            res |= bits[i] << i;
			        }
			        return res;
			    }
			}
		1.3 Single Number III
			/*
				Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
				For example:

				Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

				Note:
				The order of the result is not important. So in the above example, [5, 3] is also correct.
				Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
			*/
			public class Solution {
			    public int[] singleNumber(int[] nums) {
			        // Pass 1 : 
			        // Get the XOR of the two numbers we need to find
			        int diff = 0;
			        for (int num : nums) {
			            diff ^= num;
			        }
			        // Get its last set bit
			        diff &= -diff;//这样可以得到两个single number 不相同的最靠近右边的位数 

			        // Pass 2 :
			        //
			        //我们根据最右边这位数 去划分整个数组，一定会将两个single number分开
			        int[] res = {0, 0}; // this array stores the two numbers we will return
			        for (int num : nums) {
			            if ((num & diff) == 0) {// the bit is not set
			                res[0] ^= num;
			            }
			            else {// the bit is set
			                res[1] ^= num;
			            }
			        }
			        return res;
			    }
			}
		1.4 Missing Number
			/*
				Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

				For example,
				Given nums = [0, 1, 3] return 2.

				Note:
				Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
			*/

			//Solution1: bits prefer
			public class Solution {
				//这里的数和index是对应的， 除了missing number，将所有的数与其对应的index进行xor操作
			    public int missingNumber(int[] nums) {
			        int xor = 0;
			        int i = 0;
			        for (; i < nums.length; i++) {
			            xor = xor ^ i ^ nums[i];
			         //                    nums[0]=0 nums[1]=2  nums[2]=3    i=3
			        }// 形如 0 ，2 ， 3 = 0 ^ (0 ^ 0) ^ (1 ^ 2) ^ (2 ^ 3)   ^  3, 每一个存在的i都对应这一个nums[i] 可以消除，只有缺失的那个不存在
			        return xor ^ i;
			    }
			}
			//Solution2:
			public class Solution {
			    public int missingNumber(int[] nums) {
			        int sum = 0;
			        for(int num: nums)
			            sum += num;

			        return (nums.length * (nums.length + 1) )/ 2 - sum;
			    }
			}

2. Binary Math Operation
		2.0 A + B Problem
			/*
				A + B Problem
				Write a function that add two numbers A and B. You should not use + or any arithmetic operators.

				Have you met this question in a real interview? Yes
				Example
				Given a=1 and b=2 return 3
			 */

			/*
				位运算实现整数加法本质就是用二进制进行运算。
				其主要用了两个基本表达式：
				(1) x^y //执行加法，不考虑进位。
				(2) (x&y)<<1 //进位操作
				令x=x^y ；y=(x&y)<<1 进行迭代，
				每迭代一次进位操作右面就多一位0，最多需要“加数二进制位长度”次迭代就没有进位了，此时x^y的值就是结果。

				我们来做个3位数的加法：101+011=1000 //正常加法
					位运算加法：
					（1） 101 ^ 011 = 110
						 (101 & 011)<<1 = 010
					（2） 110 ^ 010 = 100
						 (110 & 010)<<1 = 100
					（3） 100 ^ 100 = 000
						 (100 & 100)<<1 = 1000
				此时进行相加操作就没有进位了，即000 ^ 1000=1000即是最后结果。
			 */


			class Solution {
			    public int aplusb(int a, int b) {
			        // write your code here, try to do it without arithmetic operators.
			        while (b != 0) {
			            int carry = a & b;
			            a = a ^ b;
			            b = carry << 1;
			        }
			        return a;
			    }
			};


		2.0.1 Power Of Two
			/*
				Given an integer, write a function to determine if it is a power of two.
			*/
			/*
				Key Point: Power of 2 means only one bit of n is '1', so use the trick n&(n-1)==0 to judge whether that is the case

				(n & (n - 1))将最右边的1置为0，如果不等于0说明还有其他比特位上有1， 而Power of 2意味着只有一个bit位上有1
			*/
		    /*
		    	y = x & (x - 1)

					01010111    (x)
				&   01010110    (x-1)
				    --------
				    01010110

				    01011000    (x)
				&   01010111    (x-1)
				    --------
				    01010000

				    10000000    (x = -128)
				&   01111111    (x-1 = 127 (with overflow))
				    --------
				    00000000

				    11111111    (x = all bits 1)
				&   11111110    (x-1)
				    --------
				    11111110

				    00000000    (x = no rightmost 1-bits)
				&   11111111    (x-1)
				    --------
				    00000000
		    */
			public class Solution {
			    public boolean isPowerOfTwo(int n) {
			        return n > 0 && (n & (n - 1)) == 0;
			    }
			}
		2.0.2 Power Of Three
			//Solution1
			public boolean isPowerOfThree(int n) {
			    return (Math.log10(n) / Math.log10(3)) % 1 == 0;
			}
			//Solution2
			public class Solution {
			    public boolean isPowerOfThree(int n) {
			        return n > 0 && (Math.pow(3, 19) % n == 0);
			    }
			}
		2.0.3 Power Of Four 
			/*
				我们知道
				(1)num & (num - 1)可以用来判断一个数是否为2的次方数，更进一步说，就是二进制表示下，只有最高位是1，那么由于是2的次方数，不一定是4的次方数，比如8，所以我们还要其他的限定条件，
				(2)我们仔细观察可以发现，4的次方数的最高位的1都是计数位，那么我们只需与上一个数(0x55555555) <==> 1010101010101010101010101010101，如果得到的数还是其本身，则可以肯定其为4的次方数：
			 */
			public class Solution {
			    public boolean isPowerOfFour(int num) {
			        return num > 0 && (num & (num - 1 )) == 0 && (num & 0x55555555) != 0;
			    }
			}
		2.1 Number Of 1 Bits
			/*
				Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
				For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
			*/
			public class Solution {
			    // you need to treat n as an unsigned value
			    public int hammingWeight(int n) {
			        int count = 0;
			        while (n != 0) {
			            count = count + (n & 1);//将n不停右移与1进行&操作
			            n = n >>> 1;
			        }
			        return count;
			    }
			}
		2.2 Bitwise AND Of Numbers Range
			/*
				Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
				For example, given the range [5, 7], you should return 4.
			*/
			/*
				bitwise ：按位与
				由数据范围0 <= m <= n <= 2147483647可知，时间复杂度O（n）及以上的解法是不可接受的。
				因此可以判断此题为数学题。


				101　　110　　111
				5, 6, 7相与后的结果为100，仔细观察我们可以得出，最后的数是该数字范围内所有的数的左边共同1的部分，
				如果上面那个例子不太明显，我们再来看一个范围[26, 30]，它们的二进制如下：

				11010　　11011　　11100　　11101　　11110
				发现了规律后，我们只要写代码找到左边公共1的部分即可，我们可以从建立一个32位都是1的mask
				，然后每次向左移一位，比较m和n是否相同，不同再继续左移一位，直至相同，然后把m和mask相与就是最终结果，代码如下：
			*/
			public class Solution {
			    public int rangeBitwiseAnd(int m, int n) {
			        int i = 0;
			        while (m != n) {
			            m >>= 1;
			            n >>= 1;
			            i++;
			        }
			        return m << i;
			    }
			}
		2.3 Reverse Bits
			/*
				Reverse bits of a given 32 bits unsigned integer.
				For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

				Follow up:
				If this function is called many times, how would you optimize it?
			*/
			//Solution1:
			public class Solution {
			    // you need treat n as an unsigned value

			    /*
			    	右移: >> (正数左补0， 负数左补1) 1000 >> 1:  1100
			    	左移: << (正负数皆右补0)        1000 >>>1:  0100
			    */
			     public int reverseBits(int n) {
			        int res = 0;
			        for (int i = 0; i < 32; i++) {
			            res = res + (n & 1);//n & 1是每次只
			            n = n >>> 1;//// CATCH: must do unsigned shift,无符号右移，n不断右移，再下一个循环中&1 取n最右边一位的数和res相加
			            if (i < 31) {
			                res = res << 1;// CATCH: for last digit, don't shift!
			            }
			        }
			        return res;
			    }
			}
			//Solution2 : Optimize,  called many times
			/*
			        How to optimize if this function is called multiple times? 
			        We can divide an int into 4 bytes, and reverse each byte then combine into an int. 
			        For each byte, we can use cache to improve performance.

			*/
			public class Solution {
			// cache
			    private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
			    public int reverseBits(int n) {
			        byte[] bytes = new byte[4];
			        for (int i = 0; i < 4; i++) {// convert int into 4 bytes
			            bytes[i] = (byte)((n >>> 8*i) & 0xFF);
			        }
			        int result = 0;
			        for (int i = 0; i < 4; i++) {
			            result += reverseByte(bytes[i]); // reverse per byte
			            if (i < 3) {
			                result <<= 8;
			            }
			        }
			        return result;
			    }

			    private int reverseByte(byte b) {
			        Integer value = cache.get(b); // first look up from cache
			        if (value != null) {
			            return value;
			        }
			        value = 0;
			        // reverse by bit
			        for (int i = 0; i < 8; i++) {
			            value += ((b >>> i) & 1);
			            if (i < 7) {
			                value <<= 1;
			            }
			        }
			        cache.put(b, value);
			        return value;
			    }
			}
		2.4 Number Of Digit One
			/*
				Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
				For example:
				Given n = 13,
				Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
			*/
			/*
			    intuitive: 每10个数, 有一个个位是1, 每100个数, 有10个十位是1, 每1000个数, 有100个百位是1.  做一个循环, 每次计算单个位上1得总个数(个位,十位, 百位).  
			    例子:
			    以算百位上1为例子:   假设百位上是0, 1, 和 >=2 三种情况: 
			        case 1: n = 3141092, a= 31410, b=92. 计算百位上1的个数应该为 3141 *100 次.
			                百位出现的次数有 0000100 ~ 0000199(100个) 0001100 ~ 0001199（100）  3140100 ~ 3140199 （100） 一共3141 * 100

			        case 2: n = 3141192, a= 31411, b=92. 计算百位上1的个数应该为 3141 *100 + (92+1) 次. 多了从3141100 ~ 3141192 93个
			        case 3: n = 3141592, a= 31415, b=92. 计算百位上1的个数应该为 (3141+1) *100 次.  3141592 多了 从 3141100 ~ 3141199，100个
			    以上三种情况可以用 一个公式概括:
			    (a + 8) / 10 * m + (a % 10 == 1) * (b + 1);
			*/
			public class Solution {
			    public int countDigitOne(int n) {
			        int res = 0;
			        for (long m = 1; m <= n; m *= 10) {
			            // int a = n / m;
			            // int b = n % m;
			            res += (n / m + 8) / 10 * m + (n / m % 10 == 1 ? (n % m + 1) : 0);
			          //res += (a + 8) / 10 * m + (a % 10 == 1) * (b + 1);
			        }
			        return res;
			    }
			}
		2.5 Flip Bits
			/*
				Determine the number of bits required to flip if you want to convert integer n to integer m.

				Example
				Given n = 31 (11111), m = 14 (01110), return 2.

				Note
				Both n and m are 32-bit integers.
			 */
			public class Solution {
			    public static int bitSwapRequired(int a, int b) {
			        // write your code here
			        int c = a ^ b;
			        int res = 0;
			        for (int i = 0; i < 32; i++) {
			            if ((c & 1) != 0) {
			                res++;
			            }
			            c = c >> 1;
			        }
			        return res;
			    }
			}



3. Bits Manipulation In String Operation
		3.1 Repeated DNA Sequences
			/*
				All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
				When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
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
			        mapping.put('A', 0);//用2个bits去表示一个数字，总共只需要20个bits表示10个字符
			        mapping.put('C', 1);
			        mapping.put('G', 2);
			        mapping.put('T', 3);
			        //key是10位数字通过左移+组成的hash值， value是出现的次数,当且仅当出现次数=1的时候才将结果加入res，有效规避duplicate
			        HashMap<Integer, Integer> map = new HashMap<>();
			        int hash = 0;
			        for (int i = 0; i < s.length(); i++) {
			            char c = s.charAt(i);
			            if (i < 9) {
			                hash = (hash << 2) + mapping.get(c);
			            } else {
			                hash = (hash << 2) + mapping.get(c);
			                hash &= (1 << 20) - 1;
			                // 1 << 20位代表 1后面跟着20个0，2进制，再-1，表示从 0000 0000 0001 0000 0000 0000 0000 0000 --> 0000 0000 0000 1111 1111 1111 1111 1111
			                // 为什么要用20位掩码取值？因为我们只需要 0 - 19 位 总共20位的数，而每次循环 hash都会左移 + 新的字符，所以需要规避无效位数的干扰
			                if (map.containsKey(hash) && map.get(hash) == 1) {
			                    map.put(hash, map.get(hash) + 1);
			                    res.add(s.substring(i - 9, i + 1));
			                }
			                if (!map.containsKey(hash)) {
			                    map.put(hash, 1);
			                }
			            }
			        }
			        return res;
			    }
			}
	3.2 Maximum Product Of Word Lengths
		/*
			Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
			You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
			Example 1:
				Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
				Return 16
				The two words can be "abcw", "xtfn".
			Example 2:
				Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
				Return 4
				The two words can be "ab", "cd".

			Example 3:
				Given ["a", "aa", "aaa", "aaaa"]
				Return 0
				No such pair of words.
		*/
		public class Solution {
		    public int maxProduct(String[] words) {
		        if (words == null || words.length <= 1) {
		            return 0;
		        }
		        //Pre-processing
		        int[] preprocess = new int[words.length];
		        for (int i = 0; i < words.length; i++) {
		            preprocess[i] = 0;
		            for (int j = 0; j < words[i].length(); j++) {
		                //核心思想，因为一个单词就26个字母，我们将一个单词出现的字母将它出现的bit位置1。下次只需要比较两个字母的bit &是否等于0，如果等于0就是没有重叠的字符
		                preprocess[i] |= (1 << words[i].charAt(j) - 'a');
		            }
		        }
		        
		        int maxProduct = 0;
		        for (int i = 0; i < words.length; i++) {
		            for (int j = i + 1; j < words.length; j++) {
		                if ((preprocess[i] & preprocess[j]) == 0) {
		                    maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
		                }
		            }
		        }
		        return maxProduct;
		    }
		}
