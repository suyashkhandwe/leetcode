# Problem 169. Majority Element

> [!NOTE]
> [169. Majority Element](https://leetcode.com/problems/majority-element/description/?envType=study-plan-v2&envId=top-interview-150)

Given an array `nums` of size `n`, return the majority element.

The majority element is the element that appears more than `⌊n / 2⌋` times. You may assume that the majority element always exists in the array.


**Follow-up**: Could you solve the problem in linear time and in O(1) space?

### Examples

#### Example 1:

> **Input**: `nums = [3,2,3]`<br/>
> **Output**: `3`

#### Example 2:

> **Input**: `nums = [2,2,1,1,1,2,2]`<br/>
> **Output**: `2`

#### Constraints:

- `n == nums.length`
- `1 <= n <= 5 * 104`
- `-109 <= nums[i] <= 109`

## Solutions

### Solution 1

```java
public int majorityElement(int[] nums) {
    Map<Integer, Integer> frequencies = new HashMap<>();
    int retVal = nums[0];
    int majorityThreshold = (int) Math.ceil(nums.length / 2.0);
    for (int i = 0; i < nums.length; i++) {
        frequencies.put(nums[i], frequencies.getOrDefault(nums[i], 0) + 1);
        if (frequencies.get(nums[i]) >= majorityThreshold) {
            retVal = nums[i];
        }
    }
    return retVal;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - Iterates through the array once to populate frequencies. And then iterates through the map once for each of `n` items.
- `Space Complexity`: O(n)
    - Stores all `n` elements in the map and worst case scenario could be to store all elements.

### Solution 2

```java
public int majorityElement(int[] nums) {
    Arrays.sort(nums, 0, nums.length);
    int majorityCount = 1;
    int majorityThreshold = (int) Math.ceil(nums.length / 2.0);
    int majorityValue = nums[0];
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] == nums[i - 1]) {
            majorityCount++;
        } else {
            majorityCount = 1;
        }
        if (majorityCount >= majorityThreshold) {
            majorityValue = nums[i];
        }
    }
    return majorityValue;
}
```

#### Complexities

- `Time Complexity`: O(n log n)
    - Sorts the array.
- `Space Complexity`: O(1)
    - Uses the same array with no additional storage.

### Solution 3 - Challenge

```java
public int majorityElement(int[] nums) {
    int majorityNum = -1;
    int majorityCount = 0;
    for (int num : nums) {
        if (majorityCount == 0) {
            majorityNum = num;
        }
        majorityCount += (num == majorityNum) ? 1 : -1;
    }
    if (majorityCount > nums.length / 2) {
        return majorityNum;
    }
    return majorityNum;
}
```

> [!TIP]
> This uses [Boyer-Moore Majority Voting Algorithm](https://www.geeksforgeeks.org/boyer-moore-majority-voting-algorithm/)

#### Complexities

- `Time Complexity`: O(n)
    - Iterates through the array only once.
- `Space Complexity`: O(1)
    - Uses the same array with no additional storage.
