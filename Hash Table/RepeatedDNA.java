public class Solution {
    
    /*
        Solution: 
            step1: corner case,假如长度小于11或者==null
            step2: 由于直接比较Stirng会占用大量的存储空间，因此我们用2位bit 来表示4位数，00, 01, 10,11
            step3: 设定两个HashSet，一个存储结果，一个用来验证结果的唯一性
            step4: 循环字符串，当i < 9 时，不停左移 + 字符的ascal码
            step5：大于等于10，对10个数 用掩码取值
            step6：两个hashset判断是否已存在
        
    */
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
                // 1 << 20位代表 1后面跟着20个0，2进制，再-1，表示从 1 0000 0000 0000 0000 0000 --> 1111 1111 1111 1111 1111
                if (set.contains(hash) && !unique.contains(hash)) {
                    res.add(s.substring(i - 9, i + 1));
                    unique.add(hash);
                }
                else 
                    set.add(hash);
            }
        }
        return res;
    }
}