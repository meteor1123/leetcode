package OA2;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestJobFirst {
	public static float Solution(int[] req, int[] dur) {
		if (req == null || dur == null || req.length != dur.length)	
			return 0;
		int index = 0;
		int length = req.length;
		int waitTime = 0;
		int curTime = 0;
		PriorityQueue<Process> pq = new PriorityQueue<Process>(req.length, new Comparator<Process>() {
			@Override
			public int compare(Process p1, Process p2) {
				if (p1.exeTime == p2.exeTime)
					return p1.arrTime - p2.arrTime;
				return p1.exeTime - p2.exeTime;
			}
		});
		while (!pq.isEmpty() || index < length) {
			if (!pq.isEmpty()) {
				Process cur = pq.poll();
				waitTime += curTime - cur.arrTime;
				curTime += cur.exeTime;
				while (index < length && curTime >= req[index])
					pq.offer(new Process(req[index], dur[index++]));
			} else {
				pq.offer(new Process(req[index], dur[index]));
				curTime = req[index++];
			}
		}
		return (float) waitTime / length;
	}
//JefferyChou
//	public static double findAverage(int []request, int[]duration){
//	    if(request == null || duration == null || request.length == 0 || duration.length == 0) {
//	        return 0;
//	    }
//	    int n = request.length;
//	    int[] end = new int[n];
//	    int curTime = 0;
//	    for (int i = 0; i < n; i++){
//	        if (i == 0){
//	        	curTime = duration[0];
//	            end[0] = duration[0];
//	        } else {
//	            int minDuration = Integer.MAX_VALUE;
//	            int curProcess = 0;
//	            for (int j = 0; j < n; j++){
//	                if (end[j] != 0){
//	                	continue;
//	                }
//	                if (request[j] <= curTime) {
//	                    if (duration[j] < minDuration){
//	                        minDuration = duration[j];
//	                        curProcess = j;
//	                    }
//	                } else {
//	                    break;
//	                }
//	            }
//	            if (curProcess == 0){
//	                curProcess = i;
//	                curTime = request[curProcess];
//	            }
//	            curTime = curTime + duration[curProcess];
//	            end[curProcess] = curTime;
//	        }
//	    }
//	    int waitSum = 0;
//	    for(int i = 0; i < n; i++){ 
//	        waitSum += end[i] - request[i] - duration[i];
//	    }       
//	    return (double)waitSum / (double)n;
//	}
	//aiuou
//	public static double findAverage(int []request, int[]duration){
//        if(request==null||duration==null||request.length==0||duration.length==0){
//                return 0;
//        }
//        int n=request.length;
//        int []end=new int[n];
//		int curTime=0;
//        for(int i=0;i<n;i++){
//                if(i==0){
//                        curTime=duration[0];
//                        end[0]=duration[0];
//                }else{
//                        int minDuration=Integer.MAX_VALUE;
//                        int curProcess=0;
//                        for(int j=0;j<n;j++){
//                                if(end[j]!=0) continue;
//                                if(request[j]<=curTime){
//                                        if(duration[j]<minDuration){
//                                                minDuration=duration[j];
//                                                curProcess=j;
//                                        }
//                                }else{
//									break;
//                                }
//                        }
//                        if(curProcess==0){
//                                curProcess=i;
//                                curTime=request[curProcess];
//                        }
//                        curTime=curTime+duration[curProcess];
//                        end[curProcess]=curTime;
//                }
//        }
//        int waitSum=0;
//        for(int i=0;i<n;i++){
//                waitSum+=end[i]-request[i]-duration[i];
//        }
//        
//        return (double)waitSum/(double)n;
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] req = {0, 2, 4, 5};
		int[] dur = {7, 4, 1, 4};
//		int[] req = {0, 1, 3, 9};
//		int[] dur = {2, 1, 7, 5};
//		int[] req = {0, 0};
//		int[] dur = {2, 2};

		System.out.println(Solution(req, dur));
	}
}
//
//class process {
//	int arrTime;
//	int exeTime;
//	public process(int arr, int exe) {
//		arrTime = arr;
//		exeTime = exe;
//	}
//}
