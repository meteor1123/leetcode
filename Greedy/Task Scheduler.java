/*
    621. Task Scheduler

    Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

    However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

    You need to return the least number of intervals the CPU will take to finish all the given tasks.

    Example 1:
    Input: tasks = ["A","A","A","B","B","B"], n = 2
    Output: 8
    Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
    Note:
    The number of tasks is in the range [1, 10000].
    The integer n is in the range [0, 100].
*/


// Time complexity : O(n). Number of iterations will be equal to resultant time time
// Space complexity : O(1). queuequeue and temptemp size will not exceed O(26).
class Solution {
    class Task {
        public char letter;
        public int count;
        public Task(char letter, int count) {
            this.letter = letter;
            this.count = count;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0)
            return 0;
        
        HashMap<Character, Integer> map = new HashMap();
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Task> pq = new PriorityQueue<Task>(
            (a, b) -> a.count == b.count ? a.letter - b.letter : b.count - a.count
        );
 
        for (char key : map.keySet()) {
            pq.offer(new Task(key, map.get(key)));
        }
        
        int count = 0;
        Queue<Task> queue = new LinkedList();
        while (!pq.isEmpty()) {
            int k = n + 1; //every group should have n + 1, why? 1 2 3 ,1 2 3  if n = 2, k = 2 + 1 = 3
            queue.clear();
            while (k > 0 && !pq.isEmpty()) { // most frequency task
                Task cur = pq.poll();
                cur.count -= 1; // decrease frequency, meaning it got executed
                queue.offer(cur); // collect task to add back to queue
                k--;
                count++; //successfully executed task
            }
            
            for (Task task : queue) {
                if (task.count > 0)
                    pq.offer(task); // add valid tasks 
            }
            
            if (pq.isEmpty())
                break;
            count += k; // if k > 0, then it means we need to be idle
        }
        return count;
        
    }
}


//Solution2:  time: O(n), space: O(1)
/*
    First consider the most frequent characters, we can determine their relative positions first and use them as a frame to insert the remaining less frequent characters. Here is a proof by construction:

    Let F be the set of most frequent chars with frequency k.
    We can create k chunks, each chunk is identical and is a string consists of chars in F in a specific fixed order.
    Let the heads of these chunks to be H_i; then H_2 should be at least n chars away from H_1, and so on so forth; 
    then we insert the less frequent chars into the gaps between these chunks sequentially one by one ordered by frequency in a decreasing order and try to fill the k-1 gaps as full or evenly as possible each time you insert a character. 
    In summary, append the less frequent characters to the end of each chunk of the first k-1 chunks sequentially and round and round, then join the chunks and keep their heads’ relative distance from each other to be at least n.
*/
public class Solution {
    public int leastInterval(char[] tasks, int n) {

        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--;

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i);
    }
}


// FB follow-up
public class TaskScheduler {
    
    /*
     * 3 实现一个蜜汁TaskScheduler. 实现getTask的function。每个task都有prerequisite。
        所以把graph 建好 跑一次topologicla sort 每次getTask 就输出一个task这样? thanks
        其实我也没完全搞懂那题，大概是每次getTask就打印当前运行的task
        补充内容 (2017-5-24 07:11):
        task运行完会有一个callback。只有当那个callback被调用才能把task从那个运行列表里去掉。
     */
    Map<Task, List<Task>> map = new HashMap<>();
    Map<Task, Integer> indegree = new HashMap<>();
    
    Queue<Task> queue = new LinkedList<>();
    
    public TaskScheduler(Task[][] prereq) {
        for (Task[] p : prereq) {
            if (!map.containsKey(p[0])) {
                map.put(p[0], new ArrayList<Task>());
            }
            if (!map.containsKey(p[1])) {
                map.put(p[1], new ArrayList<Task>());
            }
            map.get(p[0]).add(p[1]);
            if (!indegree.containsKey(p[1])) {
                indegree.put(p[1], 1);
            } else {
                indegree.put(p[1], indegree.get(p[1]) + 1);
            }
            p[0].callback = this;
            p[1].callback = this;
        }
        // for entry in indegeree,  if val == 0, add key to queue
    }
    
    public void release(Task t) {
        List<Task> children = map.get(t);
        for (Task child : children) {
            int count = indegree.get(child);
            count--;
            if (count == 0) {
                queue.offer(child);
            } else if (count > 0) {
                indegree.put(child, count);
            }
        }
    }
    
    public Object[] getTask() {
        return queue.toArray();
    }
}


class Task {
    int id;
    TaskScheduler callback;
    
    public Task(int i) {
        id = i;
    }
    
    public void finish() {
        callback.release(this);
    }
}


// Follow up:
public class TaskCoolDown {
//  http://www.cnblogs.com/EdwardLiu/p/5120091.html
//  http://www.cnblogs.com/EdwardLiu/p/5120090.html
        
    /**
     * Task Cooldown 1, 不能改变task的顺序，求一共多少时间处理。
     */
    public String coolDown(String s, int k) {
        
        Map<Character, Integer> map = new HashMap<>(); //char -> next avalible time
        StringBuilder sb = new StringBuilder();
        int i = 0;
        
        while (i < s.length()) {
            char c = s.charAt(i);
            if (!map.containsKey(c) || sb.length() - map.get(c) > k) {
                map.put(c, sb.length());
                sb.append(c);
                i++;
            } else {
                sb.append('_');
            }
        }
        //index就是一共需要多少时间。
        coolDownLessSpace(s, k);
        System.out.print("Not Rearrange: ");
        return sb.toString();
    }
    /** 上面的解法用了 O(n)的空间，其实没有必要 **/
    public String coolDownLessSpace(String s, int k) {
        System.out.print("Not Rearrange: ");
        Queue<Character> waitList = new LinkedList<>();
        Set<Character> waitSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        
        LinkedHashSet<Character> hash = new LinkedHashSet<>();
        while (i < s.length()) {
            char c = s.charAt(i);
            
            if (waitSet.contains(c)) {
                sb.append("_");
                waitList.offer(null);
            } else {
                sb.append(c);
                waitList.offer(c);
                waitSet.add(c);
                i++;
            }
            
            if (waitList.size() > k) {
                Character next = waitList.poll();
                if (next != null) waitSet.remove(next);
            }
        }
        //index就是一共需要多少时间。
        System.out.println(sb.toString() + ", " + sb.length());
        return sb.toString();
    }
    
    /**
     * Task Cooldown 2, 可以改变task的顺序，求最少多少时间。
     * 621. Task Scheduler
     * 
     * 
     * 使用一个map来记录每个task出现的频率，放入max heap里面
     * 使用一个valid来记录每一个task下一次可以安排的时间
     * 使用一个waitlist来记录当前等待的list
     * 
     * 如果heap是空的，但是waitList不是空的，说明没有可以安排的task，把时间增加，直到waitList中有元素可以安排的，再移到heap里面。
     * 如果heap不是空的，说明有可以安排的task，安排task，然后把valid的时间更新，把task加入waitlist
     */
    public String coolDown2Heap(String s, int k) {
        System.out.print("Can Rearrange: ");
        Map<Character, Integer> map = new HashMap<>();
        Queue<Map.Entry<Character, Integer>> waitList = new LinkedList<>();
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(10, new Comparator<Map.Entry>(){
            public int compare(Map.Entry o1, Map.Entry o2) {
                return (Integer)o2.getValue() - (Integer)o1.getValue();
            }
        });
        
        for (char c: s.toCharArray()) {
            map.put(c, map.get(c) == null ? 1 : map.get(c) + 1);
        }
        pq.addAll(map.entrySet());
        
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (!pq.isEmpty() || count < s.length()) {
            if (!pq.isEmpty()) {
                Map.Entry<Character, Integer> curr = pq.poll();
                curr.setValue(curr.getValue()-1);
                sb.append(curr.getKey());
                waitList.offer(curr);
                count++;
            } else {
                sb.append('_');
                waitList.offer(null);
            }
            if (waitList.size() > k) {
                Map.Entry<Character, Integer> available = waitList.poll();
                if (available != null && available.getValue() > 0) {
                    pq.offer(available);
                }
            }
        }
        
        
        return sb.toString();
    }
    /**
     * 用了heap，开了好多数据结构，代码比较复杂，但是时间复杂度比较好
     * 不用的话也能做，时间 O(n^2), 逻辑比较清晰。
     */
    public String coolDown2NotHeap(String s, int k) {
        System.out.print("Can Rearrange: ");
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> valid = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (char c: s.toCharArray()) {
            map.put(c, map.get(c) == null ? 1 : map.get(c) + 1);
        }
        
        int time = 0;
        while (!map.isEmpty()) {
            char next = findValidMax(map, valid, time);
            sb.append(next);
            if (next == '_') {
                time++;
                continue;
            }
            if (map.get(next) > 1) {
                map.put(next, map.get(next) - 1);
            } else {
                map.remove(next);
            }
            valid.put(next, time+k+1);
            time++;
        }
        
        return sb.toString();
    }
    
    private char findValidMax(Map<Character, Integer> map, Map<Character, Integer> valid, int time) {
        int max = Integer.MIN_VALUE;
        char res = '_';
        for (char key : map.keySet()) {
            if (map.get(key) > max && (!valid.containsKey(key) ||valid.get(key) <= time)) {
                max = map.get(key);
                res = key;
            }
        }
        return res;
    }
    
    
    public int leastInterval(char[] tasks, int n) {
        List<int[]> taskMap = new ArrayList<>();
        for(int i = 0; i < tasks.length; i++) {
            char c = tasks[i];
            for(int j = 0; j < 26; j++) {
                if(taskMap.size() == 0 || (j == 25 && c != 'A' + 25)) {
                    taskMap.add(new int[] {c, 1});
                    break;
                }
                if(taskMap.size() > j && taskMap.get(j)[0] == c) {
                    taskMap.get(j)[1]++;
                    break;
                }
            }
        }
        PriorityQueue<int[]> q = new PriorityQueue<>(tasks.length, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] != o2[1]) return o2[1] - o1[1];
                return o1[0] - o2[0];
            }
        });

        q.addAll(taskMap);

        int count = 0;
        while(!q.isEmpty()) {
            int k = n+1;
            List<int[]> waitList = new ArrayList<>();
            while(k > 0 && !q.isEmpty()) {
                int[] top = q.poll();
                top[1]--;
                waitList.add(top);
                k--;
                count++;
            }

            for(int[] e : waitList) {
                if(e[1] > 0) q.add(e);
            }

            if(q.isEmpty()) break;
            count = count + k;
        }
        return count;
    }
