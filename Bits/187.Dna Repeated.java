/*
	Repeated DNA Sequences
	All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
	Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
	For example:
		Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

		Return:
		["AAAAACCCCC", "CCCCCAAAAA"].
	Tag: Hash Table, Bit Manipulation
*/


/*
	直接存String 容易内存exceed
*/
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        if (s.length() < 11 || s == null)
            return res;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        HashSet<Integer> set = new HashSet<Integer>();
        HashSet<Integer> unique = new HashSet<Integer>();
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i < 9) 
                hash = (hash << 2) + map.get(c);
            else {
                hash = (hash << 2) + map.get(c);
                hash &= (1 << 20) - 1;//对10个数 用掩码取值
                // 1 << 20位代表 1后面跟着20个0，2进制，再-1，表示从 0000 0000 0001 0000 0000 0000 0000 0000 --> 0000 0000 0000 1111 1111 1111 1111 1111
                // 为什么要用20位掩码取值？因为我们只需要 0 - 19 位 总共20位的数，而每次循环 hash都会左移 + 新的字符，所以需要规避无效位数的干扰
               
                if (set.contains(hash) && !unique.contains(hash)) {
                    res.add(s.substring(i - 9, i + 1));
                    unique.add(hash);
                }
                //set无，unique无，无数，放入set
                //set无，unique有，不可能

                //set有，unique有，重复次数大于2，重复值不处理
                //set有，unique无，重复次数等于2，刚好有一个重复，加入res
                else 
                    set.add(hash);
            }
        }
        return res;
    }


    //Solution2
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> unique = new HashSet<>();
        Set<Integer> doubleWords = new HashSet<>();
        List<String> res = new ArrayList<>();
        char[] map = new char[26];
        map['A' - 'A'] = 0;//可以省略
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;
        
        for (int i = 0 ; i < s.length() - 9; i++) {
            int hash = 0;
            for (int j = i; j < i + 10; j++) {
                hash = hash << 2;
                hash = hash | map[s.charAt(j) - 'A'];
            }
            if (!unique.add(hash) && doubleWords.add(hash)) {
                res.add(s.substring(i, i + 10));
            }
        }
        return res;
    }
}


public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() <= 10) {
            return res;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 0);
        map.put('C', 1);
        map.put('G', 2);
        map.put('T', 3);
        //key是10位数字通过左移+组成的hash值， value是出现的次数,当且仅当出现次数=1的时候才将结果加入res，有效规避duplicate
        HashMap<Integer, Integer> item = new HashMap<>();
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i < 9) {
                hash = (hash << 2) + map.get(c);
            } else {
                hash = (hash << 2) + map.get(c);
                hash &= (1 << 20) - 1;
                // 1 << 20位代表 1后面跟着20个0，2进制，再-1，表示从 0000 0000 0001 0000 0000 0000 0000 0000 --> 0000 0000 0000 1111 1111 1111 1111 1111
                // 为什么要用20位掩码取值？因为我们只需要 0 - 19 位 总共20位的数，而每次循环 hash都会左移 + 新的字符，所以需要规避无效位数的干扰
                if (item.get(hash) == null) {
                    item.put(hash, 1);
                } else if (item.get(hash) == 1) {
                    item.put(hash, 2);
                    res.add(s.substring(i - 9, i + 1));
                }
            }
        }
        return res;
    }
}
