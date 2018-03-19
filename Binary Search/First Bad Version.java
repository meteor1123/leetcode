/*
	First Bad Version

		The code base version is an integer and start from 1 to n. One day, someone commit a bad version in the code case, so it caused itself and the following versions are all failed in the unit tests.
	You can determine whether a version is bad by the following interface: 

	Java:
	    public VersionControl {
	        boolean isBadVersion(int version);
	    }
	C++:
	    class VersionControl {
	    public:
	        bool isBadVersion(int version);
	    };
	Python:
	    class VersionControl:
	        def isBadVersion(version)

	Find the first bad version.
	Note
	You should call isBadVersion as few as possible. 

	Please read the annotation in code area to get the correct way to call isBadVersion in different language. For example, Java is VersionControl.isBadVersion.

	Example
	Given n=5

	Call isBadVersion(3), get false

	Call isBadVersion(5), get true

	Call isBadVersion(4), get true

	return 4 is the first bad version

	Challenge
	Do not call isBadVersion exceed O(logn) times.
*/

//Solution1
public class Solution {
	 public int findFirstBadVersion(int n) {
	 	int l = 1;
	 	int r = n;
	 	while (l <= r) {
	 		int m = (l + r) / 2;
	 		if (isBadVersion(m)) {
	 			r = m - 1; 
	 		} else {
	 			l = m + 1;
	 		}
	 	}
	 	return l;
	 }
}


//Solution2
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start)/2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (isBadVersion(start)) {
            return start;
        } else {
            return end;
        }
        
    }
}