# Problem 11. Container With Most Water

> [!NOTE]
> [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-interview-150)

You are given an integer array `height` of length `n`. There are `n` vertical lines drawn such that the two endpoints of the `ith` line are `(i, 0)` and `(i, height[i])`.

Find two lines that together with the `x-axis` form a container, such that the container contains the most water.

Return _the maximum amount of water a container can store_.

**Notice** that you may not slant the container.

### Examples

#### Example 1:

> **Input**: `height = [1,8,6,2,5,4,8,3,7]`<br/>
> ![Plotted Graph](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/07/17/question_11.jpg)<br/>
> **Output**: `49`<br/>
> **Explanation**: `The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.`

#### Example 2:

> **Input**: `height = [1,1]`<br/>
> **Output**: `1`

#### Constraints:

- `n == height.length`
- `2 <= n <= 105`
- `0 <= height[i] <= 104`

## Solutions

### Solution 1

```java
public int maxArea(int[] height) {
    int maxArea = 0;
    for (int l = 0; l < height.length - 1; l++) {
        for (int r = l + 1; r < height.length; r++) {
            int area = Math.min(height[r], height[l]) * (r - l);
            maxArea = Math.max(maxArea, area);
        }
    }
    return maxArea;
}
```

#### Complexities

- `Time Complexity`: O(n^2)
    - Iterates through each `height` once in the outer loop, and then for each one iterates again in the inner loop.
- `Space Complexity`: O(1)
    - Uses constant amount of space regardless of the input.

### Solution 2

```java
public int maxArea(int[] height) {
    int maxArea = 0;
    int l = 0;
    int r = height.length - 1;
    while (l < r) {
        if (height[r] >= height[l]) {
            maxArea = Math.max(maxArea, height[l] * (r - l++));
        } else {
            maxArea = Math.max(maxArea, height[r] * (r-- - l));
        }
    }
    return maxArea;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - Uses a two-pointer approach to iterate through the array once.
- `Space Complexity`: O(1)
    - Uses constant amount of space regardless of the input.

> [!CAUTION]
> `Solution 1` has a high time complexity and will timeout.
