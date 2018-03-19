/*
	535. Encode and Decode TinyURL

	Note: This is a companion problem to the System Design problem: Design TinyURL.
	TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

	Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
*/


//Solution1: use a global counter as key to store long URL
/*
	Performance Analysis
	 1. The range of URLs that can be decoded is limited by the range of \text{int}int.
   2. If excessively large number of URLs have to be encoded, after the range of \text{int}int is exceeded, integer overflow could lead to overwriting the previous URLs' encodings, 
      leading to the performance degradation.
   3. The length of the URL isn't necessarily shorter than the incoming \text{longURL}longURL. It is only dependent on the relative order in which the URLs are encoded.
   4. One problem with this method is that it is very easy to predict the next code generated, since the pattern can be detected by generating a few encoded URLs.
*/
public class Codec {
  HashMap<Integer, String> map = new HashMap();
  int counter = 0;

  // Encodes a URL to a shortened URL.
  public String encode(String longUrl) {
      map.put(counter, longUrl);
      return "http://tinyurl.com/" + counter++;
  }

  // Decodes a shortened URL to its original URL.
  public String decode(String shortUrl) {
      return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
  }
}


//Solution2: Variable-length Encoding
/*
	Performance Analysis
		1. The number of URLs that can be encoded is, again, dependent on the range of \text{int}int, since, the same countcount will be generated after overflow of integers.
    2. The length of the encoded URLs isn't necessarily short, but is to some extent dependent on the order in which the incoming \text{longURL}longURL's are encountered. 
       For example, the codes generated will have the lengths in the following order: 1(62 times), 2(62 times) and so on.
    3. The performance is quite good, since the same code will be repeated only after the integer overflow limit, which is quite large.
    4. In this case also, the next code generated could be predicted by the use of some calculations.
*/
public class Codec {
    String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    HashMap<String, String> map = new HashMap<>();
    int count = 1;

    public String getString() {
        int c = count;
        StringBuilder sb = new StringBuilder();
        while (c > 0) {
            c--;
            sb.append(chars.charAt(c % 62));
            c /= 62;
        }
        return sb.toString();
    }
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key = getString();
        map.put(key, longUrl);
        count++;
        return "http://tinyurl.com/" + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
}


/*
	Performance Analysis:

	1. The number of URLs that can be encoded is limited by the range of \text{int}int, since \text{hashCode}hashCode uses integer calculations.
  2. The average length of the encoded URL isn't directly related to the incoming \text{longURL}longURL length.
  3. The hashCode() doesn't generate unique codes for different string. This property of getting the same code for two different inputs is called collision. Thus, as the number of encoded URLs increases, the probability of collisions increases, which leads to failure.
  4. The following figure demonstrates the mapping of different objects to the same hashcode and the increasing probability of collisions with increasing number of objects.
  5. Thus, it isn't necessary that the collisions start occuring only after a certain number of strings have been encoded, but they could occur way before the limit is even near to the \text{int}int. This is similar to birthday paradox i.e. the probability of two people having the same birthday is nearly 50% if we consider only 23 people and 99.9% with just 70 people.
  6. Predicting the encoded URL isn't easy in this scheme.
*/
//Solution3: Use Java built-in Hash Code
public class Codec {
    HashMap<Integer, String> map = new HashMap<>();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        map.put(longUrl.hashCode(), longUrl);
        return "http://tinyurl.com/" + longUrl.hashCode();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
}

/*
	Performance Analysis:

	1. The number of URLs that can be encoded is limited by the range of \text{int}int.
	2. The average length of the codes generated is independent of the \text{longURL}longURL's length, since a random integer is used.
	3. The length of the URL isn't necessarily shorter than the incoming \text{longURL}longURL. It is only dependent on the relative order in which the URLs are encoded.
	4. Since a random number is used for coding, again, as in the previous case, the number of collisions could increase with the increasing number of input strings, leading to performance degradation.

	Determining the encoded URL isn't possible in this scheme, since we make use of random numbers.
*/

//Solution4: Using random number
public class Codec {
    Map<Integer, String> map = new HashMap<>();
    Random r = new Random();
    int key = r.nextInt(Integer.MAX_VALUE);

    public String encode(String longUrl) {
        while (map.containsKey(key)) {
            key = r.nextInt(Integer.MAX_VALUE);
        }
        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
}


//Solution5: Random fixed-length encoding
/*
	Performance Analysis

	1. The number of URLs that can be encoded is quite large in this case, nearly of the order (10+26*2)^6
	2. The length of the encoded URLs is fixed to 6 units, which is a significant reduction for very large URLs.
	3. The performance of this scheme is quite good, due to a very less probability of repeated same codes generated.
	4. We can increase the number of encodings possible as well, by increasing the length of the encoded strings. Thus, there exists a tradeoff between the length of the code and the number of encodings possible.

	Predicting the encoding isn't possible in this scheme since random numbers are used.
*/

public class Codec {
    String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    HashMap<String, String> map = new HashMap<>();
    Random rand = new Random();
    String key = getRand();

    public String getRand() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(alphabet.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    public String encode(String longUrl) {
        while (map.containsKey(key)) {
            key = getRand();
        }
        map.put(key, longUrl);
        return "http://tinyurl.com/" + key;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
}
