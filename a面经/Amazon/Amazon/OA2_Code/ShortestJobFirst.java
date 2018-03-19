import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestJobFirst {
	public float Solution(int[] req, int[] dur) {
		if (req == null || dur == null || req.length != dur.length)	return 0;
		int index = 0, length = req.length;
		int waitTime = 0, curTime = 0;
		PriorityQueue<process> pq = new PriorityQueue<process>(new Comparator<process>() {
			@Override
			public int compare(process p1, process p2) {
				if (p1.exeTime == p2.exeTime)
					return p1.arrTime - p2.arrTime;
				return p1.exeTime - p2.exeTime;
			}
		});
		while (!pq.isEmpty() || index < length) {
			if (!pq.isEmpty()) {
				process cur = pq.poll();
				waitTime += curTime - cur.arrTime;
				curTime += cur.exeTime;
				while (index < length && curTime >= req[index])
					pq.offer(new process(req[index], dur[index++]));
			} else {
				pq.offer(new process(req[index], dur[index]));
				curTime = req[index++];
			}
		}
		return (float) waitTime / length;
	}
}

//JefferyChou 
public double findAverage(int []request, int[]duration){
    if(request == null || duration == null || request.length == 0 || duration.length == 0) {
        return 0;
    }
    int n = request.length;
    int[] end = new int[n];
    int curTime = 0;
    for (int i = 0; i < n; i++){
        if (i == 0){
        	curTime = duration[0];
            end[0] = duration[0];
        } else {
            int minDuration = Integer.MAX_VALUE;
            int curProcess = 0;
            for (int j = 0; j < n; j++){
                if (end[j] != 0){
                	continue;
                }
                if (request[j] <= curTime) {
                    if (duration[j] < minDuration){
                        minDuration = duration[j];
                        curProcess = j;
                    }
                } else {
                    break;
                }
            }
            if (curProcess == 0){
                curProcess = i;
                curTime = request[curProcess];
            }
            curTime = curTime + duration[curProcess];
            end[curProcess] = curTime;
        }
    }
    int waitSum = 0;
    for(int i = 0; i < n; i++){ 
        waitSum += end[i] - request[i] - duration[i];
    }       
    return (double)waitSum / (double)n;
}


//aiuou
package AmazonOA;

import java.util.HashSet;
public class AverageWaiting {
    public AverageWaiting() {
            // TODO Auto-generated constructor stub
    }
    public double findAverage(int []request, int[]duration){
        if(request==null||duration==null||request.length==0||duration.length==0){
                return 0;
        }
        int n=request.length;
        int []end=new int[n];
		int curTime=0;
        for(int i=0;i<n;i++){
            if(i==0){
                curTime=duration[0];
                end[0]=duration[0];
            }else{
                int minDuration=Integer.MAX_VALUE;
                int curProcess=0;
                for(int j=0;j<n;j++){
                    if(end[j]!=0) continue;
                    if(request[j]<=curTime){
                        if(duration[j]<minDuration){
                            minDuration=duration[j];
                            curProcess=j;
                        }
                    }else{
						break;
                    }
                }
                if(curProcess==0){
                    curProcess=i;
                    curTime=request[curProcess];
                }
                curTime=curTime+duration[curProcess];
                end[curProcess]=curTime;
            }
        }
        int waitSum=0;
        for(int i=0;i<n;i++){
                waitSum+=end[i]-request[i]-duration[i];
        }
        
        return (double)waitSum/(double)n;
    }
}
