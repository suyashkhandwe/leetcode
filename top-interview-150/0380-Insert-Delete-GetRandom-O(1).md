# Problem 380. Insert Delete GetRandom O(1)

> [!NOTE]
> [380. Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1/description/?envType=study-plan-v2&envId=top-interview-150)

Implement the `RandomizedSet` class:

- `RandomizedSet()`: Initializes the `RandomizedSet` object.
- `bool insert(int val)`: Inserts an item `val` into the set if not present.
    - Returns `true` if the item was not present, `false` otherwise.
- `bool remove(int val)`: Removes an item `val` from the set if present.
    - Returns `true` if the item was present, `false` otherwise.
- `int getRandom()`: Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called).
    - Each element must have the same probability of being returned.

You must implement the functions of the class such that each function works in average `O(1) time complexity`.

### Examples

#### Example 1:

> **Input**: `["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]`<br/>
> - `[[], [1], [2], [2], [], [1], [2], []]`
> **Output**: `[null, true, false, true, 2, true, false, 2]`<br/>
> **Explanation**:

#### Constraints:

- `-2^31 <= val <= 2^31 - 1`
- `At most 2 * 10^5 calls will be made to insert, remove, and getRandom.`
- `There will be at least one element in the data structure when getRandom is called.`

## Solutions

### Solution 1

```java
public class RandomizedSet {

    private final HashSet<Integer> intSet;
    private final Random random;

    public RandomizedSet() {
        intSet = new HashSet<>();
        random = new Random();
    }

    public boolean insert(int val) {
        return intSet.add(val);
    }

    public boolean remove(int val) {
        return intSet.remove(val);
    }

    public int getRandom() {
        return (int) intSet.toArray()[random.nextInt(intSet.size())];
    }
}
```

#### Complexities

- `Time Complexity`: O(1)
    - insert, remove, and getRandom : HashSet operations like add, remove, and toArray have an average time complexity of O(1) due to the hashing function used to store and retrieve elements
- `Space Complexity`: O(n)
    - because the HashSet stores each unique element in the set, and the space required is directly proportional to the number of elements stored.
