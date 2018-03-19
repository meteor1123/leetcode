package OA2;
import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
	public static float Solution(int[] arrArr, int[] exeArr, int q) {
		if (arrArr == null || exeArr == null || arrArr.length != exeArr.length) {
			return 0;
		}
		int len = arrArr.length;
		Queue<Process> queue = new LinkedList<>();
		int curTime = 0;
		int waitTime = 0;
		int index = 0;
		while (!queue.isEmpty() || index < len) {
			if (!queue.isEmpty()) {
				Process curProcess = queue.poll();
				waitTime += curTime - curProcess.arrTime;
				curTime += Math.min(curProcess.exeTime, q);
				while (index < len && arrArr[index] <= curTime) {
					queue.offer(new Process(arrArr[index], exeArr[index]));
					if (curProcess.exeTime > q) {
						queue.offer(new Process(curTime, curProcess.exeTime - q));
					}
					index++;
				}
			} else {
				queue.offer(new Process(arrArr[index], exeArr[index]));
				curTime = arrArr[index++];
			}
		}
		return (float)waitTime / len;
		
	}
	
	class process {
        int arrTime;
        int exeTime;
        process(int arr, int exe) {
                arrTime = arr;
                exeTime = exe;
        }
	}

//	public float Solution(int[] Atime, int[] Etime, int q) {
//		if (Atime == null || Etime == null || Atime.length != Etime.length)	return 0;
//		int length = Atime.length;
//		Queue<process> queue = new LinkedList<process>();
//		int curTime = 0, waitTime = 0;
//		int index = 0;
//		while (!queue.isEmpty() || index < length) {
//			if (!queue.isEmpty()) {
//				process cur = queue.poll();
//				waitTime += curTime - cur.arrTime;
//				curTime += Math.min(cur.exeTime, q);
//				for (; index < length && Atime[index] <= curTime; index++)
//					queue.offer(new process(Atime[index], Etime[index]));
//				if (cur.exeTime > q)
//					queue.offer(new process(curTime, cur.exeTime - q));
//			}
//			else {
//				queue.offer(new process(Atime[index], Etime[index]));
//				curTime = Atime[index++];
//			}
//		}
//		return (float) waitTime / length;
//	}
	
	//Solution2
	public static double roundRobin(int[] arrive, int[] excute, int q) {
		LinkedList<Process> queue = new LinkedList<>();
		int curTime = 0;
		int waitTime = 0;
		int nextProIdx = 0;
		while (!queue.isEmpty() || nextProIdx < arrive.length) {
			if (!queue.isEmpty()) {
				Process cur = queue.poll();
				waitTime += curTime - cur.arrTime;
				curTime += Math.min(cur.exeTime, q);
				for (int i = nextProIdx; i < arrive.length; i++) {
					if (arrive[i] <= curTime) {
						queue.offer(new Process(arrive[i], excute[i]));
						nextProIdx = i + 1;
					} else {
						break;
					}
				}
				if (cur.exeTime > q) {
					queue.offer(new Process(curTime, cur.exeTime - q));
				}
			} else {
				queue.offer(new Process(arrive[nextProIdx], excute[nextProIdx]));
				curTime = arrive[nextProIdx++];
			}
		}
		return (double)waitTime / arrive.length;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arrArr = {0, 1, 4};
		int[] exeArr = {5, 2, 3};
		int q = 3;
		System.out.println(roundRobin(arrArr, exeArr, 3));
	}

}

