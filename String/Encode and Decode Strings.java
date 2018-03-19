/*
	Encode and Decode Strings

	Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

	Machine 1 (sender) has the function:

	string encode(vector<string> strs) {
	  // ... your code
	  return encoded_string;
	}
	Machine 2 (receiver) has the function:
	vector<string> decode(string s) {
	  //... your code
	  return strs;
	}
	So Machine 1 does:

	string encoded_string = encode(strs);
	and Machine 2 does:

	vector<string> strs2 = decode(encoded_string);
	strs2 in Machine 2 should be the same as strs in Machine 1.

	Implement the encode and decode methods.

	Note:
	The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
	Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
	Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
*/

/*
	Solution: 1. encode string =   s.length() + '/' + s  
			  2. decode string, 
			  	 at first using indexOf('/') get the first slash
			  	 second, get the size, s.substring(i, slash);
			  	 third, add the substring(slash + 1, slash + 1 + size);
			  	 i = slash + size + 1, next loop
*/
public class Codec {

	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append(s.length()).append('/').append(s);
		}
		return sb.toString();
	}

	public List<String> decode(String s) {
		List<String> res = new ArrayList<>();
		int i = 0;
		while (i < s.length()) {
			//Returns the index within this string of the first occurrence of the specified character, 
			//starting the search at the specified index.
			int slash = s.indexOf('/', i);//indexOf(int ch, int fromIndex)
			int size = Integer.valueOf(s.substring(i, slash));
			res.add(s.substring(slash + 1, slash + size + 1));
			i = slash + size + 1;
		}
		return res;
	}
}




