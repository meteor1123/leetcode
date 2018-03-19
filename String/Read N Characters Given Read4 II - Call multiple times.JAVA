/*
	The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.
*/

/*
	思路：这题跟上题不一样的地方在于：multiple times means: read(buf, 51) then read(buf,2);
上次read的buffer还没有用完，需要用变量存储起来，当call 2的时候，拿出来用。
buffer–  An array of size 4 use to store data returned by read4 temporarily. 
		 If the characters were read into the buffer and were not used partially, 
		 they will be used in the next call.

offset–  Use to keep track of the offset index where the data begins in the next read call. 
		 The buffer could be read partially (due to constraints of reading up to n bytes) 
		 and therefore leaving some data behind.

bufsize– The real buffer size that stores the actual data. If bufsize> 0, 
		 that means there is partial data left in buffer from the last read call 
		 and we should consume it before calling read4 again. 
		 On the other hand, if bufsize == 0, it means there is no data left in buffer. 
*/

    //                 |<--bytes-->|   
    // |____________________________________|
    // |<---offset---> |<------bufsize------>


public class Solution extends Reader4 {
	// notice these three are  Global variable that can be record the offset 
	// and the ramaning size
	private char[] buffer = new char[4];
	int offset = 0;//last time ,the buffer used how many position
	int bufsize = 0;//the buffer - offset = bufsize, remaning how many position to store the next char

	public int read(char[] buf, int n) {
		int Readbytes = 0;// record the total char had been read
		boolean lessthan4 = false; 
		int bytes = 0; //every time read bytes char, so Readbytes += bytes

		while (!lessthan4 && Readbytes < n) {
			//if bufsize == 0 means, there is no remaing char ,so we need to give new space
			if (bufsize == 0) {
				bufsize = read4(buffer);
				lessthan4 = bufsize < 4;
			}
			
			bytes = Math.min(n - Readbytes, bufsize);
			for (int i = 0; i < bytes; i++) {
				buf[Readbytes + i] = buffer[offset + i];
			}
			offset = (offset + bytes) % 4;
			bufsize -= bytes;
			Readbytes += bytes;
		}
		return Readbytes;
	}


}

// Solution2: prefer
public class Solution extends Reader4 {
    private int count = 0; //最多等于4 记录每次从read4 读取的字节数量
    private int pointer = 0; // 指向从read4读到的字节的位 (0 ~ 4)
    private char[] temp = new char[4];
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int index = 0;
        while (index < n) {
            if (pointer == 0) //初始化
                count = read4(temp);
            if (count == 0) // read4已经读不到东西了
                break;
            while (index < n && pointer < count) // 读到index要比所需的n小 && pointer必须要比4小
                buf[index++] = temp[pointer++];
            if (pointer == count)
                pointer = 0;
        }
        return index;
    }
}



