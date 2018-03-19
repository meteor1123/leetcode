package OA1;

public class RectangleOverlap {
	public boolean check(Retangle first, Retangle second) {
	    if (first == null || second == null) {
             return false;
	    } 
	    int A = first.topLeft.x;
	    int B = first.topLeft.y;
	    int C = first.bottomRight.x;
	    int D = first.bottomRight.y;
	    
	    int E = second.topLeft.x;
	    int F = second.topLeft.y;
	    int G = second.bottomRight.x;
	    int H = second.bottomRight.y;
	    if(C <= E || G <= A || D >= F || H >= B){
	            return false;
	    }
	    return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

class Retangle {
	public TopLeft topLeft;
	public BottomRight bottomRight;
	public Retangle() {
		this.topLeft = new TopLeft();
		this.bottomRight = new BottomRight();
	}
	public Retangle(int a, int b, int c, int d) {
		this.topLeft = new TopLeft(a, b);
		this.bottomRight = new BottomRight(c, d);
	}
	class TopLeft {
		public int x;
		public int y;
		public TopLeft(){};
		public TopLeft(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	class BottomRight {
		public int x;
		public int y;
		public BottomRight(){};
		public BottomRight(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}