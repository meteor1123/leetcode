/*
	H-Index II
	Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
*/

public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int len = citations.length;
        int start = 0;
        int end = len - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (citations[mid] == len - mid) {
                return len - mid;
            } else if (citations[mid] < len - mid){
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (citations[start] >= len - start) {
            return len - start;
        } else if (citations[end] >= len - end) {
            return len - end;
        } else {
            return 0;
        }
    }
}