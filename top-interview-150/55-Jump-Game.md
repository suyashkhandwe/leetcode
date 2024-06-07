# Problem 55. Jump Game

> [!NOTE]
> [55. Jump Game](https://leetcode.com/problems/jump-game/description/?envType=study-plan-v2&envId=top-interview-150)

You are given an integer array `nums`. You are initially positioned at the array's **first index**, and each element in the array represents your maximum jump length at that position.

Return `true` if you can reach the last index, or `false` otherwise.

### Examples

#### Example 1:

> **Input**: `nums = [2,3,1,1,4]`
> **Output**: `true`
> **Explanation**: `Jump 1 step from index 0 to 1, then 3 steps to the last index.`

#### Example 2:

> **Input**: `nums = [3,2,1,0,4]`
> **Output**: `false`
> **Explanation**: `You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.`

#### Constraints:

- `1 <= nums.length <= 104`
- `0 <= nums[i] <= 105`

## Solutions

### Solution 1

```java
public boolean canJump(int[] nums) {
    if (nums[0] == 0 && nums.length > 1) {
        return false;
    }
    if (nums.length == 1) {
        return true;
    }
    return canJumpRecursive(nums, nums.length - 1, true);
}

public boolean canJumpRecursive(int[] nums, int currPos, boolean canJump) {
    int prevPos = currPos - 1;
    if (prevPos <= 0) {
        return true;
    }
    while (prevPos >= 0) {
        if ((nums[prevPos] >= currPos - prevPos)) {
            return canJump && canJumpRecursive(nums, prevPos, canJump);
        }
        prevPos--;
    }
    return false;
}
```

#### Complexities

- `Time Complexity`: O(n^2)
    - in the worst case scenario, the algorithm may have to iterate through each element of the input array for each element
- `Space Complexity`: O(n)
    - recursive function calls consume space on the call stack proportional to the size of the input array

### Solution 2

```java
public boolean canJump(int[] nums) {
    if (nums[0] == 0 && nums.length > 1) {
        return false;
    }
    if (nums.length == 1) {
        return true;
    }
    int currPos = nums.length;
    int prevPos = currPos - 1;
    boolean canJump = false;
    while (prevPos >= 0) {
        if (currPos <= 0) {
            return true;
        }
        if ((nums[prevPos] >= currPos - prevPos)) {
            canJump = true;
            currPos = prevPos--;
        } else {
            canJump = false;
            prevPos--;
        }
    }
    return canJump;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through array once.
- `Space Complexity`: O(1)
    - uses constant amount of extra space

### Solution 3 (Conciseness)

```java
public boolean canJump(int[] nums) {
    int reachableIndex = 0;
    for (int idx = 0; idx < nums.length; idx++) {
        if (idx > reachableIndex) {
            return false;
        }
        if (reachableIndex >= nums.length) {
            return true;
        }
        reachableIndex = Math.max(reachableIndex, idx + nums[idx]);
    }
    return true;
}

```
#### Complexities

- `Time Complexity`: O(n)
    - iterate through array once.
- `Space Complexity`: O(1)
    - uses constant amount of extra space

> [!TIP]
> Basically, at each step, we compute what's the furthest index we can reach.
> - If the furthest index is less the current iterating index `idx`, return `false` since we can't "reach" this `idx`.
> - If the furthest index is beyond the size of `nums`, return `true`.
> - Otherwise, compute the furthest reachable index as the current iterating index `idx` + the max steps allows at `nums[idx]`.
