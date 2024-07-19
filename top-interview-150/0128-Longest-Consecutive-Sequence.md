# Problem 128. Longest Consecutive Sequence

> [!NOTE]
> [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/description/?envType=study-plan-v2&envId=top-interview-150)

Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.
**You must write an algorithm that runs in `O(n)` time.**

### Examples

#### Example 1:

> **Input**: `nums = [100,4,200,1,3,2]`<br/>
> **Output**: `4`<br/>
> **Explanation**: `The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.`

#### Example 2:

> **Input**: `nums = [0,3,7,2,5,8,4,6,0,1]`<br/>
> **Output**: `9`<br/>

#### Constraints:

- `0 <= nums.length <= 10⁵`
- `-10⁹ <= nums[i] <= 10⁹`

## Solutions

### Solution 1

```java
public int longestConsecutive(int[] nums) {
    Set<Integer> setOfNums = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    if (setOfNums.size() < 2) {
        return setOfNums.size();
    }

    List<Integer> smallestNums = Arrays.stream(nums)
            .boxed()
            .filter(num -> setOfNums.contains(num + 1))
            .filter(num -> !setOfNums.contains(num - 1))
            .toList();

    int longestConsecutiveLength = 1;
    int consecutiveLength = 1;
    for (Integer smallestNum : smallestNums) {
        while (setOfNums.contains(++smallestNum)) {
            consecutiveLength++;
        }
        longestConsecutiveLength = Math.max(consecutiveLength, longestConsecutiveLength);
        consecutiveLength = 1;
    }
    return longestConsecutiveLength;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - where `n` is the number of elements in the input array `nums`.
    - This is because we iterate through the array once to create a set of numbers and then iterate through the set to find the longest consecutive sequence.
- `Space Complexity`: O(n)
    - This is because we use a set to store the numbers from the input array.

### Solution 2

```java
public int longestConsecutive(int[] nums) {
    Map<Integer, Boolean> mapOfNums = new HashMap<>();
    Arrays.stream(nums).boxed().forEach(num -> mapOfNums.put(num, false));

    if (mapOfNums.size() < 2) {
        return mapOfNums.size();
    }

    for (int num : nums) {
        if (mapOfNums.containsKey(num + 1) && !mapOfNums.containsKey(num - 1)) {
            mapOfNums.put(num, true);
        }
    }
    int longestConsecutiveLength = 1;
    int consecutiveLength = 1;
    for (Map.Entry<Integer, Boolean> entry : mapOfNums.entrySet()) {
        if (entry.getValue()) {
            int num = entry.getKey() + 1;
            while (mapOfNums.containsKey(num)) {
                consecutiveLength++;
                num++;
            }
        }
        longestConsecutiveLength = Math.max(consecutiveLength, longestConsecutiveLength);
        consecutiveLength = 1;
    }
    return longestConsecutiveLength;
}
```

#### Complexities

- `Time Complexity`: O(n)
  - where `n` is the number of elements in the input array `nums`.
  - This is because we iterate through the array once to populate the `mapOfNums` and then iterate through the `mapOfNums` to find the longest consecutive sequence.
- `Space Complexity`: O(n)
  - This is because we use a map to store the elements of the input array `nums`.
