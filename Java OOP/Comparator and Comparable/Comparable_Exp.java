/*
	Comparable is implemented by a class in order to be able to comparing object of itself with some other objects. 
	The class itself must implement the interface in order to be able to compare its instance(s). 
	The method required for implementation is compareTo(). Here is an example to show the usage:
*/
class HDTV implements Comparable<HDTV> {
	private int size;
	private String brand;
	public HDTV (int size, String brand) {
		this.size = size;
		this.brand = brand;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getSize() {
		return this.size;
	}
	public String getBrand() {
		return this.brand;
	}
	@Override
	public int compareTo(HDTV anotherTv) {
		if (this.getSize() > anotherTv.getSize()) {
			return 1;
		} else if (this.getSize() < anotherTv.getSize()) {
			return -1;
		} return 0;
	}
}

public class Solution {
	public static void main(String[] args) {
		HDTV tv1 = new HDTV(55, "Apple");
		HDTV tv2 = new HDTV(66, "Sony");
		if (tv1.compareTo(tv2) > 0) {
			System.out.println(tv1.getBrand() + " is better");
		} else if (tv1.compareTo < 0) {
			System.out.println(tv2.getBrand() + " is better");
		} else {
			System.out.println("As well as good");
		}
	}
}