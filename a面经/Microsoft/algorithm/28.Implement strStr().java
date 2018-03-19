/*
    Implement strStr().
    Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/
/*
    solution1： KMP
    running tune: O(m + n)

*/
    public int strStr(String haystack, String needle) {
            if (haystack == null)
                return -1;
            if (needle.length() == 0)
                return 0;
            int[] next = new int[needle.length()];
            int i = 0;
            int j = 0;
            setNext(needle, next);
            while (i < haystack.length()) {
                if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                    i++;
                    j++;
                } else {
                    j = next[j];
                }
                if (j == needle.length())
                    return i - j;
            }
            return -1;
        }
        
        //create a Next[] is core idea
        public void setNext(String m, int[] next) {
            char[] s = m.toCharArray();
            int length = m.length();

            int k = -1;
            int j = 0;

            next[0] = -1;//initialize

            //notice that ,next[++j] will overflow when j = length - 1, that's why j need to smaller than length - 1
            while (j < length - 1) {
                if (k == -1 || s[k] == s[j])
                    next[++j] = ++k;
                else {
                    k = next[k];//if unmatched, replace the k with next[k]
                }
            }
        }
    }





/*
    solution2: brute force algorithm
                step1: if haystack == null,means impossible to matched
                       if needle == null, means any haystack except the null that can be matched
                step2: Using the length of needle for every step to traverse the haystack
    running time:O(n*m)
                
*/
    public int strStr(String haystack, String needle) {
        if (haystack == null)
            return -1;
        if (needle.length() == 0)
            return 0;
        for (int i = 0; i <= haystack.length() - needle.length (); i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if (j == needle.length() - 1) {
                    return i;
                }
            }
        }
        return -1;
    }