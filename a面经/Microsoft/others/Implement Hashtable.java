// Use array to implement HashTable
public class HashEntry {
      private int key;
      private int value;
 
      HashEntry(int key, int value) {
            this.key = key;
            this.value = value;
      }     
 
      public int getKey() {
            return key;
      }
 
      public int getValue() {
            return value;
      }
}

public class HashMap {
      private final static int TABLE_SIZE = 128;
 
      HashEntry[] table;
 
      HashMap() {
            table = new HashEntry[TABLE_SIZE];
            for (int i = 0; i < TABLE_SIZE; i++) {
                  table[i] = null;
            }
      }
 
      public int get(int key) {
            int hash = (key % TABLE_SIZE);
            
            //if find collision
            while (table[hash] != null && table[hash].getKey() != key) {
                  hash = (hash + 1) % TABLE_SIZE;
            }

            return table[hash] == null ? -1 : table[hash].getValue();
      }
 
      public void put(int key, int value) {
            int hash = (key % TABLE_SIZE);
            while (table[hash] != null && table[hash].getKey() != key) {
                  hash = (hash + 1) % TABLE_SIZE;// if find collison rehash
            }
            table[hash] = new HashEntry(key, value);
      }
}


Rehashing
/*
      The size of the hash table is not determinate at the very beginning. If the total size of keys is too large (e.g. size >= capacity / 10), we should double the size of the hash table and rehash every keys. Say you have a hash table looks like below:

      size=3, capacity=4

      [null, 21, 14, null]
             ↓    ↓
             9   null
             ↓
            null
      The hash function is:

      int hashcode(int key, int capacity) {
          return key % capacity;
      }
      here we have three numbers, 9, 14 and 21, where 21 and 9 share the same position as they all have the same hashcode 1 (21 % 4 = 9 % 4 = 1). We store them in the hash table by linked list.

      rehashing this hash table, double the capacity, you will get:

      size=3, capacity=8

      index:   0    1    2    3     4    5    6   7
      hash : [null, 9, null, null, null, 21, 14, null]
      Given the original hash table, return the new hash table after rehashing .

      Have you met this question in a real interview? Yes
      Example
      Given [null, 21->9->null, 14->null, null],

      return [null, 9->null, null, null, null, 21->null, 14->null, null]

      Note
      For negative integer in hash table, the position can be calculated as follow:

      C++/Java: if you directly calculate -4 % 3 you will get -1. You can use function: a % b = (a % b + b) % b to make it is a non negative integer.
      Python: you can directly use -1 % 3, you will get 2 automatically.
 */
      public class Solution {
          /**
           * @param hashTable: A list of The first node of linked list
           * @return: A list of The first node of linked list which have twice size
           */    
          public ListNode[] rehashing(ListNode[] hashTable) {
              // write your code here
              int oldSize = hashTable.length;
              int newSize = hashTable.length * 2;
              if (hashTable == null || oldSize == 0) {
                  return null;
              }
              ListNode[] res = new ListNode[newSize];
              for (int i = 0; i < oldSize; i++) {
                  if (hashTable[i] != null) {
                      rehash(hashTable, res, i);
                  }
              }
              return res;
          }
          
          public void rehash(ListNode[] hashTable, ListNode[] res, int i) {
              int newSize = res.length;
              ListNode cur = hashTable[i];
              while (cur != null) {
                  int val = cur.val;
                  int newPos = val >= 0 ? val % newSize : (val % newSize + newSize) % newSize;
                  if (res[newPos] == null) {
                      res[newPos] = new ListNode(val);
                  } else {
                      ListNode temp = res[newPos];
                      while (temp.next != null) {
                          temp = temp.next;
                      }
                      temp.next = new ListNode(val);
                  }
                  cur = cur.next;
              }
          }
      };
      
Hash Function
/*
      n data structure Hash, hash function is used to convert a string(or any other type) into an integer smaller than hash size and bigger or equal to zero. The objective of designing a hash function is to "hash" the key as unreasonable as possible. A good hash function can avoid collision as less as possible. A widely used hash function algorithm is using a magic number 33, consider any string as a 33 based big integer like follow:

      hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE 

                                    = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE

                                    = 3595978 % HASH_SIZE

      here HASH_SIZE is the capacity of the hash table (you can assume a hash table is like an array with index 0 ~ HASH_SIZE-1).

      Given a string as a key and the size of hash table, return the hash value of this key.f

      Have you met this question in a real interview? Yes
      Example
      For key="abcd" and size=100, return 78
      Clarification
      For this problem, you are not necessary to design your own hash algorithm or consider any collision issue, you just need to implement the algorithm as described.
 */
/*
      (a + b) % p = (a % p + b % p) % p （1） 
      (a - b) % p = (a % p - b % p) % p （2） 
      (a b) % p = (a % p b % p) % p （3） 
       a ^ b % p = ((a % p)^b) % p （4）

      应该还有一个（3）的变式 (a b) % p = (a % p b) % p （3）
 */
      public class Solution {
           public int hashCode(char[] key,int HASH_SIZE) {
               // write your code here
               long res = 0;
               for (int i = 0; i < key.length; i++) {
                   res = 33 * res + key[i];
                   res %= HASH_SIZE;
               }
               return (int)res;
           }
      };