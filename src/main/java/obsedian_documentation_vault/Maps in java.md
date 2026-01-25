All next data structures (Interfaces + classes) in the java collection framework they internally use **Map**  

Why MAP are  not subset classes of the collection (because in Map we have KEY VALUE Mapping) not like in collections because they have one single element 

what is the Map property (HashMap and HashTable they do not maintain insertion order) (HashMap is not thread save unlike the HashTable) (HashMap can have null keys and null values unlike the HashTable).
![[Pasted image 20260116165607.png]]
we can not have duplicate keys 
1. `LoadFactor / threefy_threshold`  
	- it's main job is to help us maintain the average complexity to stabilize at O(1) because in case we had multiple collisions we will find our-self deal with multiple node in a linked list which lead to O(n) complexity 
	- so to avoid this we have a `loadFactor` that present a percentage of our table like when it's full at 75% means the `loadFactor=0.75` we double the size of the table from for example 16 to 32 after that we do re-hashing() of all of our keys to calculate their new indexes with the new size but this does not increase 100% collisions in the table we still may face many other linked lists for this 
	- we use the `THREEFY_THERSHOLD` which present the maximum size of a linked list within a singe index for example 8 after that those linked lists will be converted to a balanced binary search tree (RED_BLACK) or (AVL) and this make the insertion and deletion complexity O(log(n))
	- so by this in `HASHMAPS` the best case complexity is O(1) and the worst is O(Log(n)) of all operation 
	an this explains the behavior of `put(key,value)` and  the `get(key)` methods how they work internally.
2. entry <K,V> interface (sub interface in the Map interface) implemented by a Node<k,v> class which it define the initial capacity of the hashmap array `[{hash,key,value,next},{hash,key,value,next},...` so basically the default the default size if it's not defined is 16 or `1 << 4`
##### bullet 1 (collusion).
- How a collusion (calculation of the hash%size give the same index 1 in this example and what happened we keep linking those nodes one front the other using indexes) looks like 
![[Pasted image 20260116171731.png]]
but it comes an other question here which is how the `get(<key>)` method works, basically it calculate the `hash(<key>)%size` (which  give the index) and look for it in the `Node<k,v>[]` array but because we may have a collusion this lead us to keep checking the keys too so we look at our linked list until we find the wanted key

- one other thing we need to mention is that there is contract between the `equal()` method and the `hashCode()`method which contains three point 
	- if `obj1==obj2` & their hash should be the same 
	- if `hash(obj1)==hash(obj2)` not implicate that they are equal ~~`obj1==obj2`~~ 
- TreeMap
Map is sorted according to the insertion order or the descending or ascending order by a compactor provided during the creation  within the constructor.

and it's based on the Red-Black tree (Self balanced binary search tree).

O(Log(n)) time complexity or insert get remove operation.