# Problem 189. Rotate Array

> [!NOTE]
> [189. Rotate Array](https://leetcode.com/problems/rotate-array/description/?envType=study-plan-v2&envId=top-interview-150)

Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

**Follow up:**
- Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
- Could you do it in-place with O(1) extra space?

### Examples

#### Example 1:

> **Input**: `nums = [1,2,3,4,5,6,7]`, `k = 3`<br/>
> **Output**: `[5,6,7,1,2,3,4]`<br/>
> **Explanation**:
> - rotate 1 steps to the right: [7,1,2,3,4,5,6]
> - rotate 2 steps to the right: [6,7,1,2,3,4,5]
> - rotate 3 steps to the right: [5,6,7,1,2,3,4]

#### Example 2:

> **Input**: `nums = [-1,-100,3,99]`, `k = 2`<br/>
> **Output**: `[3,99,-1,-100]`<br/>
> **Explanation**:
> - rotate 1 steps to the right: [99,-1,-100,3]
> - rotate 2 steps to the right: [3,99,-1,-100]

#### Constraints:

- `1 <= nums.length <= 105`
- `-231 <= nums[i] <= 231 - 1`
- `0 <= k <= 105`

## Solutions

### Solution 1

```java
public void rotate(int[] nums, int k) {
    //If k > the nums.length, just rotate the remainder of the times since nums.length rotation would result in the same array
    k %= nums.length;
    int[] lastKNums = new int[k];
    for (int i = 0; i < k; i++) {
        lastKNums[i] = nums[nums.length - i - 1];
    }

    for (int i = nums.length - k - 1, j = nums.length - 1; i >= 0; i--, j--) {
        nums[j] = nums[i];
    }

    for (int i = 0; i < k; i++) {
        nums[i] = lastKNums[k - i - 1];
    }
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the array three times, each time with a linear time complexity
- `Space Complexity`: O(k)
    - create an additional array of size k to store the last k elements of the input array

### Solution 2

```java
public void rotate(int[] nums, int k) {
    //If k > the nums.length, just rotate the remainder of the times since nums.length rotation would result in the same array
    k %= nums.length;
    int kCounter = 0;
    while (kCounter++ < k) {
        int dropped = nums[nums.length - 1];
        for (int i = nums.length - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
            log.info("\tnums={}", nums);
        }
        nums[0] = dropped;
        log.info("nums={}", nums);
    }
}
```

#### Complexities

- `Time Complexity`: O(n*k)
    - iterate through the array `n * k` times
- `Space Complexity`: O(1)
    - no extra space is being used

> [!CAUTION]
> Times out for very large payloads

### Better Solution

```java

```

> [!TIP]
> ?