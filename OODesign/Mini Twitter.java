/*
	Mini Twitter
	Implement a simple twitter. Support the following method:

	postTweet(user_id, tweet_text). Post a tweet.

	getTimeline(user_id). 
		Get the given user's most recently 10 tweets posted by himself, order by timestamp from most recent to least recent.
	
	getNewsFeed(user_id). 
		Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). Order by timestamp from most recent to least recent.
	
	follow(from_user_id, to_user_id). from_user_id followed to_user_id.
	
	unfollow(from_user_id, to_user_id). from_user_id unfollowed to to_user_id.

	Example
		postTweet(1, "LintCode is Good!!!")
		>> 1

		getNewsFeed(1)
		>> [1]

		getTimeline(1)
		>> [1]

		follow(2, 1)
		getNewsFeed(2)
		>> [1]

		unfollow(2, 1)
		getNewsFeed(2)
		>> []
 */

/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */
public class MiniTwitter {
    class Node {
        public int order;
        public Tweet tweet;
        public Node(int order, Tweet tweet) {
            this.order = order;
            this.tweet = tweet;
        }
    }
    
    
    //Order大的 在前面
    class SortByOrder implements Comparator {
        public int compare(Object obj1, Object obj2) {
            Node node1 = (Node)obj1;
            Node node2 = (Node)obj2;
            return node2.order - node1.order;
        }
    }
    
    private Map<Integer, Map<Integer, Boolean>> friends;
    private Map<Integer, List<Node>> users_tweets;
    private int order;
    
    public List<Node> getLastTen(List<Node> temp) {
        int last = 10;
        if (temp.size() < 10) {
            last = temp.size();
        }
        return temp.subList(temp.size() - last, temp.size());
    }
    
    public List<Node> getFirstTen(List<Node> temp) {
        int last = 10;
        if (temp.size() < 10) {
            last = temp.size();
        }
        return temp.subList(0, last);
    }
    
    public MiniTwitter() {
        // initialize your data structure here.
        this.friends = new HashMap<Integer, Map<Integer, Boolean>>();
        this.users_tweets = new HashMap<Integer, List<Node>>();
        this.order = 0;
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        //  Write your code here
        Tweet tweet = Tweet.create(user_id, tweet_text);
        if (!users_tweets.containsKey(user_id)) {
            users_tweets.put(user_id, new ArrayList<Node>());
        }
        order += 1;
        users_tweets.get(user_id).add(new Node(order, tweet));
        return tweet;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // Write your code here
        List<Node> temp = new ArrayList<>();
        //直接getLastTen 就是user最新的10条，因为post的时候按顺序add到ArrayList里
        if (users_tweets.containsKey(user_id)) {
            temp.addAll(getLastTen(users_tweets.get(user_id)));
        }
        
        if (friends.containsKey(user_id)) {
            for (Map.Entry<Integer, Boolean> entry : friends.get(user_id).entrySet()) {
                int user = entry.getKey();
                if (users_tweets.containsKey(user)) {
                    temp.addAll(getLastTen(users_tweets.get(user)));
                }
            }
        }
        
        Collections.sort(temp, new SortByOrder());
        List<Tweet> res = new ArrayList<>();
        temp = getFirstTen(temp);
        for (Node node : temp) {
            res.add(node.tweet);
        }
        return res;
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet> getTimeline(int user_id) {
        // Write your code here
        List<Node> temp = new ArrayList<>();
        if (users_tweets.containsKey(user_id)) {
            temp.addAll(getLastTen(users_tweets.get(user_id)));
        }
        Collections.sort(temp, new SortByOrder());
        List<Tweet> res = new ArrayList<>();
        temp = getFirstTen(temp);
        for (Node node : temp) {
            res.add(node.tweet);
        }
        return res;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        // Write your code here
        if (!friends.containsKey(from_user_id)) {
            friends.put(from_user_id, new HashMap<Integer, Boolean>());
        }
        friends.get(from_user_id).put(to_user_id, true);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        // Write your code here
        if (friends.containsKey(from_user_id)) {
            friends.get(from_user_id).remove(to_user_id);
        }
    }
}