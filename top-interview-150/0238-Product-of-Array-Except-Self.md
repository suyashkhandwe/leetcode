# Problem 238. Product of Array Except Self

> [!NOTE]
> [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=top-interview-150)

Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of nums except `nums[i]`.

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in `O(n)` time and without using the division operation.

### Examples

#### Example 1:

> **Input**: `nums = [1,2,3,4]`<br/>
> **Output**: `[24,12,8,6]`

#### Example 2:

> **Input**: `nums = [-1,1,0,-3,3]`<br/>
> **Output**: `[0,0,9,0,0]`

#### Constraints:

- `2 <= nums.length <= 105`
- `-30 <= nums[i] <= 30`
- `The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.`

## Solutions

### Solution 1

```java
public int[] productExceptSelf(int[] nums) {
    int[] answer = new int[nums.length];
    List<Integer> zeroIndexes = new ArrayList<>();

    int product = 1;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
            product *= nums[i];
        } else {
            zeroIndexes.add(i);
        }
    }
    for (int i = 0; i < nums.length; i++) {
        if ((!zeroIndexes.isEmpty() && !zeroIndexes.contains(i)) || zeroIndexes.size() > 1) {
            answer[i] = 0;
        } else if (nums[i] == 0) {
            answer[i] = product;
        } else {
            int multiplier = nums[i] > 0 ? 1 : -1;
            answer[i] = product / (Math.abs(nums[i]) * multiplier);
        }
    }
    return answer;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the array twice, once to calculate the product of all elements excluding zeros, and once to calculate the final productsExceptSelf array
- `Space Complexity`: O(n)
    - use an additional array of the same length as the input array to store the productsExceptSelf values

> [!CAUTION]
> If the requirement is to avoid using division, the above approach can not be used. 
> In that case, we can use `left to right` and `right to left` multiplication as shown below.

### Solution 2

```java
public int[] productExceptSelf(int[] nums) {
    int[] answer = IntStream.range(0, nums.length).map(it -> 1).toArray();
    int leftProduct = 1;
    int rightProduct = 1;
    for (int i = 0, j = nums.length - 1; i < nums.length; i++, j--) {
        answer[i] *= leftProduct;
        leftProduct *= nums[i];
        answer[j] *= rightProduct;
        rightProduct *= nums[j];
    }
    return answer;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the array twice, once from left to right and once from right to left, each time performing constant time operations
- `Space Complexity`: O(n)
    - an additional array of the same length as the input array to store the products except self
