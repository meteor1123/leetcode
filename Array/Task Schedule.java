public int execTime(int[] task, int N) {
    if(task==null || task.length==0)
        return 0;
    //use a hashmap to store the task and the cosume time
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    //res is the waiiting time
    int res = 0;
    int i =0;
    while(i < task.length) {
        if(!map.containsKey(task[i])) {
            map.put(task[i], i + res);
            i++;
        } else {
            //get the task's time
            int index = map.get(task[i]);
            //check i + res whether is larger than N + index
            if(i + res - index <= N) {
                res++;
            } else {
                map.put(task[i], i + res);
                i++;
            }
        }
    }
    return i + res;
}