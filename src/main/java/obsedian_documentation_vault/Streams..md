Initial intuition of Streams in java is basically a pipeline through which we pass our collection's elements applying various kind of filters, or sorting operations, useful when dealing with ==bulk== processing (can do parallel processing).

1. So basically it all start with the creation of a Stream (Step one).
2. Second we have to add the intermediate operations like `sort()` , `filter()`, `map()`... so those operation transform a stream to an other stream and more operation can be done on top of it,
	-  those  ==operations are lazy in nature means these operations get executed only when terminal operation is invoked.==
3. At step 3 terminal Operations like `collect()`, `reduce()`, `count()`..., 
	- these operations triggers the processing of the stream 
	- and produce the output means after the terminal operation used, no more operation we can perform.

Note: 
- that the resulting of the stream is total new collection the input of the stream will not be modified at all.
- also one terminal operation the stream is considered consumed and can not be used again `IllegalStateException`

here is simple example of streams:
```java 
public class StreamExample{

	public static void main(String[] args){
		List<Integer> salaries = new ArrayList<>();
		long count = 0
		
		//normal loop
		for(int salary: salaries){
			if(salary>3000) coutn++;
		}
		
		// with streams 
		long output = salaries.stream().
						filter((Integer salr) -> salr>3000).
						count();
		
	}
}
```

##### ways of creating streams (step1)
- from collection 
```java 
List<Integer> salaries = new ArrayList<>();
Stream<Integer> streamFromCollection = salaries.stream();
```
- form Array
```java 
List<Integer> salaries = {3223,4656,6724,989885}
Stream<Integer> streamFromArray = Arrays.stream(salaries); //using utility class of Array class 
```
- from static method
 ```java 
Stream<Integer> streamFromStaticMethod = Stream.of(3223,4656,6724,989885);
```
- from Stream Builder:
```java
Stream.Builder<Integer> streamBuilder = Stream.builder();
streamBuilder.add(10000).add(23111).add(10023);

Stream<Integer> streamFromBuilder = streamBuilder.build();
```
- from stream Iterate:
```java 
Stream<Integer> streamFromIterate = Stream.iterate(1000,(Integer n) -> n+5000).limit(5);
```
##### Different intermediate operations (step2)
- `filter(Predicate<T> predicate)` based on the predicate if it's true (the element achieve a certain condition) then it'l be added to the next stream.
- `map(Funciton<T,R> mapper)` used to transform each element.

##### Why the intermediate operations are lazy :

**The Raw Concept**
**Stream operations are lazy because they don't process data until absolutely necessary.**

When you chain stream methods like `filter()`, `map()`, `sorted()`, nothing actually happens to your data yet. The stream just **remembers what operations you want to perform**.

Only when you call a **terminal operation** like `collect()`, `forEach()`, or `findFirst()` does the stream actually start processing your data.

 **1. You create a pipeline**
```java
Stream<String> stream = list.stream()
    .filter(s -> s.startsWith("A"))  // Step 1
    .map(s -> s.toUpperCase())       // Step 2
    .sorted();                       // Step 3
```
**Result:** Java creates a blueprint. No data is touched. Your list is still exactly the same.

**2. You add a terminal operation**
```java
List<String> result = stream.collect(Collectors.toList());
```
**Now** Java executes the blueprint:
1. Takes first item from list
2. Checks if it starts with "A" (filter)
3. If yes, converts to uppercase (map)
4. Stores it
5. Repeats for all items
6. Sorts all stored items
7. Returns final list
 **Without Laziness:**
```java
// If eager (hypothetical):
list.filter()  // Processes ALL items → creates new list
    .map()     // Processes ALL filtered items → creates new list  
    .sorted()  // Processes ALL mapped items → creates new list
```
**Problem:** Creates 3 intermediate lists, processes everything even if you only need first item.

 **With Laziness (Actual):**
```java
// You only want first item
Optional<String> first = list.stream()
    .filter(s -> s.startsWith("A"))
    .map(s -> s.toUpperCase())
    .findFirst();
```
**What happens:**
1. Takes first item
2. Checks filter → if passes
3. Applies map
4. Returns it → **STOPS**

Items after the first one are never even looked at.
![[Pasted image 20260103145143.png]]



##### Sequence of Stream operations:

![[Pasted image 20260103145143.png]]
![[Pasted image 20260103150134.png]]

##### Parallel Strem:

Performing operation on a stream concurrently, taking advantage of multi core CPU `ParallelStream()` method is used instead of regular stream() method 
- internally 
	- Task splitting: it uses "spliterator" function to split data into multiple chunks.
	- task submission and parallel processing: uses ==Fork-join pool technique.==

![[Pasted image 20260103151359.png]]

Example:
![[Pasted image 20260103150920.png]]
