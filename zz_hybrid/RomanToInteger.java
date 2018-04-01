package LeetCode;

public class RomanToInteger {
	public int romanToInt(String roman) {
		char[] ch = roman.toCharArray();
		int res = 0;
		if (roman.indexOf("IV") != -1) res -= 2;
		if (roman.indexOf("VX") != -1) res -= 10;
		if (roman.indexOf("XL") != -1) res -= 20;
		if (roman.indexOf("LC") != -1) res -= 100;
		if (roman.indexOf("CD") != -1) res -= 200;
		if (roman.indexOf("DM") != -1) res -= 1000;
		
		for (int i = ch.length - 1; i >= 0; i--) {
			if (ch[i] == 'I') res += 1;
			if (ch[i] == 'V') res += 5;
			if (ch[i] == 'X') res += 10;
			if (ch[i] == 'L') res += 50;
			if (ch[i] == 'C') res += 100;
			if (ch[i] == 'D') res += 500;
			if (ch[i] == 'M') res += 1000;
		}
		return res;
	}
}
