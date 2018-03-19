/*
    Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

    Only one letter can be changed at a time
    Each intermediate word must exist in the dictionary
    For example,

    Given:
        start = "hit"
        end = "cog"
        dict = ["hot","dot","dog","lot","log"]
    Return:
        [
            ["hit","hot","dot","dog","cog"],
            ["hit","hot","lot","log","cog"]
        ]
    Note:
        All words have the same length.
        All words contain only lowercase alphabetic characters.
    Tags:Array, Backtracking, BFS, String
*/


/*
    和wordladderI 的不同之处在于，需要返回所有可能的结果
    为什么搜索的时候保存前驱结点容易，而保存后继结点比较困难？
    answer：因为当我们到达当前节点是，我们总是知道前驱的，但是后继却还不知道，因此后继结点的维护是比较有难度
*/

//Solution1  prefer!
public class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        List<String> item = new ArrayList<String>();
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        if (wordList.size() == 0) {
            return res;
        }
        Queue<String> queue = new LinkedList<String>();
        Set<String> unvisited = new HashSet<String>(wordList);//保存所有未访问过的结点
        Set<String> visited = new HashSet<String>();//每一层上访问过的结点，每访问一层就清空
        queue.add(beginWord);
        unvisited.add(endWord);
        unvisited.remove(beginWord);
        int curNum = 1, nextNum = 0;
        while (!queue.isEmpty()) {
            String word = queue.poll();
            curNum--;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder sb = new StringBuilder(word);
                for (char c = 'a'; c <= 'z'; c++) {
                    sb.setCharAt(i, c);
                    String newWord = sb.toString();
                    if (unvisited.contains(newWord)) {
                        //假如未访问过该结点，则该节点入队列
                        if (visited.add(newWord)) {
                            nextNum++;
                            queue.add(newWord);
                        }
                        if (map.containsKey(newWord)) {
                            map.get(newWord).add(word);
                        } else {
                            ArrayList<String> temp = new ArrayList<String>();
                            temp.add(word);
                            map.put(newWord, temp);
                        }
                    }
                }
            }
            if (curNum == 0) {
                curNum = nextNum;
                nextNum = 0;
                unvisited.removeAll(visited);//将所有一层上访问过的结点都从未访问结点中清空
                visited.clear();
            }
        }
        helper(endWord, beginWord, res, item, map);
        return res;
    }
    //DFS 遍历hashmap，从尾部到头部开始构建最短path，from endWord -> startWord
    public void helper(String word, String start, List<List<String>> res, List<String> item, Map<String, ArrayList<String>> map) {
        //终止返回条件， word == start， 
        if (word.equals(start)) {
            item.add(0, start);
            res.add(new ArrayList<String>(item));
            item.remove(0);//回溯
            return;
        }
        item.add(0, word);//每个单词都从头插入，因为HashMap中 Key是后继词， Value是前驱。 hit-->hot, hit就是前驱，hot是后继
        if (map.get(word) != null) {//只要前驱不为空，递归遍历前驱的前驱的前驱。。。。
            for (String s : map.get(word)) {
                helper(s, start, res, item, map);
            }
        }
        item.remove(0);//作用在于回溯
    }
}



//Solution2 需要新建class
public class Solution {
    
    public class StringWithLevel {
        String str;
        int level;
        public StringWithLevel(String str, int level) {
            this.str = str;
            this.level = level;
        }
    }
    public ArrayList<ArrayList<String>> findLadders(String start, String end, Set<String> dict) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        
        //用HashSet 存放没访问过的word
        HashSet<String> unvisitedSet = new HashSet<String>();
        //put whole dictionary words
        unvisitedSet.addAll(dict);
        //put the starting word
        unvisitedSet.add(start);
        //put the ending word
        unvisitedSet.remove(end);
        
        //保存一个节点的所有前驱节点
        Map<String, List<String>> nextMap = new HashMap<String, List<String>>();
        //初始化
        for (String s : unvisitedSet)
            nextMap.put(s, new ArrayList<String>());
        //
        LinkedList<StringWithLevel> queue = new LinkedList<StringWithLevel>();

        //倒着走，从end --> start
        queue.add(new StringWithLevel(end, 0));//第一个进入的节点是0
        boolean found = false;
        int finalLevel = Integer.MAX_VALUE;
        int curLevel = 0;
        int preLevel = 0;

        //在cur层上访问过的节点集合
        HashSet<String> visitedCurLevel = new HashSet<String>();
        while (!queue.isEmpty()) {
            //队列poll出带层数的节点
            StringWithLevel cur = queue.poll();
            //取出该节点的字符串
            String curStr = cur.str;
            //取出该节点的层数
            curLevel = cur.level;
            //假如找到并且该节点层数大于最终层数 break
            if (found && curLevel > finalLevel)
                break;
            //当前层数大于上一层层数，
            if (curLevel > preLevel)
                unvisitedSet.removeAll(visitedCurLevel);

            //把当前层数赋值给之前的层数
            preLevel = curLevel;

            //转化成字符数组，
            char[] curStrCharArray = curStr.toCharArray();
            for (int i = 0; i < curStr.length(); ++i) {
                char originalChar = curStrCharArray[i];
                //设环卫false
                // boolean foundCurCycle = false;
                //使用a-z遍历每一个字符，与字典和final word进行匹配
                for (char c = 'a'; c <= 'z'; ++c) {
                    curStrCharArray[i] = c;
                    //替换一个字符后生成的新字符串newStr
                    String newStr = new String(curStrCharArray);
                    //假如 替换的字符不等于原单词的字符，并且未访问集合（字典）里含有新字符串newStr
                    if (c != originalChar && unvisitedSet.contains(newStr)) {
                        //nextMap 是存储单词的前驱结点的hashmap，nextMap = HashMap<word，ArrayList<word的前驱>>

                        /*
                            start:   "hit"
                            end  :   "cog"

                            from end to start:
                                    cog -> dog -> dot -> hot -> hit

                            from start to end:
                                    hit -> hot -> dot -> dog -> cog
                        */
                        //if curStr = cog , we make newStr = dog, 
                        //从end 到start来看，      cog是dog的前驱结点
                        //因此从start 到 end 来看， cog是dog的后继结点
                        //前驱结点易求，后继难求
                        nextMap.get(newStr).add(curStr);
                        if (newStr.equals(start)) {
                            found = true;
                            finalLevel = curLevel;
                            // foundCurCycle = true;
                            break;
                        }
                        //newStr是变换一个char之后产生的，加入visitedCurLevel代表在cur层上已访问过此节点
                        //加入成功则说明，原先的visitedCurLevel里没有该结点
                        if (visitedCurLevel.add(newStr)) 
                            //新建带有level的节点，由于是变化一个字符后产生的，所以层数等于 curLevel + 1,也就是下一层的word
                            queue.add(new StringWithLevel(newStr, curLevel + 1));
                    }
                    //假设有环，则break，对下一个字符的位置进行遍历查找(注释掉也能通过，因为根本进不来这里)
                    // if (foundCurCycle) {
                    //     break;
                    // }
                    // 一个字符遍历a-z的其中一个玩了之后，都要赋值回原先的字符
                    curStrCharArray[i] = originalChar;
                }//end of inner loop
            }//end of outer loop

        }//end of while loop 
        //假如找到从end - start 的一条路径，下面开始调用dfs，对含有level属性的结点进行 dfs遍历找出所有的满足路径
        if (found) {
            ArrayList<String> list = new ArrayList<String>();
            //从头结点开始
            list.add(start);
            getPaths(start, end, list, finalLevel + 1, nextMap, res);
        }
        return res;
    }
    
    private void getPaths(String cur, String end, ArrayList<String> list, int level, Map<String, List<String>> nextMap, 
                          ArrayList<ArrayList<String>> res) {
        //假如cur 等于end 这字典匹配成功，将该路径 list 加入res
        if (cur.equals(end)) {
            res.add(new ArrayList<String>(list));
        } else if (level > 0) {
             /*
                ["hit","hot","dot","dog","cog"],
                ["hit","hot","lot","log","cog"]

                hit是hot的parent，
            */
            ArrayList<String> parentSet = nextMap.get(cur);
            for (String parent : parentSet) {
                list.add(parent);
                getPaths(parent, end, list, level - 1, nextMap, res);
                list.remove(list.size() - 1);
            }
        }
    }
}