Jiayi Gu 
31526890 
Lab Partner: Yuxin Ren 
This file contains 2 files: LinearProbing.java and UR_HashTable.java. LinearProbing extends UR_HashTable and also implements Iterable. 
This lab writes a LinearProbing Hash Table class. In LinearProbing, it contains several methods, including put, delete, get, contain, iteration, hash, size, resize, isEmpty, etc.
For the hash method, it will return the hashCode of the input. In order to put the key into the Hash Table, we still need to use the hash() and mod(table size), so we can fit it into the hash table. Also, for the delete method, after deleting the corresponding key, it will rehash the key after the deleted key. Therefore, if there is a key with hash(key)%m that is less than the corresponding deleted key position, it will put that key into that position. 
