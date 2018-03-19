/*
	One Edit Distance
	Given two strings S and T, determine if they are both one edit distance apart.
	Notice:
			Must be exactly one distance apart. Not the same
*/


//Solution1 prference
public class OneEditDistanceCheck {
	public boolean isOneEditDistance (String s, String t) {
        if (s.length() == 0 && t.length() == 0) {
            return false;
        }
        if (s.length() + t.length() == 1) {
            return true;
        }
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        if (s.length() == t.length()) {
            return isOneReplace(s,t);
        }
        if (s.length() > t.length()){
            return isOneInsert(s,t);
        } else {
            return isOneInsert(t,s);
        }
    }
    
    public boolean isOneInsert(String a, String b) {
        //set a boolean variable to check the String's modified statue
        boolean modified = false;
        //use index1 to traverse the string a
        int index1 = 0;
        //use index2 to traverse the string b
        int index2 = 0;
        
       
        while (index2 < b.length()) {
            //if the character is matched,then go ahead
            if (a.charAt(index1) == b.charAt(index2)) {
                index1++;
                index2++;
            } else {
            //else check the modified statue, if not modified,means we can insert a character to avoid the unmatched,
                if (modified) {
                    return false;
                //if the modified is false, means we still have one chance to insert the character to make sure the matched.
                } else {
                    //then just move the index1
                    index1++;
                    modified = true;
                }
            }
        }
        return true;
    }
    //use this method when the length of a and b is equals
    public boolean isOneReplace(String a, String b) {
    	//use a boolean variable modified to check the edit status
        boolean modified = false;
        //since the length of a and b is equals, so we only use one pointer to check
        int index1 = 0;
        while (index1 < a.length()) {
            if (a.charAt(index1) == b.charAt(index1)) {
                index1++;
            //if the character isn't equal, and was already modified,means no chance to edit again
            //So we return false;
            } else if (modified) {
                return false;
            } else {
                index1++;
                modified = true;
            }
        }
        //Why need to check the modified at last?since if a and b is same string,
        //which is wrong one edit distance !
        if (modified) {
            return true;
        } else {
            return false;
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OneEditDistanceCheck test = new OneEditDistanceCheck();
		String a = "abdec";
		String b = "abcde";
		if(test.isOneEditDistance(a , b)) {
			System.out.println("Valid One distance!");
		} else {
			System.out.println("unvalid one distance!");
		}
		
	}
}

//Solution2  Recursive
public class Solution {
   public boolean isOneEditDistance(String s, String t) {
       return check(s,t,0,0,0);
  }
  
  public boolean check(String s, String t, int i, int j, int distance){
      while(0<=i && i<=s.length()-1 && 0<=j && j<=t.length()-1){
          if(s.charAt(i) != t.charAt(j)){
              distance++;
              if(distance>1) return false;
              return check(s,t,i+1,j,distance) || check(s,t,i,j+1,distance) || check(s,t,i+1,j+1,distance);
          }else{
              return check(s,t,i+1,j+1,distance);
          }
      }
      
      if(distance ==1){
          return i==s.length() && j==t.length();
      }else { //(distance ==0)
          return Math.abs(s.length()-t.length())==1;
      }
  }
} 

//Solution 3 Dynamic Programming
public class Solution {
   public boolean isOneEditDistance(String s, String t) {
       if (s==null || t==null) return false;
       int[][] res = new int[s.length()+1][t.length()+1];
       res[0][0] = 0;
       for (int i=1; i<=s.length(); i++) {
           res[i][0] = i;
       }
       for (int j=1; j<=t.length(); j++) {
           res[0][j] = j;
       }
       for (int i=1; i<=s.length(); i++) {
           for (int j=1; j<=t.length(); j++) {
               int minOftwo = Math.min(res[i-1][j], res[i][j-1]) + 1;
               int replace = s.charAt(i-1) == t.charAt(j-1)? res[i-1][j-1] : res[i-1][j-1]+1;
               res[i][j] = Math.min(minOftwo, replace);
               if (res[i][j] >= 2) return false;
           }
       }
       return res[s.length][t.length]==1? true : false;
   }
}
