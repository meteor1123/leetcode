/*
		Comparator is capable if comparing objects based on different attributes. e.g. 2 men can be com-
	pared based on ‘name‘ or ‘age‘ etc. (this can not be done using comparable. )The method required to 
	implement is compare(). Now let’s use another way to compare those TV by size. The common use of Comparator 
	is sorting. Both Collections and Arrays classes provide a sort method which use a Comparator.

	A class that implements Comparator will be a Comparator for some other class. 
	1) It can be passed to a sort method, such as Collections.sort() or Arrays.sort(), 
	   to allow precise control over the sort order and 
	2) can also be used to control the order of certain data structures, 
	   such as sorted sets or sorted maps
*/

class HDTV {
	private int size;
	private String brand;
	public HDTV(int size, String brand) {
		this . size = size ;
		this.brand = brand;
	}
	public int getSize () {
		return size ;
	}
	public void setSize ( int size ) {
		this . size = size ; 
	}
	public String getBrand () {
		return brand ;
	}
	public void setBrand ( String brand ) {
		this.brand = brand;
	} 
}

class SizeComparator implements Comparator<HDTV> {
	@Override
	public int compare(HDTV tv1, HDTV tv2) {
		int tv1Size = tv1.getSize();
		int tv2Size = tv2.getSize();
		if (tv1Size > tv2.tv2Size) {
			return 1;
		} else if (tv1Size < tv2Size) {
			return -1;
		} else {
			return 0;
		}
	}
}

public class Solution {
	public static void main(String[] args) {
		HDTV tv1 = new HDTV(55, "apple");
		HDTV tv2 = new HDTV(66, "sony");
		HDTV tv3 = new HDTV(77, "xiaomi");
	}
	ArrayList<HDTV> al = new ArrayList<HDTV>();
	al.add(tv1);
	al.add(tv2);
	al.add(tv3);

	Collections.sort(al, new SizeComparator());
	for (HDTV tv : al) {
		System.out.println(tv.getBrand());
	}
	//reverse order
	// Comparator<Integer> comparator = Collections.reverseOrder(); 
	// Collections.sort (al, comparator);
	// System.out.println(al) ;
}