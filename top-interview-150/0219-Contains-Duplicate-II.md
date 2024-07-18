# Problem 219. Contains Duplicate II

> [!NOTE]
> [219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/solutions/61572/java-solution-using-hashmap-s-put/?source=submission-ac)

Given an integer array `nums` and an integer `k`, return `true` if there are two distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.

### Examples

#### Example 1:

> **Input**: `nums = [1,2,3,1], k = 3`<br/>
> **Output**: `true`<br/>

#### Example 2:

> **Input**: `nums = [1,0,1,1], k = 1`<br/>
> **Output**: `true`<br/>

#### Example 3:

> **Input**: `nums = [1,2,3,1,2,3], k = 2`<br/>
> **Output**: `false`<br/>

#### Constraints:

- `1 <= nums.length <= 10⁵`
- `-10⁹ <= nums[i] <= 10⁹`
- `0 <= k <= 10⁵`

## Solutions

### Solution 1

```java
public boolean containsNearbyDuplicate(int[] nums, int k) {
    for (int i = 1; i < nums.length; i++) {
        if (i <= k) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j - i] == nums[j]) {
                    return true;
                }
            }
        }
    }
    return false;
}
```

#### Complexities

- `Time Complexity`: O(n²)
    - This is because there are nested loops, with the outer loop iterating through the array once and the inner loop potentially iterating through the array again for each element.
- `Space Complexity`: O(1)
    - This is because the solution does not use any extra space that grows with the input size.

### Solution 2

```java
public boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, Integer> indexMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        Integer prevIdx = indexMap.put(nums[i], i);
        if (prevIdx != null && Math.abs(prevIdx - i) <= k) {
            return true;
        }
    }
    return false;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of elements in the input array `nums`.
    - This is because we iterate through the array once and perform constant time operations for each element.
- `Space Complexity`: O(n)
    - This is because we use a `HashMap` to store the indices of elements in the array.
    - In the worst case scenario, all elements in the array are unique and we store all `n` elements in the `HashMap`.
