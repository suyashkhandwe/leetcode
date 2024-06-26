# Problem 26. Remove Duplicates from Sorted Array

> [!NOTE]
> [26. Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150)

Given an integer array `nums` sorted in non-decreasing order, remove the duplicates [in-place](https://en.wikipedia.org/wiki/In-place_algorithm) such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in `nums`.

Consider the number of unique elements of `nums` to be `k`, to get accepted, you need to do the following things:

- Change the array nums such that the first `k` elements of `nums` contain the unique elements in the order they were present in `nums` initially. The remaining elements of `nums` are not important as well as the size of `nums`.
- Return `k`.

### Custom Judge:

The judge will test your solution with the following code:

```java
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.
```

### Examples

#### Example 1:

> **Input**: `nums = [1,1,2]`<br/>
> **Output**: `2, nums = [1,2,_]`<br/>
> **Explanation**: Your function should return `k = 2`, with the first two elements of `nums` being 1 and 2 respectively. It does not matter what you leave beyond the returned `k` (hence they are underscores).

#### Example 2:

> **Input**: `nums = [0,0,1,1,1,2,2,3,3,4]`<br/>
> **Output**: `5, nums = [0,1,2,3,4,_,_,_,_,_]`<br/>
> **Explanation**: Your function should return `k = 5`, with the first five elements of `nums` being 0, 1, 2, 3, and 4 respectively. It does not matter what you leave beyond the returned `k` (hence they are underscores).

#### Constraints:

- `1 <= nums.length <= 3 * 104`
- `-100 <= nums[i] <= 100`
- `nums is sorted in non-decreasing order`

## Solutions

### Solution 1

```java
public int removeDuplicates(int[] nums) {
    int uniqueValuesCounter = 0;
    int prevNum = -101;
    int[] uniqueIndexes = new int[nums.length];
    for (int i = 0, j = 0; i < nums.length; i++) {
        if (nums[i] != prevNum) {
            uniqueValuesCounter++;
            uniqueIndexes[j++] = i;
        }
        prevNum = nums[i];
    }
    for (int i = 0; i < uniqueIndexes.length; i++) {
        nums[i] = nums[uniqueIndexes[i]];
        if (i > 0 && uniqueIndexes[i] == 0) {
            break;
        }
    }
    return uniqueValuesCounter;
}
```

#### Complexities

- `Time Complexity`: O(n)
    - iterate through the array once to identify unique values and store their indexes, and then iterate through the uniqueIndexes array to update the original array with unique values.
- `Space Complexity`: O(n)
    - an additional array uniqueIndexes to store the indexes of unique values, which can potentially have the same length as the input array nums.

### Better Solution

```java
public int removeDuplicates(int[] nums) {
    int j = 1;
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] != nums[i - 1]) {
            nums[j] = nums[i];
            j++;
        }
    }
    return j;
}
```

> [!TIP]
> The better solution avoids storing the unique indexes by adding another iterator with `j` which is only incremented when consecutive values are not the same. Then `j` and `i` are used to "swap" values from `i` (the different value) to `j` (the last updated index). 