Deque = double ended queue removal and addition can be done from both sides unlike in normal queues addition can be done only form the last and removal from the fist (FIFO) 
so Deqeue is interface that inherit previous methods form the  `Iterable`, `Collection`, and the `Queue` interfaces and add its specific methods 
![[Pasted image 20260116202809.png]]

**one more thing to  mention is that  the `Queue` interface methods will change to:** 
![[Pasted image 20260116203011.png]]
so they basically keep the same `Queue` interface behavior 


- **Complexity** :
All operation are O(1) FIFO/LIFO ==Amortized== and one case where can one operation be up to O(n) is when we are out of capacity and this case we need a resize and copy the elements to the new double size array which take O(n) complexity  

- **thread Safety**
`ArrayQueue` (maintain order) objects are not thread safe neither `PriorityQueue`( do not maintain order) too but there is other version of them which are thread safe and they are `ConcurrentLinkedQueue` and `PriorityBlockingQueue`