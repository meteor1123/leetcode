/*
	&   -  bitwise and
	|   -  bitwise or
	^   -  bitwise xor
	~   -  bitwise not
	<<  -  bitwise shift left
	>>  -  bitwise shift right
*/

// Bit Hack #1. Check if the integer is even or odd

	if ((x & 1) == 0) {
		x is even;
	} else {
		x is odd
	}

	//  43 & 1 == 1 is odd
		00101011
	&   00000001   (note: 1 is the same as 00000001)
	    --------
	    00000001

	//  98 & 1 == 0 is even
	    01100010
	&   00000001
	    --------
	    00000000


// Bit Hack #2. Test if the n-th bit is set.
	
	1         00000001    (same as 1<<0)
	1<<1      00000010
	1<<2      00000100
	1<<3      00001000
	1<<4      00010000
	1<<5      00100000
	1<<6      01000000
	1<<7      10000000

	//将1 左移n位就可以判断 x的第n位上的bit位是否等于1
	if (x & (1 << n)) {
		n-th bit is set
	} else {
		n-th bit is not set
	}

	//example1: 122 & (1 << 3), deos 122 have 3rd bit set?
		01111010 
	&	00001000
		--------
		00001000
	we see that the result is not 0, so yes, 122 has the 3rd bit set.

	//example2:
	    11011111      (-33 in binary)
	&   00100000      (1<<5)
    	--------
    	00000000
    result is 0, so the 5th bit is not set

// Bit Hack #3. Set the n-th bit.
    y = x | (1 << n)

   	Suppose we have value 120, and we wish to turn on the 2nd bit.
	    01111000    (120 in binary)
	|   00000100    (1<<2)
	    --------
	    01111100
	What about -120 and 6th bit?
	    10001000   (-120 in binary)
	|   01000000   (1<<6)
	    --------
	    11001000

// Bit Hack #4. Unset the n-th bit.
	y = x & ~(1<<n)

	~1        11111110  (same as ~(1<<0))
	~(1<<1)   11111101
	~(1<<2)   11111011
	~(1<<3)   11110111
	~(1<<4)   11101111
	~(1<<5)   11011111
	~(1<<6)   10111111
	~(1<<7)   01111111

	//example: unset 4th bit in 127:
	    01111111    (127 in binary)
	&   11101111    (~(1<<4))
	    --------
	    01101111

// Bit Hack #5. Toggle the n-th bit.

	y = x ^ (1<<n)

	Here is an example. Suppose you want to toggle 5th bit in value 01110101:

	    01110101
	^   00100000
	    --------
	    01010101
	What about the same value but 5th bit originally 0?

	    01010101
	^   00100000
	    --------
	    01110101

//Bit Hack #6. Turn off the rightmost 1 - bit
	y = x & (x - 1)

		01010111    (x)
	&   01010110    (x-1)
	    --------
	    01010110

	    01011000    (x)
	&   01010111    (x-1)
	    --------
	    01010000

	    10000000    (x = -128)
	&   01111111    (x-1 = 127 (with overflow))
	    --------
	    00000000

	    11111111    (x = all bits 1)
	&   11111110    (x-1)
	    --------
	    11111110

	    00000000    (x = no rightmost 1-bits)
	&   11111111    (x-1)
	    --------
	    00000000

	Why does it work?

	// If you look at the examples and think for a while, you'll realize that there are two possible scenarios:

	// 1. The value has the rightmost 1 bit. In this case subtracting one from it sets all the lower bits to one and changes that rightmost bit to 0 (so that if you add one now, 
	//    you get the original value back). This step has masked out the rightmost 1-bit and now AND-ing it with the original value zeroes that rightmost 1-bit out.
	// 2. The value has no rightmost 1 bit (all 0). In this case subtracting one underflows the value (as it's signed) and sets all bits to 1. AND-ing all zeroes with all ones produces 0.

//Bit Hack #7. Isolate the rightmost 1 - bit. 只保留右边第一个为1的地方为1 其他地方全部置0
	y = x & (-x)

	    10111100  (x)
	&   01000100  (-x)
	    --------
	    00000100

	    01110000  (x)
	&   10010000  (-x)
	    --------
	    00010000

	    00000001  (x)
	&   11111111  (-x)
	    --------
	    00000001

	    10000000  (x = -128)
	&   10000000  (-x = -128)
	    --------
	    10000000

	    11111111  (x = all bits one)
	&   00000001  (-x)
	    --------
	    00000001

	    00000000  (x = all bits 0, no rightmost 1-bit)
	&   00000000  (-x)
	    --------
	    00000000

//Bit Hack #8. Right propagate the rightmost 1-bit.右边第一个为1的地方往右全部为1，其他地方保持原样
	y = x | (x-1)

	10111100  (x)
|   10111011  (x-1)
    --------
    10111111

    01110111  (x)
|   01110110  (x-1)
    --------
    01110111

    00000001  (x)
|   00000000  (x-1)
    --------
    00000001

    10000000  (x = -128)
|   01111111  (x-1 = 127)
    --------
    11111111

    11111111  (x = -1)
|   11111110  (x-1 = -2)
    --------
    11111111

    00000000  (x)
|   11111111  (x-1)
    --------
    11111111

// Bit Hack #9. Isolate the rightmost 0-bit.

	    y = ~x & (x+1)

	    10111100  (x)
	    --------
	    01000011  (~x)
	&   10111101  (x+1)
	    --------
	    00000001

	    01110111  (x)
	    --------
	    10001000  (~x)
	&   01111000  (x+1)
	    --------
	    00001000

	    00000001  (x)
	    --------
	    11111110  (~x)
	&   00000010  (x+1)
	    --------
	    00000010

	    10000000  (x = -128)
	    --------
	    01111111  (~x)
	&   10000001  (x+1)
	    --------
	    00000001

	    11111111  (x = no rightmost 0-bit)
	    --------
	    00000000  (~x)
	&   00000000  (x+1)
	    --------
	    00000000

	    00000000  (x)
	    --------
	    11111111  (~x)
	&   00000001  (x+1)
	    --------
	    00000001
	    
// Bit Hack #10. Turn on the rightmost 0-bit. 
	    y = x | (x + 1)

		10111100  (x)
	|   10111101  (x+1)
	    --------
	    10111101

	    01110111  (x)
	|   01111000  (x+1)
	    --------
	    01111111

	    00000001  (x)
	|   00000010  (x+1)
	    --------
	    00000011

	    10000000  (x = -128)
	|   10000001  (x+1)
	    --------
	    10000001

	    11111111  (x = no rightmost 0-bit)
	|   00000000  (x+1)
	    --------
	    11111111

	    00000000  (x)
	|   00000001  (x+1)
	    --------
	    00000001
