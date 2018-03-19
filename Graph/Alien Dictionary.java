/*
	Alien Dictionary
	There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

	For example,
	Given the following words in dictionary,

	[
	  "wrt",
	  "wrf",
	  "er",
	  "ett",
	  "rftt"
	]
	The correct order is: "wertf".

	Note:
	You may assume all letters are in lowercase.
	If the order is invalid, return an empty string.
	There may be multiple valid order of letters, return any one of them is fine.
	Tags: Graph, Topological Sort
*/


//prefer
class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap();
        //建图,每一个字符为一个顶点，邻接的两个字符视为有一条边连接
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char ch = words[i].charAt(j);
                if (!graph.containsKey(ch)) {
                    graph.put(ch, new HashSet());
                }
            }
            if (i > 0)
                // getOrder from words array中相邻的两个字符串
                getOrder(words[i - 1], words[i], graph);
        }
        return topSort(graph);
    }
    
    //check two adjencent strings, 
    //e.g. : "wer" and "wet", then 'r' is absolutely before 't'
    public void getOrder(String s1, String s2, Map<Character, Set<Character>> graph) {
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            
            //只能判断两个字符串中第一个不等的字符的order，后面的无法判断
            if (c1 != c2) {
                if (!graph.get(c1).contains(c2)) {
                    graph.get(c1).add(c2);
                }
                break;
            } 
        }
    }
    
    // 开始拓扑排序
    public String topSort(Map<Character, Set<Character>> graph) {
        StringBuilder sb = new StringBuilder();
        // 入度map
        Map<Character, Integer> indegree = new HashMap();
        Queue<Character> queue = new ArrayDeque();
        
        // 通过getOrder方法建立字符之间的顺序，也就是边，c1 -> c2, 则c2的入度++， 建立入度map
        for (char c1 : graph.keySet()) {
            for (char c2 : graph.get(c1)) {
                indegree.put(c2, indegree.getOrDefault(c2, 0) + 1);
            }
        }
        
        // 将入度为0的顶点加入队列
        for (char c : graph.keySet()) {
            if (!indegree.containsKey(c)) {
                queue.offer(c);
            }
        }
        
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (char neighbor : graph.get(c)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0)
                    queue.offer(neighbor);
            }
        }
        for (int count : indegree.values()) {
            if (count != 0)
                return "";
        }
        return sb.toString();
    }
}


public class Solution {
    public static int N = 26;
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0];
        }
        
        // the dependence relationship N*N array
        // dependence[i][j] which mean i -> j,
        // 实际表征着一个邻接矩阵 matrix[i][j]  = true, means i -> j,存在一条边，从i指向j
        boolean[][] dependence = new boolean[N][N];
        
        // indegree for every letter, 计算每个字符的入度，什么叫每个字符的入度？ 
        // 如果 [wer, wet]出现在 词语表中，则意味着 r -> t, r在t的前面，因此t的入度+1
        int[] indegree = new int[N];
        
        //existence，将所有出现在words array里的字符标记存在
        boolean[] existence = new boolean[N];
        
        
        //构建邻接矩阵dependence，
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j< words[i].length(); j++) {
                //set existence
                existence[words[i].charAt(j) - 'a'] = true;//标记该字符存在
            }
            if (i >= 1) {
            	//check 由于给出的words array 按照字符顺序排列，所以我们可以根据 words[i - 1] 和words[i]算出 第一个不同字符之间的次序关系
                checkTwoStrings(words[i - 1], words[i], dependence, indegree);
            }
        }

        //此时已经拥有完整的邻接矩阵，每个点的入度表， 即可进行topological sort
        return toplogical_sort(dependence, indegree, existence);
    }
    
    public String toplogical_sort(boolean[][] dependence, int[] indegree, boolean[] existence) {
         //queue used to store those whose indegree = 0
         Queue<Integer> queue = new LinkedList<>();
         //result
         StringBuilder res = new StringBuilder();
         
         for (int i = 0; i < N; i++) {
             if (indegree[i] == 0 && existence[i]) {
                 queue.add(i);
             }
         }
         
         while (!queue.isEmpty()) {
             int numChar = queue.poll();
             res.append((char)(numChar + 'a'));
             for (int i = 0; i < N; i++) {
                 if (dependence[numChar][i] == true) {
                     if (--indegree[i] == 0) {
                         queue.add(i);
                     }
                 }
             }
         }
         
        //  while (!queue.isEmpty()) {
        //     int course = queue.poll();
        //     count++;
        //     for (int i = 0; i < numCourses; i++) {
        //         if (matrix[course][i] != 0) {
        //             if (--indegree[i] == 0) {
        //                 queue.offer(i);
        //             }
        //         }
        //     }
        // }
         
         //check cycle
         for (int i = 0; i < N; i++) {
             if (indegree[i] != 0) {
                 return "";
             }
         }
         return res.toString();
    }
    
    //check two adjencent strings, e.g. : "wer" and "wet", then 'r' is absolutely before 't'
    public void checkTwoStrings(String s1, String s2, boolean[][] dependence, int[] indegree) {
        int minLen = Math.min(s1.length(), s2.length());
        for (int i = 0; i < minLen; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2 && dependence[c1 - 'a'][c2 - 'a'] == false) {
                dependence[c1 - 'a'][c2 - 'a'] = true;
                indegree[c2 - 'a'] ++;
                break;
            }
        }
    }
}