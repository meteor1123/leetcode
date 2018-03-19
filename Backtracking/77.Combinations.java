/*
	Combinations
	Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	For example,
	If n = 4 and k = 2, a solution is:
	[
  		[2,4],
  		[3,4],
  		[2,3],
  		[1,2],
  		[1,3],
  		[1,4],
	]
	Tags: Backtracking
*/

 /*
        n: n = 4 代表1,2,3,4
        k: k = 2 代表由1..n 个数种选取k(2)个数组合，必须小到大排列。
 */


/*

*/
    //Recursive
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> item = new ArrayList<Integer>();
        if (n < k)
            return res;
        dfs(n, k ,res, item, 1);
        return res;
    }
    
    public void dfs(int n, int k, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> item, int start) {
        // one possible combinition constructured  
        //由item的size == k 控制返回条件
        if (item.size() == k) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        
        //不是数组，因此1--n 不是0--n-1
        // try each possibility number in current position  
        for (int i = start; i <= n; i++) {
            item.add(i);
            // the new start should be after the next number after i
            dfs(n, k, res, item, i + 1);// after selecting number for current position, process next position
            item.remove(item.size() - 1);// clear the current position to try next possible number 
        }
    }

    //Iterative1
    /*
        The idea is to iteratively generate combinations for all lengths from 1 to k. 
        We start with a list of all numbers <= n as combinations for k == 1.
        When we have all combinations of length k-1, we can get the new ones for a length k with trying to add to each one all          
        elements that are <= n and greater than the last element of a current combination.
    */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0 || n == 0 || k > n) {
            return res;
        }
        
        //initially,we use 1 to n, to intialize every new list,and store to the res
        for (int i = 1; i <= n; i++) {
            res.add(Arrays.asList(i)); //asList的作用是 将i作为一个链表存储
        }
        
        //start from 2,cause if k equals 1, we just return the res ArrayList
        //cause that's already store the valur from 1 to n

        //最外层循环由k去控制，如果 k = 1,跳过直接返回res，
        //这样的好处在于方便控制item的size
        for (int i = 2; i <= k; i++) {
             List<List<Integer>> tempRes = new ArrayList<>();
             //遍历所有的 n， 根据当前item最后一位数的大小判断适不适合将n放进去，
             //比如 {（1) (2) (3) } -> j = 2  (1, 2) 
             //                       j = 3  (1, 3), (2, 3) 
             //                       j = 4  (1, 4), (2, 4), (3, 4)
             //                       此时tempRes = { (1, 2), (1, 3), (2, 3), (1, 4), (2, 4), (3, 4)},再赋值给res
             for (int j = i; j <= n; j++) {
                 for (List<Integer> item : res) {
                     if (item.get(item.size() - 1) < j) {
                         List<Integer> newItem = new ArrayList<>(item);
                         newItem.add(j);
                         tempRes.add(newItem);
                     }
                 }
             }
             res = tempRes;
         }
         return res;
    }

    //Iterative2 by use queue
    /*
      queue : 4 - 3 - 2 - 1
              (1,2) - 4 - 3 - 2
              (1,3) - (1,2) - 4 - 3 -2
              (1,4) - (1,3) - (1,2) - 4 - 3 - 2
              (2,3) - (1,4) - (1,3) - (1,2) - 4 - 3.......
              (3,4) - (2,4) - (2,3) - (1,4) - (1,3) - (1,2) 

    */

    public LinkedList<LinkedList<Integer>> combine(int n, int k) {
        Deque<LinkedList<Integer>> queue = new LinkedList<LinkedList<Integer>>();
        LinkedList<LinkedList<Integer>> res = new LinkedList<LinkedList<Integer>>();
        for (int i = 1; i <= n; i++) {
            LinkedList<Integer> list = new LinkedList<Integer>();
            list.add(i);
            queue.add(list);
        }
        
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = queue.pollFirst();
            if (list.size() == k) {
                res.add(list);
            } else {
                for (int i = list.get(list.size() - 1) + 1; i <= n; i++) {
                    LinkedList<Integer> next_list = new LinkedList<Integer>();
                    next_list.addAll(list);
                    next_list.add(i);
                    queue.addLast(next_list);
                }
            }
        }
        return res;
    }
        // public List<List<Integer>> combine(int n, int k) {
    //     Deque<List<Integer>> queue = new LinkedList<List<Integer>>();
    //     List<List<Integer>> res = new LinkedList<List<Integer>>();
    //     for (int i = 1; i <= n; i++) {
    //         List<Integer> list = new LinkedList<Integer>();
    //         list.add(i);
    //         queue.add(list);
    //     }
        
    //     while (!queue.isEmpty()) {
    //         List<Integer> list = queue.pollFirst();
    //         if (list.size() == k) {
    //             res.add(list);
    //         } else {
    //             for (int i = list.get(list.size() - 1) + 1; i <= n; i++) {
    //                 List<Integer> next_list = new LinkedList<Integer>();
    //                 next_list.addAll(list);
    //                 next_list.add(i);
    //                 queue.addLast(next_list);
    //             }
    //         }
    //     }
    //     return res;
    // }