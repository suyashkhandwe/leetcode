# Problem 1. Two Sum

> [!NOTE]
> [1. Two Sum](https://leetcode.com/problems/two-sum/description/?envType=study-plan-v2&envId=top-interview-150)

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.
You may assume that each input would have **exactly one solution**, and you may not use the same element twice.
You can return the answer in any order.

**Follow-up**: Can you come up with an algorithm that is less than O(n²) time complexity?

### Examples

#### Example 1:

> **Input**: `nums = [2,7,11,15], target = 9`<br/>
> **Output**: `[0,1]`<br/>
> **Explanation**: `Because nums[0] + nums[1] == 9, we return [0, 1].`

#### Example 2:

> **Input**: `nums = [3,2,4], target = 6`<br/>
> **Output**: `[1,2]`<br/>

#### Example 3:

> **Input**: `nums = [3,3], target = 6`<br/>
> **Output**: `[0,1]`<br/>

#### Constraints:

- `2 <= nums.length <= 10⁴`
- `-10⁹ <= nums[i] <= 10⁹`
- `-10⁹ <= target <= 10⁹`
- `Only one valid answer exists.`

## Solutions

### Solution 1

```java
public int[] twoSum(int[] nums, int target) {
    int[] indexes = new int[2];
    for (int i = 0; i < nums.length; i++) {
        indexes[0] = i;
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[i] + nums[j] == target) {
                indexes[1] = j;
                return indexes;
            }
        }
    }
    return indexes;
}
```

#### Complexities

- `Time Complexity`: O(n²)
    - This is because we have nested loops where the outer loop runs `n` times and the inner loop runs `n-1, n-2, ..., 1 times`.
    - Therefore, the total number of iterations is `n*(n-1)/2`, which simplifies to O(n²).
- `Space Complexity`: O(1)
    - This is because we are using a constant amount of extra space to store the indexes array.

### Solution 2

```java
public int[] twoSum(int[] nums, int target) {
    int[] indexes = new int[2];
    int l = 0;
    int r = nums.length - 1;
    int[] sortedNums = Arrays.copyOfRange(nums, l, nums.length);
    Arrays.sort(sortedNums);

    int sum = sortedNums[l] + sortedNums[r];
    while (sum != target) {
        if (sum < target) {
            l++;
        } else {
            r--;
        }
        sum = sortedNums[l] + sortedNums[r];
    }

    int i = 0;
    while (nums[i] != sortedNums[l] && nums[i] != sortedNums[r]) {
        i++;
    }
    indexes[0] = i++;
    while (nums[i] != sortedNums[l] && nums[i] != sortedNums[r]) {
        i++;
    }
    indexes[1] = i;
    return indexes;
}
```

#### Complexities

- `Time Complexity`: O(n * log(n))
    - where `n` is the number of elements in the input array. 
    - The sorting step takes O(n log(n)) time, and then the two-pointer approach takes O(n) time to find the two numbers that sum up to the target.
- `Space Complexity`: O(n)
    - This is because we create a copy of the input array to sort it.

### Solution 3

```java
public int[] twoSum(int[] nums, int target) {
    for (int i = 1; i < nums.length; i++) {
        for (int j = i; j < nums.length; j++) {
            if (nums[j] + nums[j-i] == target) {
                return new int[]{j-i, j};
            }
        }
    }
    return new int[]{};
}
```

#### Complexities

- `Time Complexity`: O(n²)
    - This is because there are two nested loops iterating over the array of length `n`.
- `Space Complexity`: O(1)
  - This is because the solution only uses a constant amount of extra space regardless of the input size.

> [!TIP]
> `Solution 1` and `Solution 3` have the same complexities, however, `Solution 3` eliminates a lot of duplicate comparisons making it significantly faster.
