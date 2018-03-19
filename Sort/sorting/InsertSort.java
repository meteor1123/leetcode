/*
	三、插入排序
	算法思想：
    1〉从第一个元素开始，该元素可以认为已经被排序
    2〉取出第一个未排序元素存放在临时变量temp中，在已经排序的元素序列中从后往前扫描，逐一比较
    3〉如果temp小于已排序元素，将该元素移到下个位置
    4〉重复步骤3〉，直到找到已排序的元素小于或者等于
*/
public class InsertSort {
	public static void insertSort(int[] arr) {
		//升序排列
		for (int i = 1; i < arr.length; i++) {
			//arr[i]是要插入的值
			int insertVal = arr[i];
			//index是往前要比较的值
			int index = i - 1;

			//如果index >= 0 并且 arr[index] > insetVal, 意味着 inserVal可以一直往前走，此时先不插入inserVal
			//而是将比insertVal大的值往后放,inde
			while (index >= 0 && arr[index] > insertVal) {
				arr[index + 1] = arr[index];
				index--;
			}
			arr[index + 1] = insertVal;
		}
	}
}

