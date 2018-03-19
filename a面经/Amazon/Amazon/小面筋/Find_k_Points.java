package trytry;
import java.util.*;

public class Find_k_Points {
	
	public class CPoints{
		public int x;
		public int y; 
		
		CPoints(int xx, int yy){
			this.x = xx;
			this.y = yy;
		}
	}
	
	
	public CPoints[] findkMin(CPoints[] myList, int k){
		
		PriorityQueue<CPoints> pq = new PriorityQueue<CPoints>(myList.length, new MyComparator());
		CPoints[] result = new CPoints[k];
		for(int i = 0; i < myList.length; i++){
			pq.offer(myList[i]); 
		}
		for(int j = 0; j < k; j ++){
			result[j] = pq.poll();
		}
		
		
		return result;
		
	}
	private class MyComparator implements Comparator<CPoints>{ // you need CPoints
		
		public int compare(CPoints cp1, CPoints cp2){
			
			double result = (cp1.x * cp1.x + cp1.y * cp1.y) - (cp2.x * cp2.x + cp2.y * cp2.y);
			if(result > 0){
				return 1;
			} else if(result < 0){
				return -1;
			} else{
				return 0;
			}
		}

	
	}
	
//	private static Comparator<CPoints> myComp = new Comparator<CPoints>(){ // you need CPoints
//		
//		public int compare(CPoints cp1, CPoints cp2){
//			
//			double result = (cp1.x * cp1.x + cp1.y * cp1.y) - (cp2.x * cp2.x + cp2.y * cp2.y);
//			if(result > 0){
//				return 1;
//			} else if(result < 0){
//				return -1;
//			} else{
//				return 0;
//			}
//			
//		}
//
//		
//	};
//	
	

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CPoints[] cp = new CPoints[10];
		Find_k_Points f = new Find_k_Points();
		
		for(int i = 0; i < cp.length; i++){
			cp[i]= f.new CPoints(i, i);  // no enclosing class is accessible you will need the outer class instance
			
		}
		int findNum = 8;
		CPoints[]  result = new Find_k_Points().findkMin(cp, findNum);
		for(int i = 0; i < findNum ; i ++){
			System.out.println(result[i].x + " "+ result[i].y);
		}
		
	}

}


