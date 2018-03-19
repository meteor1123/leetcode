/*
	Read N Characters Given Read4
	The API: int read4(char *buf) reads 4 characters at a time from a file.
	The return value is the actual number of characters read. 
	For example, it returns 3 if there is only 3 characters left in the file.

	By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

	Note:
	The read function will only be called once for each test case.
*/




/*
	首先read4是一个读文件的函数，只能读4个char。
	char [] buffer = new char[4]
	int size = read4(buffer)
	这里read4将空buffer改成了file 4 char.
	然后题目的目的是： 用read4,构成read函数，读n个字符，然后要将
	read(char[] buf, int n)里面的buf也改了。也就是说，要用buffer赋值buf.而且只能读n个char。

	read 要返回读了多少个char.
	题目其实在考两种极端情况：
*/

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); 
*/


/*
	这个题是要做什么呢？

	首先我们有一个read4()函数，每次可以读取4个char，我们要用这个函数实现读取n个char放到buf字符数组里
	所以应该怎么做？
				1）每次用临时char数组buffer通过read4读取4个char，再转存到buf数组里.
	会出现什么问题？
				1. 文件最后只剩下< 4的时候，也就是需要读的char字数 > 文件本身有的char数目，比如：文件有23个字符，n = 50. 最后读到<4的时候，read下一次就不读了。
					然后赋值的时候，只赋值最后这<4的char 给buf。

				2. 文件char数目 > n,比如：文件有50个char， n = 23, 只要读23个char。read4会读满4个char，但是最后我们只需要这3个char。所以最后赋值的时候，只需要最后3个。

	关键在于如何设置每次读取的最小长度：
				len = Math.min(n-readbytes, size);
*/
//Solution1 
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
   
    public int read(char[] buf, int n) {
         char[] buffer = new char[4];
         boolean endOfFile = false;
         int readBytes = 0;
         
         while (readBytes < n && !endOfFile) {
             int currReadBytes = read4(buffer);
             if (currReadBytes != 4) {
                 endOfFile = true;
             }
             
             int length = Math.min(n - readBytes, currReadBytes);
             for (int i = 0; i < length; i++) {
                 buf[readBytes + i] = buffer[i];
             }
             readBytes += length;
         }
         return readBytes;
    }
}


//Solution2
public class Solution extends Reader4 {
	public int read(char[] buf, int n) {
		//to store 4 char to buf 
		char[] buffer = new char[4];
		//indicate the size of buffer is less than 4 or
		boolean lessthan4 = false;
		int Readbyte = 0;
		int bytes = 0;

		while (!lessthan4 && Readbyte < n) {
			int size = read4(buffer);
			if (size < 4) {
				lessthan4 = true;
			}

			bytes = Math.min(n - Readbyte, size);
			for (int i = 0; i < bytes; i++) {
				buf[Readbyte + i] = buffer[i];
			}
			Readbyte += bytes;
		}
		return Readbyte;
	}
}

// Solution3 prefer
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        char[] temp = new char[4];
        int index = 0;
        while (true) {
            int count = read4(temp);
            count = Math.min(count, n - index);
            for (int i = 0; i < count; i++) {
                buf[index++] = temp[i];
            }
            if (index == n || count < 4)
                return index;
        }
    }
}
