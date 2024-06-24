# Problem 45. Jump Game II

> [!NOTE]
> [45. Jump Game II](https://leetcode.com/problems/jump-game-ii/description/?envType=study-plan-v2&envId=top-interview-150)

You are given a `0-indexed` array of integers `nums` of length `n`. You are initially positioned at `nums[0]`.

Each element `nums[i]` represents the maximum length of a forward jump from index `i`. In other words, if you are at `nums[i]`, you can jump to any `nums[i + j]` where:

- `0 <= j <= nums[i]`
- `i + j < n`

Return the minimum number of jumps to reach `nums[n - 1]`. The test cases are generated such that you can reach `nums[n - 1]`.


### Examples

#### Example 1:

> **Input**: `nums = [2,3,1,1,4]`
> **Output**: `2`
> **Explanation**: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

#### Example 2:

> **Input**: `nums = [2,3,0,1,4]`
> **Output**: `2`
> **Explanation**:

#### Constraints:

- `1 <= nums.length <= 104`
- `0 <= nums[i] <= 1000`
- `It's guaranteed that you can reach nums[n - 1].`

## Solutions

### Solution 1

```java
public int jump(int[] nums) {
    int reachedIdx = 0;
    int maxPossibleIdx = 0;
    int steps = 0;
    if (nums.length == 1) {
        return steps;
    }
    for (int i = 0; i < nums.length - 1; i++) {
        maxPossibleIdx = Math.max(maxPossibleIdx, i + nums[i]);
        if (i == reachedIdx) {
            steps++;
            reachedIdx = maxPossibleIdx;
        }
    }
    return steps;
}
```

#### Complexities

- `Time Complexity`: O(n) 
    - iterate through the array once.
- `Space Complexity`: O(1)
    - only a constant amount of extra space for variables like reachedIdx, maxPossibleIdx, and steps, regardless of the size of the input array.
