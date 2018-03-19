/*
	721. Accounts Merge
	DescriptionHintsSubmissionsDiscussSolution
	Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

	Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

	After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

	Example 1:
	Input: 
	accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
	Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
	Explanation: 
	The first and third John's are the same person as they have the common email "johnsmith@mail.com".
	The second John and Mary are different people as none of their email addresses are used by other accounts.
	We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
	['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
	Note:

	The length of accounts will be in the range [1, 1000].
	The length of accounts[i] will be in the range [1, 10].
	The length of accounts[i][j] will be in the range [1, 30].
*/


// BFS or DFS, 思路： 建图， 把每一个list的邮箱当节点，将同一个list的email 都联通， 不同list的email如果同层的email邻居也在另外一个list，则这两个list为强连通图
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList();
        if (accounts == null || accounts.size() == 0)
            return res;
        Map<String, Set<String>> graph = new HashMap();
        
        // Build the graph
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                if (!graph.containsKey(account.get(i)))
                    graph.put(account.get(i), new HashSet());
                graph.get(account.get(i)).add(account.get(1));
                graph.get(account.get(1)).add(account.get(i));
            }
        }
        
        // traverse the graph, find out all the connected subgraph
        Set<String> visited = new HashSet();
        
        for (List<String> account : accounts) {
            // use every first email to iterate the current level email
            String firstEmail = account.get(1);
            if (!visited.contains(firstEmail)) {
                List<String> temp = new ArrayList();
                visited.add(firstEmail);
                bfs(graph, visited, firstEmail, temp); // or dfs(graph, visited, account.get(1), temp)
                // build level res
                Collections.sort(temp);
                temp.add(0, account.get(0));
                res.add(temp);
            }
        }
        return res;
    }
    
    // BFS
    public void bfs(Map<String, Set<String>> graph, Set<String> visited, String firstEmail, List<String> temp) {
        Queue<String> queue = new ArrayDeque();
        queue.add(firstEmail);
        while (!queue.isEmpty()) {
            String email = queue.poll();
            temp.add(email);
            for (String nextEmail : graph.get(email)) {
                if (!visited.contains(nextEmail)) {
                    queue.add(nextEmail);
                    visited.add(nextEmail);
                }
            }
        }
    }
    
    //DFS
    public void dfs(Map<String,Set<String>> graph, Set<String> visited, String firstEmail, List<String> temp){
        temp.add(firstEmail);
        for(String nextEmail : graph.get(firstEmail)){
            if(!visited.contains(nextEmail)){
                dfs(graph, visited, nextEmail,temp);
            }
        }
    }
}


// Prefer: Union Find
/*
	a b c // now b, c have parent a
	d e f // now e, f have parent d
	g a d // now abc, def all merged to group g

	parents populated after parsing 1st account: a b c
	a->a
	b->a
	c->a

	parents populated after parsing 2nd account: d e f
	d->d
	e->d
	f->d

	parents populated after parsing 3rd account: g a d
	g->g
	a->g
	d->g
*/

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> owner = new HashMap();
        Map<String, String> parents = new HashMap();
        Map<String, TreeSet<String>> unions = new HashMap();
        
        //建union find初始map
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                parents.put(account.get(i), account.get(i));
                owner.put(account.get(i), account.get(0));
            }
        }
        
        //将每一个account list 以 第一个email地址为parent开始设parent
        for (List<String> account : accounts) {
            String parent = find(account.get(1), parents);
            for (int i = 2; i < account.size(); i++) {
                parents.put(find(account.get(i), parents), parent);
            }
        }
        
        // union 同一个parent的 account
        for (List<String> account : accounts) {
            String parent = find(account.get(1), parents);
            if (!unions.containsKey(parent))
                unions.put(parent, new TreeSet());
            for (int i = 1; i < account.size(); i++)
                unions.get(parent).add(account.get(i));
        }
        
        // 将union后的list根据owner 加入res
        List<List<String>> res = new ArrayList();
        for (String parent : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(parent));
            emails.add(0, owner.get(parent));
            res.add(emails);
        }
        return res;
    }
    
    private String find(String s, Map<String, String> parents) {
        return parents.get(s) == s ? s : find(parents.get(s), parents);
    }
}