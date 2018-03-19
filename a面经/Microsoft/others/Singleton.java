/*
	Singleton-LintCode
	Singleton is a most widely used design pattern. If a class has and only has one instance at every moment, we call this design as singleton. For example, for class Mouse (not a animal mouse), we should design it in singleton.

	You job is to implement a getInstance method for given class, return the same instance of this class every time you call this method.

	Example
		In Java:

		A a = A.getInstance();
		A b = A.getInstance();
		a should equal to b.

	Challenge
	If we call getInstance concurrently, can you make sure your code could run correctly?
*/


/*
	Solution:http://www.cnblogs.com/EdwardLiu/p/4443230.html
*/
//Eager initialization
class Solution {
    /**
     * @return: The same instance of this class every time
     */
    private static Solution instance = new Solution();
    private Solution() {
    }
    public static Solution getInstance() {
        // write your code here
        return instance;
    }
}

//Lazy initialization
class Solution {
    private static Solution instance;
    private Solution() {
    }
    public static Solution getInstance() {
        // write your code here
        if (instance == null) {
            instance = new Solution();
        } 
        return instance;
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
             synchronized (LazySingleton.class) {
                 instance = new LazySingleton();
             }
         }
         return instance;
    }
}

//Double checking Locking
public class EagerSingleton {
    private static volatile EagerSingleton instance = null;
 
    // private constructor
    private EagerSingleton() {
    }
 
    public static EagerSingleton getInstance() {
        if (instance == null) {
            synchronized (EagerSingleton.class) {
                // Double check
                if (instance == null) {
                    instance = new EagerSingleton();
                }
            }
        }
        return instance;
    }
}
